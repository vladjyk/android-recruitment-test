package dog.snow.androidrecruittest.util

import dog.snow.androidrecruittest.data.Repository
import dog.snow.androidrecruittest.data.db.entityes.ItemDetail
import dog.snow.androidrecruittest.data.db.entityes.ListItem
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception

class DataCacher (private val repository: Repository) {

    fun start(onSuccess: ()-> Unit, onError: (e: Exception)-> Unit){
        GlobalScope.launch(IO) {
            val alreadyDownloadedAlbumIds = HashSet<Int>()

            val photos = repository.getPhotos().await()

            photos.forEach {
                if (it.albumId !in alreadyDownloadedAlbumIds){
                    val album = repository.getAlbum(it.albumId).await()
                    val user = repository.getUser(album.userId).await()

                    val listItem = ListItem(it.id, it.title, album.title, it.thumbnailUrl)
                    val itemDetail = ItemDetail(it.id, it.title, album.title, user.username, user.email, user.phone, it.url)
                }
            }
        }
    }
}