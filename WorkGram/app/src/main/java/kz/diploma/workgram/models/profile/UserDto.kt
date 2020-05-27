package kz.diploma.workgram.models.profile

import kz.diploma.workgram.models.BaseResponse

data class UserDto (
    var token: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var description: String? = null,
    var name: String? = null,
    var surname: String? = null,
    var nickname: String? = null,
    var rating_score: Double? = 0.0,
    var avatar: String? = null
): BaseResponse()