package dog.snow.androidrecruittest.util

import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.data.db.entityes.RawAlbum
import dog.snow.androidrecruittest.data.db.entityes.RawUser
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.lang.Exception

private const val TAG = "DataCacher"

class DataCacher(private val repository: Repository) {

    fun start(onSuccess: () -> Unit, onError: (e: Exception) -> Unit) {
        GlobalScope.launch(IO) {
            val albumsMap = HashMap<Int, RawAlbum>()
            val usersMap = HashMap<Int, RawUser>()
            val photos = repository.getPhotos()

            photos.forEach { photo ->
                if (albumsMap[photo.albumId] == null)
                    albumsMap[photo.albumId] = repository.getAlbum(photo.albumId)

                val userId = albumsMap[photo.albumId]!!.userId

                if (usersMap[userId] == null)
                    usersMap[userId] = repository.getUser(userId)
            }

            repository.apply {
                insertPhotos(photos)
                insertAlbums(albumsMap)
                insertUsers(usersMap)
            }
        }
    }
}