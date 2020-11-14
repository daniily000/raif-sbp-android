package com.gitpub.raiffts.ui.new_order.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitpub.raiffts.data.model.BaseItem
import com.gitpub.raiffts.data.model.TextItem
import com.gitpub.raiffts.databinding.ViewTextItemBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class TextAdapterDelegate : AdapterDelegate<List<BaseItem>>() {

    override fun isForViewType(items: List<BaseItem>, position: Int) = items[position] is TextItem

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(
        ViewTextItemBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(
        items: List<BaseItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as? TextItem ?: return
        (holder as? ViewHolder)?.apply {
            binding.text.text = item.text
        }
    }

    class ViewHolder(val binding: ViewTextItemBinding) : RecyclerView.ViewHolder(binding.root)
}