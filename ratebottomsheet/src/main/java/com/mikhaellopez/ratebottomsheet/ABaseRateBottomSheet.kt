package com.mikhaellopez.ratebottomsheet

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
abstract class ABaseRateBottomSheet : BottomSheetDialogFragment() {

    protected val defaultNoClickListener = View.OnClickListener {
        RateBottomSheetManager(it.context).disableAgreeShowDialog()
        dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.rate_bottom_sheet_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRateBottomSheetCancel.visibility =
            if (RateBottomSheetManager.showLaterButton) View.VISIBLE else View.GONE

        context?.also {
            btnRateBottomSheetOk.backgroundTintList =
                ColorStateList.valueOf(getThemeAccentColor(it))
        }

        btnRateBottomSheetCancel.setOnClickListener { dismiss() }
        btnRateBottomSheetNo.setOnClickListener(defaultNoClickListener)
        btnRateBottomSheetLater.setOnClickListener {
            RateBottomSheetManager(it.context).setRemindInterval()
            dismiss()
        }
    }

    private fun getThemeAccentColor(context: Context): Int {
        val colorAttr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            android.R.attr.colorAccent
        } else { // Get colorAccent defined for AppCompat
            context.resources.getIdentifier("colorAccent", "attr", context.packageName)
        }
        val outValue = TypedValue()
        context.theme.resolveAttribute(colorAttr, outValue, true)
        return outValue.data
    }

}