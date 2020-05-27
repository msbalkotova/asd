package kz.diploma.workgram.views.workers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_worker.view.*
import kz.diploma.workgram.R
import kz.diploma.workgram.models.workers.WorkerDto
import kz.diploma.workgram.utils.paginator.WorkerDiffUtilCallback
import kz.diploma.workgram.utils.setImage

class WorkersAdapter() : PagedListAdapter<WorkerDto, RecyclerView.ViewHolder>(WorkerDiffUtilCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent.context)
        view = inflater.inflate(R.layout.item_worker, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            holder as ListViewHolder
            holder.bindWorker(it)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName = itemView.fullName
        val avatar = itemView.avatar
        val rating_bar = itemView.rating
        val phoneText = itemView.phoneText

        fun bindWorker(item: WorkerDto) {
            with(item) {
                fullName.text = fullName.context.getString(R.string.fullName, last_name, first_name, description)
                phoneText.text = phone
                image_path?.let {
                    avatar.setImage(it)
                }
                rating_score?.let {
                        rating_bar.rating = it.toFloat()
                }
            }
        }
    }
}