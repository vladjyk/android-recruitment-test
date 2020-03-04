package dog.snow.androidrecruittest.data.network.service

import android.content.Context
import dog.snow.androidrecruittest.data.network.service.model.RawAlbum
import dog.snow.androidrecruittest.data.network.service.model.RawPhoto
import dog.snow.androidrecruittest.data.network.service.model.RawUser
import dog.snow.androidrecruittest.data.network.retrofit.RetrofitCreator
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceholderApiService {

    @GET("/photos")
    fun getPhotos(@Query("_limit") limit: Int = 100): Deferred<List<RawPhoto>>

    @GET("/albums/{albumId}")
    fun getAlbum(@Path("albumId") albumId: Int): Deferred<RawAlbum>

    @GET("/users/{userId}")
    fun getUser(@Path("userId") userId: Int): Deferred<RawUser>


    companion object {
        operator fun invoke(context: Context) = RetrofitCreator
            .getRetrofitInstance(context)
            .create(JsonPlaceholderApiService::class.java)!!
    }
}