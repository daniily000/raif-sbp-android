package com.gitpub.raiffts.ui.offers.view.delegates

import com.gitpub.raiffts.R
import com.gitpub.raiffts.data.model.NewOfferCardItem
import com.gitpub.raiffts.ui.util.view.adapters.BaseAdapterDelegate

class NewOfferCardAdapterDelegate : BaseAdapterDelegate<NewOfferCardItem>(
    NewOfferCardItem::class.java,
    R.layout.view_new_offer_card
)