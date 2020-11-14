package com.gitpub.raiffts.ui.util

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import org.joda.time.Instant
import org.joda.time.format.DateTimeFormat
import java.util.*


fun TextInputLayout.applyDatePicker(
    fragmentManager: FragmentManager,
    locale: Locale = Locale.getDefault(),
    pattern: String = "dd MMMM yyyy",
    onDateChosen: (epochMilli: Long) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker().build().apply {
        addOnPositiveButtonClickListener {
            onDateChosen(it)
            editText?.setText(
                DateTimeFormat.forPattern("dd MMMM yyyy").print(Instant.ofEpochMilli(it))
            )
        }
    }
    val pickerTag = "DatePicker"
    setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            editText?.requestFocus()
        }
    }
    editText?.apply {
        setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                picker.show(fragmentManager, pickerTag)
            }
        }
        setOnClickListener {
            picker.show(fragmentManager, pickerTag)
        }
    }
}