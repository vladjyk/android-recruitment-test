package dog.snow.androidrecruittest.data.network.service

import android.content.Context
import dog.snow.androidrecruittest.data.db.entityes.Album
import dog.snow.androidrecruittest.data.db.entityes.Photo
import dog.snow.androidrecruittest.data.db.entityes.User
import dog.snow.androidrecruittest.data.network.retrofit.RetrofitFactory
import dog.snow.androidrecruittest.data.network.retrofit.RetrofitFactory.RetrofitType.JSON_PLACEHOLDER
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceholderApiService {

    @GET("/photos")
    fun getPhotos(@Query("_limit") limit: Int = 100): Deferred<List<Photo>>

    @GET("/albums/{albumId}")
    fun getAlbum(@Path("albumId") albumId: Int): Deferred<Album>

    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: Int): Deferred<User>


    companion object {
        operator fun invoke(context: Context) = RetrofitFactory
            .getInstance(context, JSON_PLACEHOLDER)
            .create(JsonPlaceholderApiService::class.java)!!
    }
}