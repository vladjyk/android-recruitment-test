package dog.snow.androidrecruittest.data.db.entityes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import dog.snow.androidrecruittest.ui.main.fragments.list.model.ListItem

@Dao
interface ListItemDao {
    @Query("SELECT photos.id, photos.title, albums.title AS albumTitle, photos.thumbnailUrl " +
            "FROM photos, albums " +
            "WHERE photos.albumId == albums.id")
    fun getAll(): LiveData<List<ListItem>>
}