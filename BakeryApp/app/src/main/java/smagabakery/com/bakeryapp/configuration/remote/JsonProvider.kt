package smagabakery.com.bakeryapp.configuration.remote

import com.google.gson.Gson

interface JsonProvider {
    fun provide() : Gson
}