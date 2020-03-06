package dog.snow.androidrecruittest.ui.splash

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.extend.exception.NetworkDisabledException
import dog.snow.androidrecruittest.extend.exception.SlowConnectivityException
import dog.snow.androidrecruittest.util.DataCachingHelper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

const val TAG = "SplashActivity"

class SplashActivity : AppCompatActivity(R.layout.splash_activity), KodeinAware {
    override val kodein by closestKodein()
    private val chacher by instance<DataCachingHelper>()
    private val repo by instance<Repository>()

    private fun showError(errorMessage: String?) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.cant_download_dialog_title)
            .setMessage(getString(R.string.cant_download_dialog_message, errorMessage))
            .setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> /*tryAgain()*/ }
            .setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }
            .create()
            .apply { setCanceledOnTouchOutside(false) }
            .show()
    }


    override fun onResume() {
        super.onResume()
        GlobalScope.launch (IO) {
            try {
                chacher.start {
                    Log.e(TAG, "Caching is successful complete")
                }
            } catch (e: NetworkDisabledException){
                Log.e(TAG, e.message, e)
            } catch (e: SlowConnectivityException){
                Log.e(TAG, e.message, e)
            }
        }
    }
}