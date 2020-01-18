package com.mikhaellopez.ratebottomsheet

import android.content.Context
import android.util.Log
import java.util.*

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class RateBottomSheetManager(context: Context) {

    companion object {
        var installDays: Int = 3
            private set
        var launchTimes: Int = 5
            private set
        var remindInterval: Int = 2
            private set
        var showAskBottomSheet: Boolean = true
            private set
        var showLaterButton: Boolean = true
            private set
        var showCloseButtonIcon: Boolean = true
            private set
        var debugForceOpenEnable: Boolean = false
            private set
        var debugLogEnable: Boolean = false
            private set
    }

    private val preferenceHelper = PreferenceHelper(context)

    internal fun shouldShowRateDialog(): Boolean {
        val isAgreeShowDialog = preferenceHelper.isAgreeShowDialog()
        val isOverInstallDays = preferenceHelper.getInstallDays().isOverDate(installDays)
        val isOverLaunchTimes = preferenceHelper.getCptLaunchTimes() >= launchTimes
        val isOverRemindInterval = preferenceHelper.getRemindInterval().isOverDate(remindInterval)

        if (debugLogEnable) {
            Log.d(
                "RateBottomSheetManager",
                " \nRateBottomSheet Conditions:\n" +
                        "- isAgreeShowDialog = $isAgreeShowDialog\n" +
                        "- isOverLaunchTimes = $isOverLaunchTimes : cptLaunchTimes = ${preferenceHelper.getCptLaunchTimes()}\n" +
                        "- isOverInstallDays = $isOverInstallDays : " +
                        "${preferenceHelper.getInstallDays().showOverCondition(installDays)})\n" +
                        "- isOverRemindInterval = $isOverRemindInterval : " +
                        preferenceHelper.getRemindInterval().showOverCondition(remindInterval)
            )
        }

        return debugForceOpenEnable
                || (isAgreeShowDialog && isOverLaunchTimes
                && isOverInstallDays && isOverRemindInterval)
    }

    internal fun setRemindInterval() {
        preferenceHelper.setRemindInterval()
    }

    internal fun disableAgreeShowDialog() {
        preferenceHelper.disableAgreeShowDialog()
    }

    //region SET PUBLIC
    fun setInstallDays(installDays: Int): RateBottomSheetManager =
        apply { RateBottomSheetManager.installDays = installDays }

    fun setLaunchTimes(launchTimes: Int): RateBottomSheetManager =
        apply { RateBottomSheetManager.launchTimes = launchTimes }

    fun setRemindInterval(remindInterval: Int): RateBottomSheetManager =
        apply { RateBottomSheetManager.remindInterval = remindInterval }

    fun setShowAskBottomSheet(showAskBottomSheet: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.showAskBottomSheet = showAskBottomSheet }

    fun setShowLaterButton(showLaterButton: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.showLaterButton = showLaterButton }

    fun setShowCloseButtonIcon(showCloseButtonIcon: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.showCloseButtonIcon = showCloseButtonIcon }

    fun setDebugForceOpenEnable(debugForceOpenEnable: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.debugForceOpenEnable = debugForceOpenEnable }

    fun setDebugLogEnable(debugLogEnable: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.debugLogEnable = debugLogEnable }

    fun monitor() {
        if (preferenceHelper.getInstallDays() == 0L) {
            preferenceHelper.setInstallDays()
        }
        preferenceHelper.setCptLaunchTimes()
    }
    //endregion

    //region UTILS
    private fun Long.isOverDate(threshold: Int): Boolean =
        Date().time - this >= threshold * 24 * 60 * 60 * 1000

    private fun Long.showOverCondition(threshold: Int): String =
        "${(Date().time - this) / (60 * 60 * 24 * 1000)} >= $threshold " +
                "-> ${Date().time} (now) - $this (condition) = ${Date().time - this} " +
                ">= ${threshold * 24 * 60 * 60 * 1000} (config)"
    //endregion

}