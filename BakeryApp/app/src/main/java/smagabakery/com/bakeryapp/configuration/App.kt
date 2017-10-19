package smagabakery.com.bakeryapp.configuration

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid
import retrofit2.Retrofit
import smagabakery.com.bakeryapp.BuildConfig
import smagabakery.com.bakeryapp.configuration.remote.DefaultJsonProvider
import smagabakery.com.bakeryapp.configuration.remote.DefaultRetrofitProvider
import smagabakery.com.bakeryapp.configuration.remote.RemoteConfiguration

class App : Application() {
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        JodaTimeAndroid.init(this)
        initRetrofit()
    }

    private fun initRetrofit() {
        val gson = DefaultJsonProvider().provide()
        retrofit = DefaultRetrofitProvider(RemoteConfiguration(BuildConfig.BAKERY_BASE_URL), gson).provide()
    }
}