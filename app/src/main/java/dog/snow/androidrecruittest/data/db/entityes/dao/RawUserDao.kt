package dog.snow.androidrecruittest.data.db.entityes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dog.snow.androidrecruittest.data.db.entityes.RawUser

@Dao
interface RawUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RawUser)
}