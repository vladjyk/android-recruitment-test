package dog.snow.androidrecruittest.data

import dog.snow.androidrecruittest.data.db.ApplicationDatabase
import dog.snow.androidrecruittest.data.db.entityes.Album
import dog.snow.androidrecruittest.data.db.entityes.Photo
import dog.snow.androidrecruittest.data.db.entityes.User
import dog.snow.androidrecruittest.data.network.service.JsonPlaceholderApiService
import dog.snow.androidrecruittest.util.retryIO

class Repository(private val placeholderApiService: JsonPlaceholderApiService, database: ApplicationDatabase){
    private val photoWithExtendedInfoDao = database.photoWithExtendInfoDao()
    private val photoDetailDao = database.photoDetailDao()
    private val photoDao = database.photoDao()
    private val albumDao = database.albumDao()
    private val userDao = database.userDao()

    fun insertPhotos(photos: List<Photo>){
        photoDao.insert(photos)
    }

    fun insertAlbums(albums: Map<Int,Album>){
        for ((_, album) in albums){
            albumDao.insert(album)
        }
    }

    fun insertUsers(users: Map<Int,User>){
        for ((_, user) in users){
            userDao.insert(user)
        }
    }


    fun getAllPhotosWithExtendedInfo() = photoWithExtendedInfoDao.getAll()

    fun getPhotosWithExtendedInfo(title: String) = photoWithExtendedInfoDao.getAllWithTitle(title)

    fun getPhotoDetail(photoId: Int) = photoDetailDao.getPhotoDetail(photoId)

    fun getPhotosCount() = photoDao.getItemsCount()

    fun getAlbumsCount() = albumDao.getItemsCount()

    fun getUsersCount() = userDao.getItemsCount()


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