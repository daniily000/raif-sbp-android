package com.gitpub.raiffts.ui.new_order.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.model.CheckableOfferItem
import com.gitpub.raiffts.databinding.DialogChooseOrdersBinding
import com.gitpub.raiffts.ui.new_order.view.adapters.CheckableOfferAdapterDelegate
import com.gitpub.raiffts.ui.util.view.adapters.ItemListAdapter
import com.gitpub.raiffts.ui.util.view.adapters.VIEW_TYPE_CHECKABLE_OFFERS

class ChooseOrdersDialog(
    context: Context,
    var onAccept: (List<Order>) -> Unit = {}
) : Dialog(context) {

    var list: List<CheckableOfferItem> = listOf()
        set(value) {
            field = value
            updateList()
        }

    private lateinit var binding: DialogChooseOrdersBinding
    private val adapter = ItemListAdapter().apply {
        addDelegates(
            VIEW_TYPE_CHECKABLE_OFFERS to CheckableOfferAdapterDelegate()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewBinding()
        initializeControls()
        initializeOfferList()
    }

    fun setOffers(list: List<Order>) {
        this.list = list.map { CheckableOfferItem(it) }
    }

    private fun initializeViewBinding() {
        binding = DialogChooseOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeControls() {
        binding.apply {
            dialogAccept.setOnClickListener {
                dismiss()
                onAccept(list.filter { it.checked }.map { it.order })
            }
            dialogCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun initializeOfferList() {
        binding.apply {
            offerList.adapter = adapter
            offerList.layoutManager = LinearLayoutManager(root.context)
        }
    }

    private fun updateList() {
        adapter.setItemList(list)
    }

    class Builder(private val context: Context) {

        fun build() = ChooseOrdersDialog(context)
    }


}