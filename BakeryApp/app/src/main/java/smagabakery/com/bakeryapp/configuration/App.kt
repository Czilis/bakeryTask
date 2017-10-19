package smagabakery.com.bakeryapp.configuration

import android.app.Application
import retrofit2.Retrofit
import smagabakery.com.bakeryapp.BuildConfig
import smagabakery.com.bakeryapp.configuration.remote.DefaultRetrofitProvider
import smagabakery.com.bakeryapp.configuration.remote.RemoteConfiguration

class App : Application() {
     lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        initRetrofit()
    }

    private fun initRetrofit() {
        retrofit = DefaultRetrofitProvider(RemoteConfiguration(BuildConfig.BAKERY_BASE_URL)).provide()
    }
}