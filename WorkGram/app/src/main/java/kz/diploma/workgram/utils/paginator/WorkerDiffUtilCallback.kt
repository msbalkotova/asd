package kz.diploma.workgram.utils.paginator

import androidx.recyclerview.widget.DiffUtil
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.models.workers.WorkerDto

class WorkerDiffUtilCallback: DiffUtil.ItemCallback<WorkerDto>() {
    override fun areItemsTheSame(oldItem: WorkerDto, newItem: WorkerDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WorkerDto, newItem: WorkerDto): Boolean {
        return oldItem.email == newItem.email
    }
}