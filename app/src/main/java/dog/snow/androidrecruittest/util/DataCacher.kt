package dog.snow.androidrecruittest.util

import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.data.db.entityes.Album
import dog.snow.androidrecruittest.data.db.entityes.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.lang.Exception

private const val TAG = "DataCacher"

class DataCacher(private val repository: Repository) {

    @Volatile
    private var isLocked = false

    fun start(onSuccess: () -> Unit, onError: (e: Exception) -> Unit) {
        if (!isLocked) {
            isLocked = true

            GlobalScope.launch(IO) {
                val albumsMap = HashMap<Int, Album>()
                val usersMap = HashMap<Int, User>()
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

                launch(Main) {
                    onSuccess()
                }

                isLocked = false
            }
        }
    }
}