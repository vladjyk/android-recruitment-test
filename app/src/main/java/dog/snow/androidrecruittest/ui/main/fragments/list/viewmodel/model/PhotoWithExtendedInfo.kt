package dog.snow.androidrecruittest.ui.main.fragments.list.viewmodel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoWithExtendedInfo(
    val id: Int,
    val title: String,
    val albumTitle: String,
    val thumbnailUrl: String
) : Parcelable