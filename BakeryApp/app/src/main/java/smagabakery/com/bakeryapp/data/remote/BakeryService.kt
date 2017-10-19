package smagabakery.com.bakeryapp.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import smagabakery.com.bakeryapp.data.model.People


interface BakeryService {

    @GET("zadanie/zadanie.json")
    fun getPeople(): Single<People>
}