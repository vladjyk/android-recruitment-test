package dog.snow.androidrecruittest.data.network.retrofit.iterceptor

import okhttp3.Interceptor
import okhttp3.Response

object JsonPlaceholderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader("User-Agent", "Some value")
            .build()

        return chain.proceed(request)
    }
}