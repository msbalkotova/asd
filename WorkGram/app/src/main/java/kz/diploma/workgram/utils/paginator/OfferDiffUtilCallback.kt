package kz.diploma.workgram.utils.paginator

import androidx.recyclerview.widget.DiffUtil
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.models.workers.WorkerDto

class OfferDiffUtilCallback: DiffUtil.ItemCallback<OfferDto>() {
    override fun areItemsTheSame(oldItem: OfferDto, newItem: OfferDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OfferDto, newItem: OfferDto): Boolean {
        return oldItem.name == newItem.name
    }
}