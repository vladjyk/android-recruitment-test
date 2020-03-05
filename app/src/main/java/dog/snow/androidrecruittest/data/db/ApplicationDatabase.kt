package dog.snow.androidrecruittest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dog.snow.androidrecruittest.data.db.entityes.*
import dog.snow.androidrecruittest.data.db.entityes.dao.*

@Database(entities = [RawPhoto::class, RawAlbum::class, RawUser::class], version = 2, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun itemDetailDao(): ItemDetailDao
    abstract fun listItemDao(): ListItemDao
    abstract fun photoDao(): RawPhotoDao
    abstract fun albumDao(): RawAlbumDao
    abstract fun userDao(): RawUserDao

    companion object {
        @Volatile private var instance: ApplicationDatabase? = null

        operator fun invoke(context: Context): ApplicationDatabase = synchronized(this) {
            return instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "my.db")
                .fallbackToDestructiveMigration()
                .build()
    }



}