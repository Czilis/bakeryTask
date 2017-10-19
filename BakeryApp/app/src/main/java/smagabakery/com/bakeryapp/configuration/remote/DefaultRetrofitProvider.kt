package smagabakery.com.bakeryapp.configuration.remote

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DefaultRetrofitProvider(private val configuration: RemoteConfiguration, private val gson: Gson) : RetrofitProvider {

    override fun provide(): Retrofit = Retrofit.Builder().baseUrl(configuration.baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(provideClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    private fun provideClient():OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
     return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
}