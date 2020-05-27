package kz.diploma.workgram.models.errors

import kz.diploma.workgram.models.BaseResponse

data class ErrorResponse (
    val type: String?,
    val errorCode: Int?,
    val errors: List<String>
): BaseResponse()