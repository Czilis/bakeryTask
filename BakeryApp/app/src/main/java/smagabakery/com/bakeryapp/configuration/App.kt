package smagabakery.com.bakeryapp.configuration

import android.app.Application
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import smagabakery.com.bakeryapp.BuildConfig
import smagabakery.com.bakeryapp.configuration.remote.DefaultJsonProvider
import smagabakery.com.bakeryapp.configuration.remote.DefaultRetrofitProvider
import smagabakery.com.bakeryapp.configuration.remote.RemoteConfiguration
import smagabakery.com.bakeryapp.data.remote.BakeryService
import smagabakery.com.bakeryapp.data.repository.DefaultPeopleRepository

class App : Application() {
    lateinit var singletons: Persisted

    override fun onCreate() {
        super.onCreate()

        initSingletons()
    }

    private fun initSingletons() {
        val gson = DefaultJsonProvider().provide()
        val retrofit = DefaultRetrofitProvider(RemoteConfiguration(BuildConfig.BAKERY_BASE_URL), gson).provide()

        val serviceFactory = object : ServiceFactory {
            override fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
        }
        val schedulers = object : Schedulers {
            override fun main(): Scheduler = AndroidSchedulers.mainThread()
            override fun io(): Scheduler = io.reactivex.schedulers.Schedulers.io()
        }

        singletons = PersistedInstances(serviceFactory, schedulers, DefaultPeopleRepository(serviceFactory.create(BakeryService::class.java)))
    }
}