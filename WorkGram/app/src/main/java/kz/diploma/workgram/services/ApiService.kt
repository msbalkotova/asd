package kz.diploma.workgram.services

import androidx.lifecycle.LiveData
import io.reactivex.Single
import kz.diploma.workgram.models.ApiResponse
import kz.diploma.workgram.models.BaseResponse
import kz.diploma.workgram.models.categories.CategoryResponseDto
import kz.diploma.workgram.models.offers.OfferResponse
import kz.diploma.workgram.models.profile.UserDto
import kz.diploma.workgram.models.profile.UserResponse
import kz.diploma.workgram.models.workers.WorkersResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("api/{auth_type}")
    fun auth(
        @Path("auth_type") authType: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("platform") platform: String,
        @Field("device_token") fcmtoken: String?

    ): LiveData<ApiResponse<UserDto>>


    @GET("api/categories")
    fun getCategories(
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): LiveData<ApiResponse<CategoryResponseDto>>

    @GET("api/user/categories")
    fun getMyCategories(
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): LiveData<ApiResponse<CategoryResponseDto>>

    @FormUrlEncoded
    @POST("api/user/categories/add")
    fun sendMyCategories(
        @Field("categories_ids[]") cats: List<Int>
    ): LiveData<ApiResponse<BaseResponse>>

    @GET("api/users/by/category/{categoryId}")
    fun getUsersByCategory(
        @Path("categoryId") categoryId: Int?,
        @Query("page") page: Int
    ): Single<WorkersResponse>

    @GET("api/projects")
    fun getProjects(
        @Query("page") page: Int
    ): Single<OfferResponse>

    @GET("api/user/projects/implementer")
    fun getImplementedProjects(
        @Query("page") page: Int
    ): Single<OfferResponse>

    @GET("api/user/projects/creator")
    fun getCreatedProjects(
        @Query("page") page: Int
    ): Single<OfferResponse>

    @FormUrlEncoded
    @POST("api/create/project")
    fun createProject(
        @FieldMap params: Map<String, String>
    ): LiveData<ApiResponse<BaseResponse>>

    @GET("api/profile")
    fun fetchUserProfile(): LiveData<ApiResponse<UserResponse>>

    @Multipart
    @POST("api/profile/avatar")
    fun uploadAvatar(
        @Part image: MultipartBody.Part?
    ): Call<BaseResponse>

    @POST("api/profile/update")
    fun updateProfile(
        @Body body: Map<String, String>
    ): LiveData<ApiResponse<BaseResponse>>
}