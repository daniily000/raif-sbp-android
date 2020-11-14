package com.gitpub.raiffts.data.model

import com.gitpub.raiffts.data.entities.Order

class CheckableOfferItem(
    val order: Order,
    var checked: Boolean = false,
    val checkboxVisible: Boolean = true
) : BaseItem