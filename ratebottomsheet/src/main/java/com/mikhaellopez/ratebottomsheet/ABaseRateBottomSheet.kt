package com.mikhaellopez.ratebottomsheet

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.rate_bottom_sheet_layout.*

abstract class ABaseRateBottomSheet: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.rate_bottom_sheet_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.also {
            btnRateBottomSheetNo.setTextColor(getThemeAccentColor(it))
            btnRateBottomSheetOk.setTextColor(getThemeAccentColor(it))
        }

        btnRateBottomSheetCancel.setOnClickListener { dismiss() }
        btnRateBottomSheetNo.setOnClickListener { dismiss() }
    }

    private fun getThemeAccentColor(context: Context): Int {
        val colorAttr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            android.R.attr.colorAccent
        } else { //Get colorAccent defined for AppCompat
            context.resources.getIdentifier("colorAccent", "attr", context.packageName)
        }
        val outValue = TypedValue()
        context.theme.resolveAttribute(colorAttr, outValue, true)
        return outValue.data
    }

}