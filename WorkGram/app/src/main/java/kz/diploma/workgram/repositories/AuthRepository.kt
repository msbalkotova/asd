package kz.diploma.workgram.repositories

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import kz.diploma.workgram.models.ApiResponse
import kz.diploma.workgram.models.profile.UserDto
import kz.diploma.workgram.repositories.utils.NetworkOnlyRepository
import kz.diploma.workgram.repositories.vo.Resource
import kz.diploma.workgram.services.ApiService
import kz.diploma.workgram.utils.Constants

class AuthRepository(private val apiService: ApiService,
                     private val sharedPref: SharedPreferences) {
    fun authRequest(authType: String, email: String, password: String): LiveData<Resource<UserDto>> {
        // val fcmToken = sharedPref.getString(AppConstants.FCM_TOKEN, "")
        return object : NetworkOnlyRepository<UserDto, UserDto>(){

            override fun saveLoadedData(item: UserDto) {
                item.email = email
                saveUserJson(item)
            }

            override fun fetchService(): LiveData<ApiResponse<UserDto>> {
                return apiService.auth(authType, email, password, "ANDROID", "asd")
            }

            override fun onFetchFailed(errCode: Int?) {
            }

        }.asLiveData()
    }

    private fun saveUserJson(user: UserDto){
        val userJson = Gson().toJson(user)
        sharedPref.edit().putString(Constants.USER_JSON, userJson).apply()
    }
}