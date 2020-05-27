package kz.diploma.workgram.utils.helpers

import kz.diploma.workgram.R


class ErrorHelper {
    companion object{
        val INVALID_FIELD = 1
        val UNAUTHORIZED = 2
        val AUTH_ERROR = 4
        val ACCESS_DENIED = 5
        val UNIQUE_RESOURCE_CONFLICT = 6
        val INVALID_ARGUMENT = 8
        val INVALID_TOKEN = 9
        val INVALID_RESET_CODE = 10
        val INVALID_PASSWORD_FORMAT = 11
        val INVALID_EMAIL_FORMAT = 12
        val EXPIRED_RESET_CODE = 14
        val EXPIRED_TOKEN = 15
        val TOO_LARGE_FILE_SIZE = 18
        val REQUIRED_PARAMS_NOT_FOUND = 19
        val FIELD_REQUIRED = 24
        val INCORRECT_AMOUNT = 25
        val NOT_REGISTERED = 26
        val INCORRECT_PASSWORD = 27

        fun getErrorMessage(code: Int?): Int{
            code?.let{
                var msgCode = R.string.generic_error
                when(code){
                    INVALID_FIELD -> {msgCode = R.string.INVALID_FIELD }
                    UNAUTHORIZED -> msgCode = R.string.UNAUTHORIZED
                    AUTH_ERROR -> R.string.AUTH_ERROR
                    INVALID_ARGUMENT -> msgCode = R.string.INVALID_ARGUMENT
                    INVALID_TOKEN -> msgCode = R.string.INVALID_TOKEN
                    EXPIRED_TOKEN -> msgCode = R.string.EXPIRED_TOKEN
                    REQUIRED_PARAMS_NOT_FOUND -> msgCode = R.string.REQUIRED_PARAMS_NOT_FOUND
                    FIELD_REQUIRED -> msgCode = R.string.FIELD_REQUIRED
                    NOT_REGISTERED -> msgCode = R.string.NOT_REGISTERED
                    INCORRECT_PASSWORD -> msgCode = R.string.INCORRECT_PASSWORD
                }
                return msgCode
            }?:run{
                return R.string.generic_error
            }
        }
    }
}