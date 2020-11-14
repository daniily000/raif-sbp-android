package com.gitpub.raiffts.ui.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gitpub.raiffts.R

class OffersFragment : Fragment() {

    private lateinit var offersViewModel: OffersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        offersViewModel = ViewModelProvider(this).get(OffersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_offers, container, false)
        val textView: TextView = root.findViewById(R.id.text_offers)
        offersViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}