package dog.snow.androidrecruittest.data

import androidx.lifecycle.LiveData
import dog.snow.androidrecruittest.data.db.ApplicationDatabase
import dog.snow.androidrecruittest.data.db.entityes.ItemDetail
import dog.snow.androidrecruittest.data.db.entityes.ListItem
import dog.snow.androidrecruittest.data.network.service.JsonPlaceholderApiService
import dog.snow.androidrecruittest.data.network.service.model.RawAlbum
import dog.snow.androidrecruittest.data.network.service.model.RawPhoto
import dog.snow.androidrecruittest.data.network.service.model.RawUser
import kotlinx.coroutines.Deferred

class Repository(private val placeholderApiService: JsonPlaceholderApiService, database: ApplicationDatabase) {
    private val listItemDao = database.listItemDao()
    private val itemDetailDao = database.itemDetailDao()

    fun insert(listItem: ListItem){
        listItemDao.insert(listItem)
    }

    fun insert(itemDetail: ItemDetail){
        itemDetailDao.insert(itemDetail)
    }


    fun getAllListItems(): LiveData<List<ListItem>> {
        return listItemDao.getAll()
    }

    fun getItemDetail(photoId: Int): ItemDetail {
        return itemDetailDao.getItemDetail(photoId)
    }


    fun getPhotos(limit: Int = 100): Deferred<List<RawPhoto>> {
        return placeholderApiService.getPhotos(limit)
    }

    fun getAlbum(albumId: Int): Deferred<RawAlbum> {
        return placeholderApiService.getAlbum(albumId)
    }

    fun getUser(userId: Int): Deferred<RawUser> {
        return placeholderApiService.getUser(userId)
    }

}