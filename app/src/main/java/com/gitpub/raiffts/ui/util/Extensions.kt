package com.gitpub.raiffts.ui.util

import android.view.View
import com.gitpub.raiffts.data.model.SnackbarInfo
import com.gitpub.raiffts.util.withPredicate
import com.google.android.material.snackbar.Snackbar

fun SnackbarInfo.showSnackbarForView(view: View?) {
    view?.apply {
        Snackbar
            .make(this, message, length)
            .withPredicate { action != null }
            .mapIfTrue { it.setAction(actionText) { action?.invoke() } }
            .end()
            .apply { view.setOnClickListener { dismiss() } }
            .show()
    }
}
