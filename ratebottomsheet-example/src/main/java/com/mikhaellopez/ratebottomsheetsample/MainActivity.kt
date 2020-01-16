package com.mikhaellopez.ratebottomsheetsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.ratebottomsheet.AskRateBottomSheet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowRate.setOnClickListener { AskRateBottomSheet.show(supportFragmentManager) }
    }

}