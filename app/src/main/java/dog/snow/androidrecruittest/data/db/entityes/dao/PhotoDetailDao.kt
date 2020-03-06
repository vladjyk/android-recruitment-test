package dog.snow.androidrecruittest.data.db.entityes.dao

import androidx.room.Dao
import androidx.room.Query
import dog.snow.androidrecruittest.ui.main.fragments.detail.model.PhotoDetail

@Dao
interface PhotoDetailDao {
    @Query("SELECT photos.id AS photoId, photos.title AS photoTitle, albums.title AS albumTitle, " +
            "users.username, users.email, users.phone, photos.url " +
            "FROM photos, albums, users " +
            "WHERE photos.id == :photoId AND photos.albumId == albums.id AND albums.userId == users.id")
    fun getPhotoDetail(photoId: Int): PhotoDetail
}