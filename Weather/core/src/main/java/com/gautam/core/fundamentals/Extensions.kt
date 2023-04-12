package com.gautam.core.fundamentals

import android.app.AlertDialog
import android.content.Context
import com.gautam.core.R

fun Context.displayErrorDialog(errorMsg:String) {
    AlertDialog.Builder(this).apply {
        setTitle(getString(R.string.error_head))
        setMessage(errorMsg)
        setCancelable(false)
        setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        setNegativeButton(getString(R.string.cancel_text), null)
        show()
    }
}
