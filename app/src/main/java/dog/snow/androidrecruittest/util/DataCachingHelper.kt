package dog.snow.androidrecruittest.util

import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.data.db.entityes.Album
import dog.snow.androidrecruittest.data.db.entityes.User
import dog.snow.androidrecruittest.extend.exception.NetworkDisabledException
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

private const val TAG = "DataCachingHelper"
private const val NETWORK_TIMEOUT = 5000L

class DataCachingHelper(private val repository: Repository, private val networkUtil: NetworkUtil) {

    @Volatile
    private var isLocked = false

    suspend fun start(onSuccess: () -> Unit) {
        if (!isLocked) {
            if (!networkUtil.isNetworkAvailable())
                throw NetworkDisabledException()

            isLocked = true

            withTimeout(NETWORK_TIMEOUT, {
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

            }) { isLocked = false }
        }
    }

    suspend fun isDataCached(): Boolean {
        return with(repository) {
            getPhotosCount() != 0 && getAlbumsCount() != 0 && getUsersCount() != 0
        }
    }
}