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
            .setInstallDays(1) // 3 by default
            .setLaunchTimes(2) // 5 by default
            .setRemindInterval(1) // 2 by default
            .setShowAskBottomSheet(true) // True by default
            .setShowLaterButton(true) // True by default
            .setShowCloseButtonIcon(true) // True by default
            .setDebugLogEnable(true) // False by default
            .monitor()

        // Show bottom sheet if meets conditions
        RateBottomSheet.showRateBottomSheetIfMeetsConditions(this)

        btnShowRate.setOnClickListener {
            RateBottomSheetManager(this)
                .setDebugForceOpenEnable(true) // False by default
            RateBottomSheet.showRateBottomSheetIfMeetsConditions(this)
        }
    }

}