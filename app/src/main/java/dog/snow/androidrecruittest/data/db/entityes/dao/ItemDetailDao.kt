package dog.snow.androidrecruittest.data.db.entityes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dog.snow.androidrecruittest.data.db.entityes.ItemDetail

@Dao
interface ItemDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemDetail: ItemDetail)

    @Query("SELECT * FROM item_details WHERE photoId IS :photoId LIMIT 1")
    fun getItemDetail(photoId: Int): ItemDetail
}