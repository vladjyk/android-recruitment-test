package dog.snow.androidrecruittest.data.db.entityes

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class RawUser(
    @PrimaryKey
    val id: Int,
    @Embedded(prefix = "address")
    val address: RawAddress,
    @Embedded(prefix = "company")
    val company: RawCompany,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
) : Parcelable {
    @Parcelize
    data class RawAddress(
        @Embedded(prefix = "geo")
        val geo: RawGeo,
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String
    ) : Parcelable {
        @Parcelize
        data class RawGeo(val lat: String, val lng: String) : Parcelable
    }
    @Parcelize
    data class RawCompany(
        val name: String,
        val catchPhrase: String,
        val bs: String
    ) : Parcelable
}