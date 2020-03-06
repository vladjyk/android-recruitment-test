package dog.snow.androidrecruittest.extend.view

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.show(){
    visibility = VISIBLE
}

fun View.hide(){
    visibility = GONE
}