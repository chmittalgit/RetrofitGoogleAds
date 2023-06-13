package com.example.retrofitgoogleads.googleadsretrofit.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        if (CarUtils.isOnline(context!!)) {
            connectivityReceiverListener?.onNetworkConnectionChanged(true)
        } else {
            connectivityReceiverListener?.onNetworkConnectionChanged(false)
        }
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}