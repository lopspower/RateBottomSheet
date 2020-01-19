package com.mikhaellopez.ratebottomsheet

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class RateBottomSheet : ABaseRateBottomSheet() {

    companion object {
        internal fun show(manager: FragmentManager) {
            RateBottomSheet().show(manager, "rateBottomSheet")
        }

        /**
         * Display rate bottom sheet if meets conditions.
         *
         * @param activity [AppCompatActivity]
         */
        fun showRateBottomSheetIfMeetsConditions(activity: AppCompatActivity) {
            showRateBottomSheetIfMeetsConditions(
                activity.applicationContext,
                activity.supportFragmentManager
            )
        }

        /**
         * Display rate bottom sheet if meets conditions.
         *
         * @param fragment [Fragment]
         */
        fun showRateBottomSheetIfMeetsConditions(fragment: Fragment) {
            (fragment.activity as? AppCompatActivity)?.also { showRateBottomSheetIfMeetsConditions(it) }
        }

        /**
         * Display rate bottom sheet if meets conditions.
         *
         * @param context [Context]
         * @param fragmentManager [FragmentManager]
         */
        fun showRateBottomSheetIfMeetsConditions(context: Context, fragmentManager: FragmentManager) {
            if (RateBottomSheetManager(context).shouldShowRateBottomSheet()) {
                if (RateBottomSheetManager.showAskBottomSheet) {
                    AskRateBottomSheet.show(fragmentManager)
                } else {
                    show(fragmentManager)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRateBottomSheetLater.visibility =
            if (RateBottomSheetManager.showLaterButton) View.VISIBLE else View.GONE

        textRateBottomSheetTitle.text = getString(R.string.rate_popup_title)
        textRateBottomSheetMessage.text = getString(R.string.rate_popup_message)
        btnRateBottomSheetNo.text = getString(R.string.rate_popup_no)
        btnRateBottomSheetLater.text = getString(R.string.rate_popup_later)
        btnRateBottomSheetOk.text = getString(R.string.rate_popup_ok)

        btnRateBottomSheetOk.setOnClickListener {
            activity?.run {
                openStore(packageName)
                RateBottomSheetManager(it.context).disableAgreeShowDialog()
            }
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