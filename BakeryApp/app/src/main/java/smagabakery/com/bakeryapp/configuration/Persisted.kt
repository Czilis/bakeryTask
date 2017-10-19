package smagabakery.com.bakeryapp.configuration

import smagabakery.com.bakeryapp.data.repository.PeopleRepository


interface Persisted {
    val serviceFactory: ServiceFactory
    val schedulers: Schedulers
    val peopleRepository: PeopleRepository
}

class PersistedInstances(override val serviceFactory: ServiceFactory, override val schedulers: Schedulers, override val peopleRepository: PeopleRepository) : Persisted