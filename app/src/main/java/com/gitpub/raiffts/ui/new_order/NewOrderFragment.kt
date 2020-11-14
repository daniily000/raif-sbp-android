package com.gitpub.raiffts.ui.new_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gitpub.raiffts.databinding.FragmentNewOrderBinding
import com.gitpub.raiffts.ui.new_order.view.ChooseOrdersDialog
import com.gitpub.raiffts.ui.util.applyDatePicker
import org.joda.time.LocalDate

class NewOrderFragment : Fragment() {

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
        initializeControls()
        updateControls()
    }

    private fun initializeViewModel() {
        newOrderViewModel.apply {
            payerName.observe(viewLifecycleOwner) {
                updateControls()
            }
            payerNumber.observe(viewLifecycleOwner) {
                updateControls()
            }
            paymentDate.observe(viewLifecycleOwner) {
                updateControls()
            }
        }
    }

    private fun initializeControls() {
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
            attachOrder.setOnClickListener {
                val dialog = ChooseOrdersDialog.Builder(root.context).build()
                dialog.show()
            }
        }
    }

    private fun updateControls() {
        val payerName = newOrderViewModel.payerName.value
        val payerNumber = newOrderViewModel.payerNumber.value
        val paymentDate = newOrderViewModel.paymentDate.value

        val disabled = payerName.isNullOrBlank() ||
                payerNumber.isNullOrBlank() ||
                paymentDate == null

        binding.createPayment.isEnabled = !disabled
    }
}
