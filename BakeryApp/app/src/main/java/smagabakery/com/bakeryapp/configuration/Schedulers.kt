package smagabakery.com.bakeryapp.configuration

import io.reactivex.Scheduler

interface Schedulers {
    fun io(): Scheduler
    fun main(): Scheduler
}