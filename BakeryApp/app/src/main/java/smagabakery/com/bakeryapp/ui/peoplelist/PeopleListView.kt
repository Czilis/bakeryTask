package smagabakery.com.bakeryapp.ui.peoplelist

import smagabakery.com.bakeryapp.data.model.People
import smagabakery.com.bakeryapp.ui.MVPView


interface PeopleListView : MVPView{
    fun showLoading()
    fun hideLoading()
    fun bindPeople(people: People)
    fun showError(message:String)
}