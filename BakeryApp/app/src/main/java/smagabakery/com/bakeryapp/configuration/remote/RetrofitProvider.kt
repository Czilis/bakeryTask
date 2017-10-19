package smagabakery.com.bakeryapp.configuration.remote

import retrofit2.Retrofit


interface RetrofitProvider {
    fun provide():Retrofit
}