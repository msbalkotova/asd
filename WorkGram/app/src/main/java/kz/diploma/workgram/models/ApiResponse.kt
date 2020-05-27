package kz.diploma.workgram.models

import android.util.Log
import androidx.annotation.Nullable
import com.google.gson.Gson
import kz.diploma.workgram.R
import kz.diploma.workgram.models.errors.ErrorResponse
import kz.diploma.workgram.models.errors.SessionExpired
import kz.diploma.workgram.utils.Constants
import kz.diploma.workgram.utils.helpers.ErrorHelper
import org.greenrobot.eventbus.EventBus
import retrofit2.Response
import java.util.regex.Pattern

class ApiResponse<T> {
    val code: Int
    @Nullable
    var body: T? = null
    var errorCode: Int? = null
    var msgCode: Int = R.string.generic_error
    val isSuccessful: Boolean
        get() = code in 200..300

    constructor(error: Throwable) {
        this.code = 500
    }

    constructor(response: Response<T>) {
        this.code = response.code()
        this.errorCode = null
        if (response.isSuccessful) {
            this.body = response.body()
        } else {
            response.errorBody()?.let {
                try {
                    val errResp = Gson().fromJson(it.string(), ErrorResponse::class.java)
                    errResp?.let {
                        this.msgCode = ErrorHelper.getErrorMessage(it.errorCode)
                        this.errorCode = it.errorCode
                    }
                } catch (exc: Exception) {
                    Log.e("ApiResponse", "error while parsing")
                }
            }
            if(response.code() == Constants.UNAUTHORIZED_CODE){
                EventBus.getDefault().post(SessionExpired(true))
            }
        }
    }
}