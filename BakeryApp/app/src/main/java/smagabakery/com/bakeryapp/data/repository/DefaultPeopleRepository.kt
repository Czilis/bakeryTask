package smagabakery.com.bakeryapp.data.repository

import io.reactivex.Single
import smagabakery.com.bakeryapp.data.model.People
import smagabakery.com.bakeryapp.data.model.Person
import smagabakery.com.bakeryapp.data.remote.BakeryService

class DefaultPeopleRepository(private val bakeryService: BakeryService) : PeopleRepository {
    companion object {

        private var CACHED_PEOPLE: People? = null
    }

    override fun getPeople(): Single<People> =
            if (CACHED_PEOPLE != null) {
                Single.just(CACHED_PEOPLE)
            } else {
                bakeryService.getPeople().doOnSuccess({
                    CACHED_PEOPLE = it
                })
            }

    override fun deletePerson(person: Person) {
        CACHED_PEOPLE?.all?.also {
            if (it.contains(person)) {
                CACHED_PEOPLE = People(it.filter { it != person }.toList())
            }
        }
    }

}