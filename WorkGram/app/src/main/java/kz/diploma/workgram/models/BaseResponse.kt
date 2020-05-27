package kz.diploma.workgram.models

import java.io.Serializable

open class BaseResponse: Serializable {
    var success: Boolean = false;
}