package com.gitpub.raiffts.ui.new_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.model.CheckableOfferItem
import com.gitpub.raiffts.databinding.FragmentNewOrderBinding
import com.gitpub.raiffts.service.DataService
import com.gitpub.raiffts.ui.new_order.view.ChooseOrdersDialog
import com.gitpub.raiffts.ui.new_order.view.adapters.CheckableOfferAdapterDelegate
import com.gitpub.raiffts.ui.util.applyDatePicker
import com.gitpub.raiffts.ui.util.view.adapters.ItemListAdapter
import com.gitpub.raiffts.ui.util.view.adapters.VIEW_TYPE_CHECKABLE_OFFERS
import org.joda.time.LocalDate
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class NewOrderFragment : Fragment(), DIAware {

    private val chosenOrdersAdapter = ItemListAdapter().apply {
        addDelegates(
            VIEW_TYPE_CHECKABLE_OFFERS to CheckableOfferAdapterDelegate()
        )
    }
    override val di by di()

    private val dataService: DataService by instance()

    private val newOrderViewModel: NewOrderViewModel by activityViewModels()
    private lateinit var binding: FragmentNewOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initializeViewBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
        initializeViewModel()
    }

    private fun initializeViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentNewOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializeUi() {
        initializeForm()
        updateForm()
    }

    private fun initializeViewModel() {
        newOrderViewModel.apply {
            payerName.observe(viewLifecycleOwner) {
                updateForm()
            }
            payerNumber.observe(viewLifecycleOwner) {
                updateForm()
            }
            paymentDate.observe(viewLifecycleOwner) {
                updateForm()
            }
            chosenOrders.observe(viewLifecycleOwner) {
                updateForm()
            }
        }
    }

    private fun initializeForm() {
        binding.apply {
            payerName.editText?.addTextChangedListener {
                newOrderViewModel.payerName.postValue(it.toString())
            }
            payerNumber.editText?.addTextChangedListener {
                newOrderViewModel.payerNumber.postValue(it.toString())
            }
            paymentDate.applyDatePicker(childFragmentManager) {
                newOrderViewModel.paymentDate.postValue(LocalDate(it))
            }
            attachOrders.setOnClickListener {
                val dialog = ChooseOrdersDialog(root.context).apply {
                    setOffers(dataService.getOrders())
                    onAccept = ::updateOrders
                }
                dialog.show()
            }
            reattachOrders.setOnClickListener {
                val dialog = ChooseOrdersDialog(root.context).apply {
                    setOffers(dataService.getOrders())
                    onAccept = ::updateOrders
                }
                dialog.show()
            }
            chosenOrdersList.apply {
                adapter = chosenOrdersAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun updateOrders(orders: List<Order>) {
        newOrderViewModel.chosenOrders.postValue(orders)
    }

    private fun updateForm() {
        val payerName = newOrderViewModel.payerName.value
        val payerNumber = newOrderViewModel.payerNumber.value
        val paymentDate = newOrderViewModel.paymentDate.value
        val chosenOrders = newOrderViewModel.chosenOrders.value

        val ordersEmpty = chosenOrders.isNullOrEmpty()
        val disabled = payerName.isNullOrBlank() ||
                payerNumber.isNullOrBlank() ||
                paymentDate == null ||
                ordersEmpty
        binding.createPayment.isEnabled = !disabled

        binding.apply {
            chosenOrdersContainer.isVisible = !ordersEmpty
            attachOrders.isVisible = ordersEmpty
        }

        if (!chosenOrders.isNullOrEmpty()) {
            val checkableOrders = chosenOrders.map {
                CheckableOfferItem(
                    it,
                    checked = true,
                    checkboxVisible = false
                )
            }
            chosenOrdersAdapter.setItemList(checkableOrders)

        }
    }
}
