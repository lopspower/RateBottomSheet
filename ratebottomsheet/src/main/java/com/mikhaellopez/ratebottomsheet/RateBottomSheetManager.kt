package com.mikhaellopez.ratebottomsheet

import android.content.Context
import android.util.Log
import java.util.*

/**
 * Copyright (C) 2020 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 * Manage all properties for the conditions
 * to display the rate bottom sheet
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

    internal fun shouldShowRateBottomSheet(): Boolean {
        val isAgreeShowBottomSheet = preferenceHelper.isAgreeShowBottomSheet()
        val isOverInstallDays = preferenceHelper.getInstallDays().isOverDate(installDays)
        val isOverLaunchTimes = preferenceHelper.getCptLaunchTimes() >= launchTimes
        val isOverRemindInterval = preferenceHelper.getRemindInterval().isOverDate(remindInterval)

        if (debugLogEnable) {
            Log.d(
                "RateBottomSheetManager",
                " \nRateBottomSheet Conditions:\n" +
                        "- isAgreeShowBottomSheet = $isAgreeShowBottomSheet\n" +
                        "- isOverLaunchTimes = $isOverLaunchTimes : cptLaunchTimes = ${preferenceHelper.getCptLaunchTimes()}\n" +
                        "- isOverInstallDays = $isOverInstallDays : " +
                        "${preferenceHelper.getInstallDays().showOverCondition(installDays)})\n" +
                        "- isOverRemindInterval = $isOverRemindInterval : " +
                        preferenceHelper.getRemindInterval().showOverCondition(remindInterval)
            )
        }

        return debugForceOpenEnable
                || (isAgreeShowBottomSheet && isOverLaunchTimes
                && isOverInstallDays && isOverRemindInterval)
    }

    internal fun setRemindInterval() {
        preferenceHelper.setRemindInterval()
    }

    internal fun disableAgreeShowDialog() {
        preferenceHelper.disableAgreeShowBottomSheet()
    }

    //region SET PUBLIC
    /**
     * Set nb days needed to show rate bottom sheet.
     * 3 days needed by default.
     *
     * @param installDays [Int]
     * @return [RateBottomSheetManager]
     */
    fun setInstallDays(installDays: Int): RateBottomSheetManager =
        apply { RateBottomSheetManager.installDays = installDays }

    /**
     * Set nb launch needed to show rate bottom sheet.
     * 5 launch needed by default.
     *
     * @param launchTimes [Int]
     * @return [RateBottomSheetManager]
     */
    fun setLaunchTimes(launchTimes: Int): RateBottomSheetManager =
        apply { RateBottomSheetManager.launchTimes = launchTimes }

    /**
     * Set nb days to recall rate bottom sheet.
     * 2 days by default.
     *
     * @param remindInterval [Int]
     * @return [RateBottomSheetManager]
     */
    fun setRemindInterval(remindInterval: Int): RateBottomSheetManager =
        apply { RateBottomSheetManager.remindInterval = remindInterval }

    /**
     * Display the first bottom sheet to filter users who like the app.
     * true by default.
     *
     * @param showAskBottomSheet [Boolean]
     * @return [RateBottomSheetManager]
     */
    fun setShowAskBottomSheet(showAskBottomSheet: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.showAskBottomSheet = showAskBottomSheet }

    /**
     * Display the later button.
     * true by default.
     *
     * @param showLaterButton [Boolean]
     * @return [RateBottomSheetManager]
     */
    fun setShowLaterButton(showLaterButton: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.showLaterButton = showLaterButton }

    /**
     * Display the close icon button on bottom sheet.
     * true by default.
     *
     * @param showCloseButtonIcon [Boolean]
     * @return [RateBottomSheetManager]
     */
    fun setShowCloseButtonIcon(showCloseButtonIcon: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.showCloseButtonIcon = showCloseButtonIcon }

    /**
     * Enable debug mode to ignore conditions to show the rate bottom sheet.
     * false by default.
     *
     * @param debugForceOpenEnable [Boolean]
     * @return [RateBottomSheetManager]
     */
    fun setDebugForceOpenEnable(debugForceOpenEnable: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.debugForceOpenEnable = debugForceOpenEnable }

    /**
     * Display logs to debug the conditions.
     * false by default.
     *
     * @param debugLogEnable [Boolean]
     * @return [RateBottomSheetManager]
     */
    fun setDebugLogEnable(debugLogEnable: Boolean): RateBottomSheetManager =
        apply { RateBottomSheetManager.debugLogEnable = debugLogEnable }

    /**
     * Clear all data conditions:
     * installDays, cptLaunchTimes, isAgreeShowBottomSheet, remindInterval.
     *
     * @return [RateBottomSheetManager]
     */
    fun clear(): RateBottomSheetManager =
        apply { preferenceHelper.clear() }

    /**
     * Record actions of users for the display conditions.
     */
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