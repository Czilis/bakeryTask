package smagabakery.com.bakeryapp.ui.peoplelist

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import smagabakery.com.bakeryapp.R
import smagabakery.com.bakeryapp.configuration.App
import smagabakery.com.bakeryapp.data.model.People
import smagabakery.com.bakeryapp.data.model.Person

class MainActivity : AppCompatActivity(), PeopleListView {

    private lateinit var presenter: PeopleListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = createPresenter()
        presenter.attachView(this)



        recyclerPeople.setHasFixedSize(true)
        recyclerPeople.layoutManager = LinearLayoutManager(this)
        recyclerPeople.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btnFetch.setOnClickListener { presenter.fetchPeople() }


    }

    override fun showLoading() {
        showMessage("LOADING")
    }

    override fun hideLoading() {
        showMessage("HIDE LOADING")
    }

    override fun bindPeople(people: People) {
        recyclerPeople.adapter = PeopleListAdapter(people, { showConfirmationDialog(it) })
    }


    override fun showError(message: String) {
        showMessage(message)
    }

    private fun showConfirmationDialog(person: Person) {
        AlertDialog.Builder(this).setTitle("Are You sure to delete ${person.firstName} ${person.lastName}")
                .setPositiveButton("Delete", { dialogInterface, _ ->
                    dialogInterface.dismiss()
                    presenter.deletePerson(person)
                })
                .setNegativeButton("Cancel", { dialogInterface, _ -> dialogInterface.dismiss() })
                .create()
                .show()

    }

    private fun createPresenter(): PeopleListPresenter = with(application as App) { PeopleListPresenter(singletons.peopleRepository, singletons.schedulers) }

    private fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
