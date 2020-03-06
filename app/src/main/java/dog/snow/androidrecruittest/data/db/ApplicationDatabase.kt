package dog.snow.androidrecruittest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dog.snow.androidrecruittest.data.db.entityes.*
import dog.snow.androidrecruittest.data.db.entityes.dao.*

@Database(entities = [Photo::class, Album::class, User::class], version = 4, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun photoDetailDao(): PhotoDetailDao
    abstract fun photoWithExtendInfoDao(): PhotoWithExtendedInfoDao
    abstract fun photoDao(): PhotoDao
    abstract fun albumDao(): AlbumDao
    abstract fun userDao(): UserDao

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