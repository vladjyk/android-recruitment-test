package dog.snow.androidrecruittest.data

import android.content.Context
import dog.snow.androidrecruittest.data.db.ApplicationDatabase
import dog.snow.androidrecruittest.data.db.entityes.ListItem
import dog.snow.androidrecruittest.data.network.service.JsonPlaceholderApiService

class Repository(val apiService: JsonPlaceholderApiService, database: ApplicationDatabase) {
    val listItemDao = database.listItemDao()
    val itemDetailDao = database.itemDetailDao()

}