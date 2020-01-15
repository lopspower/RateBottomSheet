package com.mikhaellopez.ratepopup

import android.app.AlertDialog
import android.content.Context

class RatePopup(context: Context) {

    companion object {
        fun create(context: Context): RatePopup = RatePopup(context)
    }

    init {
        init(context)
    }

    private lateinit var askDialogBuilder: AlertDialog.Builder
    private lateinit var rateDialogBuilder: AlertDialog.Builder

    private fun init(context: Context) {
        askDialogBuilder = AlertDialog.Builder(context)
        rateDialogBuilder = AlertDialog.Builder(context)

        setAskTitle(context.getString(R.string.rate_popup_ask_title))
        setAskMessage(context.getString(R.string.rate_popup_ask_message))
        setAskOkButton(context.getString(R.string.rate_popup_ask_ok))
        setAskNoButton(context.getString(R.string.rate_popup_ask_no))

        setTitle(context.getString(R.string.rate_popup_title))
        setMessage(context.getString(R.string.rate_popup_message))
        setOkButton(context.getString(R.string.rate_popup_ok))
        setCancelButton(context.getString(R.string.rate_popup_cancel))
        setNoButton(context.getString(R.string.rate_popup_no))
    }

    //region ASK
    fun setAskTitle(title: String): RatePopup =
        askDialogBuilder.setTitle(title).let { this }

    fun setAskMessage(message: String): RatePopup =
        askDialogBuilder.setMessage(message).let { this }

    fun setAskOkButton(text: String, ok: (() -> Unit)? = null) {
        askDialogBuilder.setPositiveButton(text) { _, _ ->
            ok?.invoke()
            rateDialogBuilder.show()
        }
    }

    fun setAskNoButton(text: String, no: (() -> Unit)? = null) {
        askDialogBuilder.setNegativeButton(text) { _, _ ->
            no?.invoke()
        }
    }
    //endregion

    //region RATE
    fun setTitle(title: String): RatePopup =
        rateDialogBuilder.setTitle(title).let { this }

    fun setMessage(message: String): RatePopup =
        rateDialogBuilder.setMessage(message).let { this }

    fun setOkButton(text: String, ok: (() -> Unit)? = null) {
        rateDialogBuilder.setPositiveButton(text) { _, _ ->
            ok?.invoke()
        }
    }

    fun setCancelButton(text: String, cancel: (() -> Unit)? = null) {
        rateDialogBuilder.setNeutralButton(text) { _, _ ->
            cancel?.invoke()
        }
    }

    fun setNoButton(text: String, no: (() -> Unit)? = null) {
        rateDialogBuilder.setNegativeButton(text) { _, _ ->
            no?.invoke()
        }
    }
    //endregion

    fun show() {
        askDialogBuilder.show()
    }

}