package dog.snow.androidrecruittest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dog.snow.androidrecruittest.R

class MainActivity : AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}