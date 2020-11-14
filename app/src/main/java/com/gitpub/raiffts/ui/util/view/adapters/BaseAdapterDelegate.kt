package com.gitpub.raiffts.ui.util.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gitpub.raiffts.data.model.BaseItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

open class BaseAdapterDelegate<T : BaseItem>(
    private val itemType: Class<T>,
    @LayoutRes private val layoutRes: Int
) : AdapterDelegate<List<BaseItem>>() {

    protected open fun onViewHolderInit(view: View) {}
    protected open fun onBindViewHolder(item: T, holder: BaseAdapterDelegate<T>.ViewHolder) {}

    override fun isForViewType(items: List<BaseItem>, position: Int): Boolean =
        itemType.isInstance(items[position])

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(layoutRes, parent, false)
        )

    override fun onBindViewHolder(
        items: List<BaseItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        run {
            try {
                val item = items[position] as? T ?: return@run
                val castHolder = holder as? BaseAdapterDelegate<T>.ViewHolder ?: return@run
                onBindViewHolder(item, castHolder)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    open inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            onViewHolderInit(view)
        }
    }

}