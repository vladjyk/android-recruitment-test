package dog.snow.androidrecruittest.data.db.entityes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.db.entityes.ListItem

@Dao
interface ListItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listItem: ListItem)

    @Query("SELECT * FROM list_items ORDER BY id")
    fun getAll(): LiveData<List<ListItem>>
}