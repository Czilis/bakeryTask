package smagabakery.com.bakeryapp.data.repository

import io.reactivex.Single
import smagabakery.com.bakeryapp.data.model.People

interface PeopleRepository {
    fun getPeople(): Single<People>
}