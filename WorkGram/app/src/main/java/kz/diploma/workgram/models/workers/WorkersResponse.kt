package kz.diploma.workgram.models.workers

import kz.diploma.workgram.models.BaseResponse

data class WorkersResponse (
    val users: WorkersPageDto? = null
): BaseResponse()