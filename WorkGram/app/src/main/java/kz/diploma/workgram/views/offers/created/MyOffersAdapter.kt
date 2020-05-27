package kz.diploma.workgram.views.offers.created

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_offer.view.*
import kz.diploma.workgram.R
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.utils.paginator.OfferDiffUtilCallback

class MyOffersAdapter: PagedListAdapter<OfferDto, RecyclerView.ViewHolder>(OfferDiffUtilCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent.context)
        view = inflater.inflate(R.layout.item_offer, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            holder as ListViewHolder
            holder.bindOffer(it)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val statusText = itemView.status
        val categoryText = itemView.category
        val startTime = itemView.startTime
        val endTime = itemView.endTime

        fun bindOffer(item: OfferDto) {
            with(item) {
                title.text = name
                startTime.text = startTime.context.getString(R.string.start_time, start)
                endTime.text = endTime.context.getString(R.string.finish_time, finish)
                if (category != null) {
                    categoryText.text = category.name
                }
                when(item.status) {
                    2 -> {
                        statusText.text = statusText.context.getString(R.string.project_status,"Finished")
                    }
                    1 -> {
                        statusText.text = statusText.context.getString(R.string.project_status,"Process")
                    }
                    0 -> {
                        statusText.text = statusText.context.getString(R.string.project_status,"Created")
                    }
                }
            }
        }
    }
}