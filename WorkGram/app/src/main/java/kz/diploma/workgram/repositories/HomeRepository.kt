package kz.diploma.workgram.repositories

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kz.diploma.workgram.models.ApiResponse
import kz.diploma.workgram.models.BaseResponse
import kz.diploma.workgram.models.categories.CategoryResponseDto
import kz.diploma.workgram.models.profile.UserDto
import kz.diploma.workgram.models.profile.UserResponse
import kz.diploma.workgram.repositories.utils.NetworkOnlyRepository
import kz.diploma.workgram.repositories.vo.Resource
import kz.diploma.workgram.services.ApiService
import kz.diploma.workgram.utils.Constants
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import java.io.File

class HomeRepository(private val apiService: ApiService,
private val sharedPref: SharedPreferences)
{
    val TAG = HomeRepository::class.java.simpleName

    fun getUser() = Gson().fromJson(sharedPref.getString(Constants.USER_JSON, "{}"), UserDto::class.java)

    fun fetchCategories(page: Int?, size: Int?): LiveData<Resource<CategoryResponseDto>> {
        return getNetworkData { apiService.getCategories(page, size) }
    }

    fun fetchMyCategories(page: Int?, size: Int?): LiveData<Resource<CategoryResponseDto>> {
        return getNetworkData { apiService.getMyCategories(page, size) }
    }

    fun sendMyCategories(myCategories: List<Int>): LiveData<Resource<BaseResponse>> {
        return getNetworkData { apiService.sendMyCategories(myCategories) }
    }

    fun createProject(params: Map<String, String>): LiveData<Resource<BaseResponse>>{
        return getNetworkData { apiService.createProject(params) }
    }

    fun getProfile(): LiveData<Resource<UserResponse>>{
        return getNetworkData { apiService.fetchUserProfile() }
    }

    fun uploadAvatar(file: File): MutableLiveData<BaseResponse> {
        val baseResponse = MutableLiveData<BaseResponse>()

        val requestBody = RequestBody.create(MediaType.parse("*/*"), file)
        val filePart = MultipartBody.Part.createFormData("avatar", file.name, requestBody)

        val call = apiService.uploadAvatar(filePart)
        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: retrofit2.Response<BaseResponse>) {
                if(response.isSuccessful) {
                    baseResponse.postValue(response.body())
                }else{
                    val errorResp = BaseResponse()
                    errorResp.success = false
                    baseResponse.postValue(errorResp)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                val errorResp = BaseResponse()
                errorResp.success = false
                baseResponse.postValue(errorResp)
            }
        })

        return baseResponse
    }

    fun updateProfile(name: String, lastName: String, nick: String,
                      phone: String, description: String): LiveData<Resource<BaseResponse>>{
        val body = HashMap<String, String>()
        body["name"] = name
        body["surname"] = lastName
        body["nickname"] = nick
        body["phone"] = phone
        body["description"] = description

        return object : NetworkOnlyRepository<BaseResponse, BaseResponse>(){
            override fun saveLoadedData(item: BaseResponse) {
                val user = getUser()
                user.name = name
                user.surname = lastName
                user.nickname = nick

                saveUserJson(user)
            }

            override fun fetchService(): LiveData<ApiResponse<BaseResponse>> {
                return apiService.updateProfile(body)
            }

            override fun onFetchFailed(errCode: Int?) {

            }
        }.asLiveData()
    }

    fun logout(){
        sharedPref.edit().remove(Constants.USER_JSON).apply()
    }

    fun <T> getNetworkData(serviceCall: () -> LiveData<ApiResponse<T>>): LiveData<Resource<T>> {
        return object : NetworkOnlyRepository<T, T>() {
            override fun saveLoadedData(item: T) {

            }

            override fun fetchService(): LiveData<ApiResponse<T>> {
                return serviceCall()
            }

            override fun onFetchFailed(errorCode: Int?) {
                Log.d(TAG, "onFetchFailed : $errorCode")
            }
        }.asLiveData()
    }

    private fun saveUserJson(user: UserDto){
        val userJson = Gson().toJson(user)
        sharedPref.edit().putString(Constants.USER_JSON, userJson).apply()
    }

}