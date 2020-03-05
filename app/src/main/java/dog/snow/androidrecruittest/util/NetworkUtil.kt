package dog.snow.androidrecruittest.util

import android.content.Context
import android.net.ConnectivityManager
import android.telecom.ConnectionService

class NetworkUtil(context: Context) {
    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable(): Boolean {
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}