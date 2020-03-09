package dog.snow.androidrecruittest.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dog.snow.androidrecruittest.util.NetworkUtil

class NetworkStateChangesReceiver(private val listener: ConnectivityStatusChangeListener, private val networkUtil: NetworkUtil): BroadcastReceiver() {

    private lateinit var previousStatus: Status

    override fun onReceive(context: Context, intent: Intent?) {
        val currentStatus = if (networkUtil.isNetworkAvailable())
            Status.CONNECTED
        else Status.DISCONNECTED


        if (::previousStatus.isInitialized){
            if (currentStatus != previousStatus)
                listener.onConnectivityStatusChanged(currentStatus)

        } else {
            if (currentStatus == Status.DISCONNECTED)
                listener.onConnectivityStatusChanged(Status.DISCONNECTED)
        }

        previousStatus = currentStatus
    }


    interface ConnectivityStatusChangeListener {
        fun onConnectivityStatusChanged(status: Status)
    }

    enum class Status(val title: String){
        CONNECTED("Connected"),
        DISCONNECTED("Disconnected")
    }
}