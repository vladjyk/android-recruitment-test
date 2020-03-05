package dog.snow.androidrecruittest.data.db.entityes

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "list_items")
@Parcelize
data class ListItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val albumTitle: String,
    val thumbnailUrl: String
) : Parcelable