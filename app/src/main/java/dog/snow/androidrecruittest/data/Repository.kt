package dog.snow.androidrecruittest.data

import dog.snow.androidrecruittest.data.db.ApplicationDatabase
import dog.snow.androidrecruittest.data.db.entityes.RawAlbum
import dog.snow.androidrecruittest.data.db.entityes.RawPhoto
import dog.snow.androidrecruittest.data.db.entityes.RawUser
import dog.snow.androidrecruittest.data.network.service.JsonPlaceholderApiService
import dog.snow.androidrecruittest.util.retryIO

class Repository(private val placeholderApiService: JsonPlaceholderApiService, database: ApplicationDatabase){
    private val listItemDao = database.listItemDao()
    private val itemDetailDao = database.itemDetailDao()
    private val photoDao = database.photoDao()
    private val albumDao = database.albumDao()
    private val userDao = database.userDao()

    fun insertPhotos(photos: List<RawPhoto>){
        photoDao.insert(photos)
    }

    fun insertAlbums(albums: Map<Int,RawAlbum>){
        for ((_, album) in albums){
            albumDao.insert(album)
        }
    }

    fun insertUsers(users: Map<Int,RawUser>){
        for ((_, user) in users){
            userDao.insert(user)
        }
    }


    fun getListItems() = listItemDao.getAll()

    fun getItemDetail(photoId: Int) = itemDetailDao.getItemDetail(photoId)


    suspend fun getPhotos(limit: Int = 100) = retryIO{
       return@retryIO placeholderApiService.getPhotos(limit).await()
    }

    suspend fun getAlbum(albumId: Int) = retryIO {
        return@retryIO placeholderApiService.getAlbum(albumId).await()
    }

    suspend fun getUser(userId: Int) = retryIO {
        return@retryIO placeholderApiService.getUser(userId).await()
    }

}