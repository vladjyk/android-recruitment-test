package dog.snow.androidrecruittest.ui.main

import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.main.child.EXTRA_PHOTO_ID
import dog.snow.androidrecruittest.ui.main.child.PhotoDetailActivity

class MainActivity : AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
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
}