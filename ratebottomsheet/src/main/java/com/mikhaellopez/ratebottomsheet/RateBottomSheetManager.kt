package com.mikhaellopez.ratebottomsheet

import android.content.Context
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class RateBottomSheetManager(context: Context) {

    companion object {
        var installDays: Int = 10
        var launchTimes: Int = 10
        var remindInterval: Int = 2
        var showLaterButton: Boolean = true
        var debugForceOpenEnable: Boolean = false
        var debugLogEnable: Boolean = false
    }

    private val preferenceHelper = PreferenceHelper(context)

    internal fun shouldShowRateDialog(): Boolean {

        val isAgreeShowDialog = preferenceHelper.isAgreeShowDialog()
        val isOverLaunchTimes = preferenceHelper.getCptLaunchTimes() >= launchTimes
        val isOverInstallDays = isOverDate(preferenceHelper.getInstallDays(), installDays)
        val isOverRemindInterval = isOverDate(preferenceHelper.getRemindInterval(), remindInterval)

        if (debugLogEnable) {
            Log.d(
                "RateBottomSheetManager",
                "isAgreeShowDialog=$isAgreeShowDialog" +
                        " / isOverLaunchTimes=$isOverLaunchTimes (cptLaunchTimes=${preferenceHelper.getCptLaunchTimes()})" +
                        " / isOverInstallDays=$isOverInstallDays (nbInstallDays=${preferenceHelper.getInstallDays().nbDaysSinceToday()})" +
                        " / isOverRemindInterval=$isOverRemindInterval"
            )
        }

        return debugForceOpenEnable || (isAgreeShowDialog
                && isOverLaunchTimes
                && isOverInstallDays
                && isOverRemindInterval)
    }


    fun monitor() {
        preferenceHelper.setCptLaunchTimes()
    }

    //region SET PUBLIC
    fun setInstallDays(installDays: Int): RateBottomSheetManager {
        RateBottomSheetManager.installDays = installDays
        return this
    }

    fun setLaunchTimes(launchTimes: Int): RateBottomSheetManager {
        RateBottomSheetManager.launchTimes = launchTimes
        return this
    }

    fun setRemindInterval(remindInterval: Int): RateBottomSheetManager {
        RateBottomSheetManager.remindInterval = remindInterval
        return this
    }

    fun setShowLaterButton(showLaterButton: Boolean): RateBottomSheetManager {
        RateBottomSheetManager.showLaterButton = showLaterButton
        return this
    }

    fun setDebugForceOpenEnable(debugForceOpenEnable: Boolean): RateBottomSheetManager {
        RateBottomSheetManager.debugForceOpenEnable = debugForceOpenEnable
        return this
    }

    fun setDebugLogEnable(debugLogEnable: Boolean): RateBottomSheetManager {
        RateBottomSheetManager.debugLogEnable = debugLogEnable
        return this
    }
    //endregion

    //region UTILS
    private fun isOverDate(targetDate: Long, threshold: Int): Boolean =
        Date().time - targetDate >= threshold * 24 * 60 * 60 * 1000

    private fun Long.nbDaysSinceToday(): Long =
        TimeUnit.DAYS.convert(Date().time - this, TimeUnit.MILLISECONDS)
    //endregion

}