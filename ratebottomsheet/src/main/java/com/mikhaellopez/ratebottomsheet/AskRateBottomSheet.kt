package com.mikhaellopez.ratebottomsheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

class AskRateBottomSheet : ABaseRateBottomSheet() {

    companion object {
        fun show(manager: FragmentManager) {
            AskRateBottomSheet().show(manager, "askRateBottomSheet")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textRateBottomSheetTitle.text = getString(R.string.rate_popup_ask_title)
        textRateBottomSheetMessage.text = getString(R.string.rate_popup_ask_message)
        btnRateBottomSheetNo.text = getString(R.string.rate_popup_ask_no)
        btnRateBottomSheetOk.text = getString(R.string.rate_popup_ask_ok)

        btnRateBottomSheetOk.setOnClickListener {
            activity?.run { RateBottomSheet.show(supportFragmentManager) }
            dismiss()
        }
    }

}