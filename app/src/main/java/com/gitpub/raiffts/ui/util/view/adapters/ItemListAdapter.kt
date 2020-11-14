package com.gitpub.raiffts.ui.util.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitpub.raiffts.data.model.BaseItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager


const val VIEW_TYPE_CHECKABLE_OFFERS = 0x00000000

class ItemListAdapter(items: List<BaseItem> = listOf()) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mItemList = mutableListOf<BaseItem>().apply {
        items?.forEach { this.add(it) }
    }
    private val mDelegatesManager = AdapterDelegatesManager<List<BaseItem>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        mDelegatesManager.onCreateViewHolder(parent, viewType)

    override fun getItemCount(): Int = mItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mDelegatesManager.onBindViewHolder(mItemList, position, holder)
    }

    fun addDelegate(viewType: Int, delegate: AdapterDelegate<List<BaseItem>>) {
        mDelegatesManager.addDelegate(viewType, delegate)
    }

    fun addDelegates(vararg delegates: Pair<Int, AdapterDelegate<List<BaseItem>>>) {
        delegates.forEach { mDelegatesManager.addDelegate(it.first, it.second) }
    }

    override fun getItemViewType(position: Int): Int {
        return mDelegatesManager.getItemViewType(mItemList, position)
    }

    fun setItemList(items: List<BaseItem>) {
        mItemList.clear()
        items.forEach { mItemList.add(it) }
        notifyDataSetChanged()
    }
}