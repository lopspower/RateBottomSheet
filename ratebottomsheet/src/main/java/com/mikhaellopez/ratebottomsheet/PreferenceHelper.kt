package com.mikhaellopez.ratebottomsheet

import android.content.Context
import android.content.SharedPreferences
import java.util.*

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class PreferenceHelper(context: Context) {

    companion object {
        private const val PREF_FILE_NAME = "rate_bottom_sheet_pref"
        private const val PREF_INSTALL_DAYS = "pref_rate_install_days"
        private const val PREF_CPT_LAUNCH_TIMES = "pref_rate_cpt_launch_times"
        private const val PREF_IS_AGREE_SHOW_DIALOG = "pref_rate_is_agree_show_dialog"
        private const val PREF_REMIND_INTERVAL = "pref_rate_remind_interval"
    }

    private val sharedPreferences =
        context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    fun getInstallDays(): Long =
        sharedPreferences.getLong(PREF_INSTALL_DAYS, 0)

    fun setInstallDays() {
        sharedPreferences.put(PREF_INSTALL_DAYS, Date().time)
    }

    fun getCptLaunchTimes(): Int =
        sharedPreferences.getInt(PREF_CPT_LAUNCH_TIMES, 0)

    fun setCptLaunchTimes() {
        sharedPreferences.getInt(PREF_CPT_LAUNCH_TIMES, 0).also {
            sharedPreferences.put(PREF_CPT_LAUNCH_TIMES, it + 1)
        }
    }

    fun isAgreeShowDialog(): Boolean =
        sharedPreferences.getBoolean(PREF_IS_AGREE_SHOW_DIALOG, true)

    fun disableAgreeShowDialog() {
        sharedPreferences.put(PREF_IS_AGREE_SHOW_DIALOG, false)
    }

    fun getRemindInterval(): Long =
        sharedPreferences.getLong(PREF_REMIND_INTERVAL, 0)

    fun setRemindInterval() {
        sharedPreferences.put(PREF_REMIND_INTERVAL, Date().time)
    }

    //region Extensions
    private fun SharedPreferences.put(key: String, value: Boolean) {
        edit().putBoolean(key, value).apply()
    }

    private fun SharedPreferences.put(key: String, value: Int) {
        edit().putInt(key, value).apply()
    }

    private fun SharedPreferences.put(key: String, value: Long) {
        edit().putLong(key, value).apply()
    }

    private fun SharedPreferences.clear(key: String) {
        edit().remove(key).apply()
    }
    //endregion


}
