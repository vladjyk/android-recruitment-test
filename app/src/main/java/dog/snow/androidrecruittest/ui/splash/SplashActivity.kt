package dog.snow.androidrecruittest.ui.splash

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.util.DataCacher
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SplashActivity : AppCompatActivity(R.layout.splash_activity), KodeinAware {
    override val kodein by closestKodein()
    private val chacher by instance<DataCacher>()
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
        chacher.start({},{})
    }
}