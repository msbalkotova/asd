package kz.diploma.workgram.models.workers

import java.util.*

data class WorkerDto(
    var email: String? = null,
    var first_name: String? = null,
    var description: String? = null,
    var last_name: String? = null,
    var nickname: String? = null,
    var phone: String? = null,
    var image_path: String? = null,
    var rating_score: Double? = 0.0,
    var id: Int? = null,
    var created_at: String? = null,
    val deleted_at: String? = null,
    val updated_at: String? = null
)