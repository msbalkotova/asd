package kz.diploma.workgram.models.offers

import kz.diploma.workgram.models.categories.CategoryDto

data class OfferPageDto(
    val current_page: Int? = null,
    val data: List<OfferDto>? = null,
    val first_page_url: String? = null,
    val from: Int? = null,
    val last_page: Int? = null,
    val last_page_url: String? = null,
    val next_page_url: String? = null,
    val path: String? = null,
    val per_page: Int? = null,
    val prev_page_url: String? = null,
    val to: Int? = null,
    val total: Int? = null
)