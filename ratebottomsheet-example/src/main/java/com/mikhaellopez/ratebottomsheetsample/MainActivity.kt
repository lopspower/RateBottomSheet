package com.mikhaellopez.ratebottomsheetsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.ratebottomsheet.AskRateBottomSheet
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

        // The listener is optional, as well as each single callback:
        // you can choose which one you want to override
        val actionListener = object : AskRateBottomSheet.ActionListener {
            override fun onDislikeClickListener() {
                Toast.makeText(
                    this@MainActivity,
                    "Oh... that's sad! :(",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onRateClickListener() {
                Toast.makeText(
                    this@MainActivity,
                    "Great choice! ♡",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNoClickListener() {
                Toast.makeText(
                    this@MainActivity,
                    "Well... at least you like it, I'm ok with that",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Show bottom sheet if meets conditions
        RateBottomSheet.showRateBottomSheetIfMeetsConditions(this)

        btnShowRate.setOnClickListener {
            RateBottomSheetManager(this)
                .setDebugForceOpenEnable(true) // False by default
            RateBottomSheet.showRateBottomSheetIfMeetsConditions(
                activity = this,
                listener = actionListener
            )
        }
    }

}