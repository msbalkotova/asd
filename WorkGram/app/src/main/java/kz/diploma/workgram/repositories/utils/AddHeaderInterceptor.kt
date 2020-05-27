package kz.diploma.workgram.repositories.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import kz.diploma.workgram.models.profile.UserDto
import kz.diploma.workgram.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AddHeaderInterceptor(val sPref: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val user = Gson().fromJson(sPref.getString(Constants.USER_JSON, "{}"), UserDto::class.java)

        request = request.newBuilder()
            .header("Authorization", "Bearer ${user.token}")
            .build()

        return chain.proceed(request)
    }
}