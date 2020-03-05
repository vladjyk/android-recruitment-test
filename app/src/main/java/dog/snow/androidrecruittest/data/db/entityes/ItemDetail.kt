package dog.snow.androidrecruittest.data.db.entityes

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "item_details")
@Parcelize
data class ItemDetail(
    @PrimaryKey
    val photoId: Int,
    val photoTitle: String,
    val albumTitle: String,
    val username: String,
    val email: String,
    val phone: String,
    val url: String
) : Parcelable