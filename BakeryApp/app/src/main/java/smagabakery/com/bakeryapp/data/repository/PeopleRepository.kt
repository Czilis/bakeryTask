package smagabakery.com.bakeryapp.data.repository

import io.reactivex.Single
import smagabakery.com.bakeryapp.data.model.People
import smagabakery.com.bakeryapp.data.model.Person

interface PeopleRepository {
    fun getPeople(): Single<People>

    fun deletePerson(person: Person)
}