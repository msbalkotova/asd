package kz.diploma.workgram.models.offers

import kz.diploma.workgram.models.BaseResponse
import kz.diploma.workgram.models.workers.WorkersPageDto

data class OfferResponse (
    val projects: OfferPageDto? = null
): BaseResponse()