package smagabakery.com.bakeryapp.configuration.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import smagabakery.com.bakeryapp.data.model.People
import smagabakery.com.bakeryapp.data.remote.PeopleDeserializer

class DefaultJsonProvider : JsonConfigurationProvider {
    override fun provide(): Gson =
            GsonBuilder()
                    .registerTypeAdapter(People::class.java, PeopleDeserializer())
                    .create()

}