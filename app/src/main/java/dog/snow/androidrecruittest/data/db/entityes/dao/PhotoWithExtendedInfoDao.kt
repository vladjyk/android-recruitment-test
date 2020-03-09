package dog.snow.androidrecruittest.data.db.entityes.dao

import androidx.room.Dao
import androidx.room.Query
import dog.snow.androidrecruittest.ui.main.fragments.list.viewmodel.model.PhotoWithExtendedInfo

@Dao
interface PhotoWithExtendedInfoDao {
    @Query("SELECT photos.id, photos.title, albums.title AS albumTitle, photos.thumbnailUrl " +
            "FROM photos, albums " +
            "WHERE photos.albumId == albums.id")
    fun getAll(): List<PhotoWithExtendedInfo>

    @Query("SELECT photos.id, photos.title, albums.title AS albumTitle, photos.thumbnailUrl FROM photos, albums WHERE photos.albumId == albums.id AND photos.title LIKE '%' || :title || '%' OR albums.title LIKE '%' || :title || '%'")
    fun getAllWithTitle(title: String): List<PhotoWithExtendedInfo>
}