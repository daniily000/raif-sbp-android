package com.gitpub.raiffts.ui.new_order.view_order

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gitpub.raiffts.R
import com.gitpub.raiffts.data.model.TextItem
import com.gitpub.raiffts.databinding.FragmentViewPaymentBinding
import com.gitpub.raiffts.ui.new_order.view.adapters.TextAdapterDelegate
import com.gitpub.raiffts.ui.util.showSnackbarForView
import com.gitpub.raiffts.ui.util.view.adapters.ItemListAdapter
import com.gitpub.raiffts.ui.util.view.adapters.VIEW_TYPE_TEXT
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class ViewPaymentFragment : Fragment() {

    private val navArgs by navArgs<ViewPaymentFragmentArgs>()

    private lateinit var binding: FragmentViewPaymentBinding
    private lateinit var viewPaymentViewModel: ViewPaymentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initializeViewBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPaymentViewModel = ViewPaymentViewModel(view.context)
        initializeUi()
        updateUi()
        initializeViewModel()
        createPaymentLink()
    }

    private fun initializeViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentViewPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializeUi() {

        val payments = navArgs.payments.toList()
        val payerName = payments.first().payerName
        val payerNumber = payments.first().payerNumber
        val paymentDate = payments.first().paymentDate
        val paymentPrice = payments.map { it.order.orderCost }.reduce { acc, price -> acc + price }

        val orderNames = payments.map { TextItem(it.order.orderName) }

        val adapter = ItemListAdapter().apply {
            addDelegates(
                VIEW_TYPE_TEXT to TextAdapterDelegate()
            )
        }

        binding.apply {
            this.payerName.text = payerName
            this.payerNumber.text = payerNumber
            orderNameList.adapter = adapter
            orderNameList.layoutManager = LinearLayoutManager(root.context)
            adapter.setItemList(orderNames)
            this.paymentDate.text = paymentDate.toString("dd MMM yyyy")
            price.text = String.format(
                root.context.getString(R.string.currency_template),
                paymentPrice.toString()
            )
            shareLinks.setOnClickListener {
                val links = viewPaymentViewModel.links.value ?: return@setOnClickListener
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, links.createMessage())
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    private fun initializeViewModel() {
        viewPaymentViewModel.apply {
            links.observe(viewLifecycleOwner) {
                updateUi()
            }
            snackbarChannel
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { info -> info.showSnackbarForView(binding.root) }
        }
    }

    private fun updateUi() {
        val loading = viewPaymentViewModel.links.value == null
        binding.apply {
            progressLoading.isVisible = loading
            iconComplete.isVisible = !loading
            shareLinks.isEnabled = !loading
        }
    }

    private fun createPaymentLink() {
        viewPaymentViewModel.sendRequest(navArgs.payments.toList())
    }
}
