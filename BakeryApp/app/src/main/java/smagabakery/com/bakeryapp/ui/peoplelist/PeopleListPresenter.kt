package smagabakery.com.bakeryapp.ui.peoplelist

import smagabakery.com.bakeryapp.configuration.Schedulers
import smagabakery.com.bakeryapp.data.model.Person
import smagabakery.com.bakeryapp.data.repository.PeopleRepository
import smagabakery.com.bakeryapp.ui.MVPPresenter


class PeopleListPresenter(private val peopleRepository: PeopleRepository, private val schedulers: Schedulers) : MVPPresenter<PeopleListView>() {

    fun fetchPeople() {
        view?.showLoading()
        peopleRepository.getPeople()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .subscribe({
                    view?.hideLoading()
                    view?.bindPeople(it)
                }, {
                    view?.showError("Error occured")
                })
    }

    fun deletePerson(person: Person) {
        peopleRepository.deletePerson(person)
        peopleRepository.getPeople().subscribe({
            view?.bindPeople(it)
        }, {
            view?.showError("Some error occured")
        }
        )
    }

}
