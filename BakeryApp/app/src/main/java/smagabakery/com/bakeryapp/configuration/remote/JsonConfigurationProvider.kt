package smagabakery.com.bakeryapp.configuration.remote

import com.google.gson.Gson

interface JsonConfigurationProvider {
    fun provide() : Gson
}