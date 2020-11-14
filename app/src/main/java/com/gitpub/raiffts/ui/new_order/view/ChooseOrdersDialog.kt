package com.gitpub.raiffts.ui.new_order.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.gitpub.raiffts.databinding.DialogChooseOrdersBinding

class ChooseOrdersDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogChooseOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewBinding()
    }

    private fun initializeViewBinding() {
        binding = DialogChooseOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    class Builder(private val context: Context) {

        fun build() = ChooseOrdersDialog(context)
    }


}