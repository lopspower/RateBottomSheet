package com.mikhaellopez.ratebottomsheet

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

class RateBottomSheet : ABaseRateBottomSheet() {

    companion object {
        fun show(manager: FragmentManager) {
            RateBottomSheet().show(manager, "rateBottomSheet")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textRateBottomSheetTitle.text = getString(R.string.rate_popup_title)
        textRateBottomSheetMessage.text = getString(R.string.rate_popup_message)
        btnRateBottomSheetNo.text = getString(R.string.rate_popup_no)
        btnRateBottomSheetOk.text = getString(R.string.rate_popup_ok)

        btnRateBottomSheetOk.setOnClickListener {
            activity?.run { openStore(packageName) }
            dismiss()
        }
    }

    private fun Activity.openStore(appPackageName: String) {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (_: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }

}