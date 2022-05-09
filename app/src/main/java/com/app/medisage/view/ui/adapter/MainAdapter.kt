package com.app.medisage.view.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.medisage.databinding.ItemBinding
import com.app.medisage.model.ItemModel
import com.app.medisage.utils.CellClickListener

class MainAdapter(private val cellClickListener: CellClickListener) : RecyclerView.Adapter<MainViewHolder>() {

    var items = mutableListOf<ItemModel>()

    fun setItemList(itemModels: List<ItemModel>) {
        this.items = itemModels.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvBody.text = "Body:- ${item.body}"
        holder.binding.tvTitle.text = "Title:- ${item.title}"
        holder.binding.tvId.text = "Id:- ${item.id}"
        holder.binding.tvUserid.text = "UserId:- ${item.userId}"
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

}