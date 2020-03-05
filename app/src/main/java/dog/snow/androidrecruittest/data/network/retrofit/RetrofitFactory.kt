package dog.snow.androidrecruittest.data.network.retrofit

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.data.network.retrofit.RetrofitFactory.RetrofitType.JSON_PLACEHOLDER
import dog.snow.androidrecruittest.data.network.retrofit.iterceptor.JsonPlaceholderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun getInstance(context: Context, type: RetrofitType): Retrofit{
        return when(type){
            JSON_PLACEHOLDER -> {
                val baseUrl = context.resources.getString(R.string.base_url)

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(JsonPlaceholderInterceptor)
                    .build()

                Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }

    enum class RetrofitType{
        JSON_PLACEHOLDER
    }

}