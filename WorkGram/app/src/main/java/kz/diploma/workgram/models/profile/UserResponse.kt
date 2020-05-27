package kz.diploma.workgram.models.profile

import kz.diploma.workgram.models.BaseResponse

data class UserResponse (
    val profile: UserDto? = null
) : BaseResponse()