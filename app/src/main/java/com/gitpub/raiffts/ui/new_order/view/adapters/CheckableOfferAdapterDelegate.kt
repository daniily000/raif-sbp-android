package com.gitpub.raiffts.ui.new_order.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gitpub.raiffts.R
import com.gitpub.raiffts.data.model.BaseItem
import com.gitpub.raiffts.data.model.CheckableOfferItem
import com.gitpub.raiffts.databinding.ViewCheckableOfferBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class CheckableOfferAdapterDelegate : AdapterDelegate<List<BaseItem>>() {

    private lateinit var binding: ViewCheckableOfferBinding

    override fun isForViewType(items: List<BaseItem>, position: Int): Boolean =
        items[position] is CheckableOfferItem

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewCheckableOfferBinding.inflate(LayoutInflater.from(parent.context)).let {
            ViewHolder(it)
        }

    override fun onBindViewHolder(
        items: List<BaseItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as? CheckableOfferItem ?: return
        (holder as? ViewHolder)?.binding?.apply {
            offerTitle.text = item.order.orderName
            offerPrice.text = String.format(
                root.context.getString(R.string.currency_template),
                item.order.orderCost.toString()
            )
            checkbox.apply {
                isChecked = item.checked
                isVisible = item.checkboxVisible
                setOnCheckedChangeListener { _, isChecked -> item.checked = isChecked }
            }
        }
    }

    private class ViewHolder(val binding: ViewCheckableOfferBinding) :
        RecyclerView.ViewHolder(binding.root)

}