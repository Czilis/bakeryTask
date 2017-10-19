package smagabakery.com.bakeryapp.configuration


interface ServiceFactory {

    fun <T> create(serviceClass: Class<T>): T

}