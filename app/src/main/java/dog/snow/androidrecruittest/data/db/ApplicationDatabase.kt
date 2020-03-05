package dog.snow.androidrecruittest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dog.snow.androidrecruittest.data.db.entityes.ItemDetail
import dog.snow.androidrecruittest.data.db.entityes.ListItem
import dog.snow.androidrecruittest.data.db.entityes.dao.ItemDetailDao
import dog.snow.androidrecruittest.data.db.entityes.dao.ListItemDao

@Database(entities = [ListItem::class, ItemDetail::class], version = 1)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun itemDetailDao(): ItemDetailDao
    abstract fun listItemDao(): ListItemDao

    companion object {
        @Volatile private var instance: ApplicationDatabase? = null

        operator fun invoke(context: Context): ApplicationDatabase = synchronized(this) {
            return instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "my.db")
                .build()
    }



}