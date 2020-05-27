package kz.diploma.workgram.views.offers.ratings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_my_rating.view.*
import kotlinx.android.synthetic.main.item_offer.view.*
import kotlinx.android.synthetic.main.item_offer.view.title
import kz.diploma.workgram.R
import kz.diploma.workgram.models.offers.OfferDto
import kz.diploma.workgram.utils.paginator.OfferDiffUtilCallback

class RatingAdapter: PagedListAdapter<OfferDto, RecyclerView.ViewHolder>(OfferDiffUtilCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent.context)
        view = inflater.inflate(R.layout.item_my_rating, parent, false)
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
        val statusText = itemView.statusText
        val priceText = itemView.priceText
        val ratingScore = itemView.ratingScore

        fun bindOffer(item: OfferDto) {
            with(item) {
                title.text = name
                priceText.text = priceText.context.getString(R.string.price, price.toString())
                if (rating_score != null) {
                    ratingScore.rating = rating_score.toFloat()
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