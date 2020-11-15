package com.gitpub.raiffts.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gitpub.raiffts.data.model.SavedPaymentItem
import com.gitpub.raiffts.databinding.FragmentHistoryBinding
import com.gitpub.raiffts.service.DataService
import com.gitpub.raiffts.service.PaymentService
import com.gitpub.raiffts.ui.history.view.adapters.SavedPaymentAdapterDelegate
import com.gitpub.raiffts.ui.util.view.adapters.ItemListAdapter
import com.gitpub.raiffts.ui.util.view.adapters.VIEW_TYPE_SAVED_PAYMENT
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class HistoryFragment : Fragment(), DIAware {

    override val di by di()

    private val dataService: DataService by instance()
    private val paymentService: PaymentService by instance()

    private lateinit var binding: FragmentHistoryBinding
    private val mHistoryAdapter by lazy {
        ItemListAdapter().apply {
            addDelegates(
                VIEW_TYPE_SAVED_PAYMENT to SavedPaymentAdapterDelegate(paymentService)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initializeViewBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
    }

    private fun initializeViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHistoryBinding.inflate(inflater, container, false).let {
            binding = it
            binding.root
        }

    private fun initializeUi() {
        binding.apply {
            paymentList.adapter = mHistoryAdapter
            paymentList.layoutManager = LinearLayoutManager(root.context)
            mHistoryAdapter.setItemList(
                dataService
                    .getPaymentsHistory()
                    .map { SavedPaymentItem(it) }
            )
        }
    }

}