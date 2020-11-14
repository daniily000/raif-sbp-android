package com.gitpub.raiffts.data.model

import androidx.annotation.StringRes
import com.gitpub.raiffts.R
import com.google.android.material.snackbar.Snackbar

data class SnackbarInfo(
    @StringRes val message: Int,
    @StringRes val actionText: Int = R.string.retry,
    val action: (() -> Unit)? = null,
    val length: Int = if (action == null) Snackbar.LENGTH_LONG else Snackbar.LENGTH_INDEFINITE
) {
    class Builder(@StringRes val message: Int = R.string.error_check_connection_description) {
        @StringRes
        private var actionText: Int = R.string.retry
        private var action: (() -> Unit)? = null
        private var length: Int? = null

        fun withAction(action: (() -> Unit)): Builder {
            this.action = action
            return this
        }

        fun withActionText(@StringRes actionText: Int): Builder {
            this.actionText = actionText
            return this
        }

        fun withLength(length: Int): Builder {
            this.length = length
            return this
        }

        fun build() = SnackbarInfo(
            message,
            actionText,
            action,
            length ?: if (action == null) Snackbar.LENGTH_LONG else Snackbar.LENGTH_INDEFINITE
        )
    }
}