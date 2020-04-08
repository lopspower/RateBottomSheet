package com.mikhaellopez.ratebottomsheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class AskRateBottomSheet(
    val listener: ActionListener? = null
) : ABaseRateBottomSheet() {

    /**
     * You can use this listener if you choose to setShowAskBottomSheet(true)
     * See .RateBottomSheet.ActionListener for more callbacks
     *
     * Each callback has an empty body, meaning that is optional
     */
    interface ActionListener : RateBottomSheet.ActionListener {
        /**
         * Will be called when a click on the "I don't like" button is triggered
         */
        fun onDislikeClickListener() {}
    }

    companion object {
        internal fun show(manager: FragmentManager, listener: ActionListener? = null) {
            AskRateBottomSheet(listener).show(manager, "askRateBottomSheet")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRateBottomSheetLater.visibility = View.GONE
        textRateBottomSheetTitle.text = getString(R.string.rate_popup_ask_title)
        textRateBottomSheetMessage.text = getString(R.string.rate_popup_ask_message)
        btnRateBottomSheetNo.text = getString(R.string.rate_popup_ask_no)
        btnRateBottomSheetOk.text = getString(R.string.rate_popup_ask_ok)

        btnRateBottomSheetOk.setOnClickListener {
            activity?.run { RateBottomSheet.show(supportFragmentManager, listener) }
            dismiss()
        }

        btnRateBottomSheetNo.setOnClickListener {
            defaultBtnNoClickAction(it)
            listener?.onDislikeClickListener()
        }
    }

}