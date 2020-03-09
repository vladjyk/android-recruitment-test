package dog.snow.androidrecruittest.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.receiver.NetworkStateChangesReceiver
import dog.snow.androidrecruittest.ui.main.child.EXTRA_PHOTO_ID
import dog.snow.androidrecruittest.ui.main.child.PhotoDetailActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(R.layout.activity_main), KodeinAware, NetworkStateChangesReceiver.ConnectivityStatusChangeListener{
    override val kodein by closestKodein()
    private val networkReceiver: NetworkStateChangesReceiver by instance(arg = this)


    override fun onCreate(savedInstanceState: Bundle?) {
        fun initToolbar(){
            setSupportActionBar(findViewById(R.id.toolbar))
        }

        fun registerNetworkStatusReceiver() {
            IntentFilter().apply {
                addAction("android.net.conn.CONNECTIVITY_CHANGE")
                addAction("android.net.wifi.WIFI_STATE_CHANGED")
                registerReceiver(networkReceiver, this)
            }
        }

        super.onCreate(savedInstanceState)
        initToolbar()
        registerNetworkStatusReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    fun showDetailActivity(photoId: Int, imageView: ImageView){
        fun excludeTransitionAnimation(){
            val fade = Fade()
            fade.excludeTarget(R.id.appbar, true)
            fade.excludeTarget(android.R.id.statusBarBackground, true)
            fade.excludeTarget(android.R.id.navigationBarBackground, true)

            window.enterTransition = fade
            window.exitTransition = fade
        }

        excludeTransitionAnimation()

        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, imageView, ViewCompat.getTransitionName(imageView)!!)

        Intent(this, PhotoDetailActivity::class.java).apply {
            putExtra(EXTRA_PHOTO_ID, photoId)
            startActivity(this, options.toBundle())
        }
    }

    override fun onConnectivityStatusChanged(status: NetworkStateChangesReceiver.Status) {

    }
}