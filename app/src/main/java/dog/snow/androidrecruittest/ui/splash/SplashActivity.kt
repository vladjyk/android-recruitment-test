package dog.snow.androidrecruittest.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.extend.exception.NetworkDisabledException
import dog.snow.androidrecruittest.extend.exception.SlowConnectivityException
import dog.snow.androidrecruittest.extend.view.hide
import dog.snow.androidrecruittest.extend.view.show
import dog.snow.androidrecruittest.ui.main.MainActivity
import dog.snow.androidrecruittest.util.DataCachingHelper
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SplashActivity : AppCompatActivity(R.layout.activity_splash), KodeinAware {
    override val kodein by closestKodein()
    private val cachingHelper by instance<DataCachingHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cacheData()
    }

    private fun cacheData() {
        progressbar.show()

        GlobalScope.launch(IO) {
            try {
                cachingHelper.startCaching {
                    showMainActivity()
                }
            } catch (e: NetworkDisabledException) {
                showErrorDialog(R.string.network_is_disabled_dialog_message)
            } catch (e: SlowConnectivityException) {
                showErrorDialog(R.string.slow_connectivity_dialog_message)
            } finally {
                launch(Main) { progressbar.hide() }
            }
        }
    }

    private fun showErrorDialog(@StringRes errorMessage: Int) {
        GlobalScope.launch(IO) {
            val isCached = cachingHelper.isDataAlreadyCached()

            launch(Main) {
                MaterialAlertDialogBuilder(this@SplashActivity).apply {
                    setTitle(R.string.cant_download_dialog_title)
                    setMessage(getString(
                            R.string.cant_download_dialog_message,
                            getString(errorMessage)
                        ))
                    setCancelable(false)
                    setPositiveButton(R.string.cant_download_dialog_btn_positive) { _, _ -> cacheData() }
                    setNegativeButton(R.string.cant_download_dialog_btn_negative) { _, _ -> finish() }

                    if (isCached)
                        setNeutralButton(R.string.cant_download_dialog_btn_neutral) { _, _ -> showMainActivity() }

                    create()
                        .apply { setCanceledOnTouchOutside(false) }
                        .show()
                }
            }
        }
    }

    private fun showMainActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
        finish()
    }
}