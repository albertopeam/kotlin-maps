package albertopeam.github.com.kotlinmaps.app

import albertopeam.github.com.kotlinmaps.BuildConfig
import albertopeam.github.com.kotlinmaps.gateway.api.SearchApi
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by alberto.penas.amor on 4/2/18.
 */
class Provider(app:App) {

    var apiUrl:String = "https://maps.googleapis.com/maps/api/"
    var searchApi:SearchApi

    init {
        val cacheSizeBytes = 1024 * 1024 * 2
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        val okHttpClient:OkHttpClient  = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(Cache(app.cacheDir, cacheSizeBytes.toLong()))
                .build()
        val gson = GsonBuilder().create()
        val retrofit:Retrofit =  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(apiUrl)
                .client(okHttpClient)
                .build()
        searchApi = retrofit.create(SearchApi::class.java!!)
    }

}