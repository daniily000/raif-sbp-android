package com.gitpub.raiffts.ui.new_order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.entities.Payment
import com.gitpub.raiffts.databinding.FragmentPickStudentBinding
import com.gitpub.raiffts.ui.new_order.ChosenOrderForm
import org.joda.time.LocalDate

class PickStudentFragment(
    var onItemClicked: (() -> Unit)? = null
) : Fragment() {

    private lateinit var binding: FragmentPickStudentBinding

    private val hardcodedPayments = listOf(
        listOf(
            Payment.create(
                "Леха с завода",
                "8 800 555 35 35",
                LocalDate.now(),
                Order.create("Фонд Хабенского", 1)
            )
        ),
        listOf(
            Payment.create(
                "Моя маман",
                "+7 902 252 06 39",
                LocalDate.now(),
                Order.create("Фонд Хабенского", 5)
            )
        ),
        listOf(
            Payment.create(
                "Красивая Лиля",
                "+7 963 343 25 61",
                LocalDate.now(),
                Order.create("Фонд Хабенского", 10)
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = initializeViewBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
    }

    private fun initializeViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentPickStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializeUi() {
        binding.apply {
            student1.setOnClickListener {
                ChosenOrderForm.payments = hardcodedPayments[0]
                onItemClicked?.invoke()
            }
            student2.setOnClickListener {
                ChosenOrderForm.payments = hardcodedPayments[1]
                onItemClicked?.invoke()
            }
            student3.setOnClickListener {
                ChosenOrderForm.payments = hardcodedPayments[2]
                onItemClicked?.invoke()
            }
        }
    }
}