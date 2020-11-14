package com.gitpub.raiffts.ui.new_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gitpub.raiffts.R

class NewOrderFragment : Fragment() {

    private lateinit var newOrderViewModel: NewOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newOrderViewModel = ViewModelProvider(this).get(NewOrderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new_order, container, false)
        val textView: TextView = root.findViewById(R.id.text_new_order)
        newOrderViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}