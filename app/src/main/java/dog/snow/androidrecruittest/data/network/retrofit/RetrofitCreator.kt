package dog.snow.androidrecruittest.data.network.retrofit

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.data.network.retrofit.iterceptor.JsonPlaceholderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCreator {
    fun getRetrofitInstance(context: Context): Retrofit{
        val baseUrl = context.resources.getString(R.string.base_url)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(JsonPlaceholderInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}