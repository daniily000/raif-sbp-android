package com.gitpub.raiffts.ui.new_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gitpub.raiffts.databinding.FragmentNewOrderBinding

class NewOrderFragment : Fragment() {

    private lateinit var newOrderViewModel: NewOrderViewModel
    private lateinit var binding: FragmentNewOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newOrderViewModel = ViewModelProvider(this).get(NewOrderViewModel::class.java)
        binding = FragmentNewOrderBinding.inflate(inflater, container, false)
        return binding.root
    }
}