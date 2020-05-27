package kz.diploma.workgram.views.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.diploma.workgram.databinding.ItemSettingBinding
import kz.diploma.workgram.models.settings.SettingDto
import kz.diploma.workgram.views.ItemSelector

class SettingsAdapter (val infoList: ArrayList<SettingDto>): RecyclerView.Adapter<SettingsAdapter.ViewHolder>(),
    ItemSelector {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(infoList[position], this)
    }

    override fun <T> onItemSelected(item: T) {
        infoList.forEachIndexed { index, info ->
            if(info.id == (item as SettingDto).id){
                info.isSelected = !info.isSelected!!
                notifyItemChanged(index)

                return@forEachIndexed
            }
        }
    }

    inner class ViewHolder(val binding: ItemSettingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: SettingDto, selector: ItemSelector){
            binding.item = item
            binding.selector = selector
        }
    }
}