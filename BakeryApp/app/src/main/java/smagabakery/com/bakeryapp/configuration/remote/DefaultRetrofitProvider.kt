package smagabakery.com.bakeryapp.configuration.remote

import retrofit2.Retrofit

class DefaultRetrofitProvider (private val configuration: RemoteConfiguration): RetrofitProvider {

    override fun provide(): Retrofit = Retrofit.Builder().baseUrl(configuration.baseApiUrl).build()

}