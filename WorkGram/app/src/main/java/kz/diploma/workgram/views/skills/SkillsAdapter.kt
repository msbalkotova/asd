package kz.diploma.workgram.views.skills

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.diploma.workgram.R
import kz.diploma.workgram.models.categories.CategoryDto

class SkillsAdapter (val categories: List<CategoryDto>) :
    RecyclerView.Adapter<SkillsAdapter.CategoryViewHolder>() {
    var onItemClick: ((CategoryDto) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        val res = holder.mainView.context.resources

        holder.title.text = item.name
        if (item.isSelected || item.have) {
            item.isSelected = true
            holder.mainView.background = res.getDrawable(R.drawable.view_rounded_selected)
            holder.title.setTextColor(res.getColor(R.color.snow))
        } else {
            holder.mainView.background =
                res.getDrawable(R.drawable.view_rounded_border)
            holder.title.setTextColor(res.getColor(R.color.snow))
        }

        holder.mainView.setOnClickListener {
            onItemClick.invoke(item)
            categories.forEach {
                if(it.id == item.id){
                    it.isSelected = !it.isSelected
                    item.have = !it.have
                    notifyItemChanged(categories.indexOf(it))
                }

            }
        }
    }

    inner class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tvTitle)
        var mainView: View = view.findViewById(R.id.ll_view)
    }
}