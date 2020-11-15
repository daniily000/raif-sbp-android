package com.gitpub.raiffts.ui.history.view.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gitpub.raiffts.R
import com.gitpub.raiffts.data.model.BaseItem
import com.gitpub.raiffts.data.model.PaymentStatus
import com.gitpub.raiffts.data.model.SavedPaymentItem
import com.gitpub.raiffts.data.model.TextItem
import com.gitpub.raiffts.databinding.ViewSavedPaymentBinding
import com.gitpub.raiffts.service.PaymentService
import com.gitpub.raiffts.ui.new_order.view.adapters.TextAdapterDelegate
import com.gitpub.raiffts.ui.util.view.adapters.ItemListAdapter
import com.gitpub.raiffts.ui.util.view.adapters.VIEW_TYPE_TEXT
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import io.reactivex.rxjava3.disposables.Disposable

class SavedPaymentAdapterDelegate(private val paymentService: PaymentService) :
    AdapterDelegate<List<BaseItem>>() {

    override fun isForViewType(items: List<BaseItem>, position: Int) =
        items[position] is SavedPaymentItem

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(
        ViewSavedPaymentBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(
        items: List<BaseItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as? SavedPaymentItem ?: return
        val payment = item.payment.payments.first()
        val orders = item.payment.payments.map { it.order }
        (holder as? ViewHolder)?.binding?.apply {
            payerName.text = payment.payerName
            payerNumber.text = payment.payerNumber
            (orderNameList.adapter as? ItemListAdapter)
                ?.setItemList(orders.map { TextItem(it.orderName) })
            price.text = orders.map { it.orderCost }.reduce { acc, price -> acc + price }.toString()
            paymentDate.text = payment.paymentDate.toString("dd MMM yyyy")
            holder.currentRequest?.dispose()
            holder.currentRequest = paymentService.getPaymentStatus(item.payment.id) {
                when (it) {
                    PaymentStatus.NotPaidYet ->
                        paymentStatus.apply {
                            setText(R.string.not_paid_yet)
                            backgroundTintList = ColorStateList.valueOf(
                                ResourcesCompat.getColor(
                                    resources,
                                    R.color.colorBrand,
                                    null
                                )
                            )
                        }
                    PaymentStatus.PaymentComplete ->
                        paymentStatus.apply {
                            setText(R.string.payment_complete)
                            backgroundTintList = ColorStateList.valueOf(
                                ResourcesCompat.getColor(
                                    resources,
                                    R.color.colorOk,
                                    null
                                )
                            )
                        }
                    null -> TODO()
                }
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as? ViewHolder)?.apply { currentRequest?.dispose() }
    }

    private class ViewHolder(
        val binding: ViewSavedPaymentBinding,
        var currentRequest: Disposable? = null
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                orderNameList.apply {
                    layoutManager = LinearLayoutManager(root.context)
                    adapter = ItemListAdapter().apply {
                        addDelegates(
                            VIEW_TYPE_TEXT to TextAdapterDelegate()
                        )
                    }
                }

            }
        }
    }
}