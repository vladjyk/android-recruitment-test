package dog.snow.androidrecruittest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.main.child.EXTRA_PHOTO_ID
import dog.snow.androidrecruittest.ui.main.child.PhotoDetailActivity

class MainActivity : AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    fun showDetailActivity(photoId: Int){
        Intent(this, PhotoDetailActivity::class.java).apply {
            putExtra(EXTRA_PHOTO_ID, photoId)
            startActivity(this)
        }
    }
}