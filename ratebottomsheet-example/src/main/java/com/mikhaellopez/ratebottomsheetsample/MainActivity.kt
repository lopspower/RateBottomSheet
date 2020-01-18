package com.mikhaellopez.ratebottomsheetsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.ratebottomsheet.RateBottomSheet
import com.mikhaellopez.ratebottomsheet.RateBottomSheetManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RateBottomSheetManager(this)
            .setInstallDays(0)
            .setLaunchTimes(0)
            .setRemindInterval(0)
            .setDebugLogEnable(true)
            .monitor()

        RateBottomSheet.showRateDialogIfMeetsConditions(this)

        btnShowRate.setOnClickListener { RateBottomSheet.showRateDialogIfMeetsConditions(this) }
    }

}