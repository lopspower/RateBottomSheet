package com.mikhaellopez.ratepopupsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.ratepopup.RatePopup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowRate.setOnClickListener { RatePopup.create(this).show() }
    }

}