package dog.snow.androidrecruittest.ui.main.fragments.detail.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoDetail(
    val photoId: Int,
    val photoTitle: String,
    val albumTitle: String,
    val username: String,
    val email: String,
    val phone: String,
    val url: String
) : Parcelable