package com.gautam.core.fundamentals

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.gautam.core.R

/*
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
*/


fun Context.isConnected(): Boolean {
    var status = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            status = true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            status = true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
            status = true
        }
    }
    return status
}
