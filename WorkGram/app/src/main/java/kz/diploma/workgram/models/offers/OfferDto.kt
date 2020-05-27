package kz.diploma.workgram.models.offers

import kz.diploma.workgram.models.categories.CategoryDto
import kz.diploma.workgram.models.workers.WorkerDto
import java.io.Serializable

data class OfferDto(
    var id: Int? = null,
    var created_at: String? = null,
    val deleted_at: String? = null,
    val updated_at: String? = null,
    val name: String? = null,
    val description: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val status: Int? = null,
    val price: Double? = 0.0,
    val rating_score: Double? = 0.0,
    val start: String? = null,
    val finish: String? = null,
    val creator: WorkerDto? = null,
    val implementer: WorkerDto? = null,
    val category: CategoryDto? = null
    ): Serializable