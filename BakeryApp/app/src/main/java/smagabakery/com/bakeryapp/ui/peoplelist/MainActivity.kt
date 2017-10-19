package smagabakery.com.bakeryapp.ui.peoplelist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import smagabakery.com.bakeryapp.R
import smagabakery.com.bakeryapp.configuration.App
import smagabakery.com.bakeryapp.data.model.People

class MainActivity : AppCompatActivity(), PeopleListView {

    private lateinit var presenter: PeopleListPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = createPresenter()
        presenter.attachView(this)



        recyclerPeople.setHasFixedSize(true)
        recyclerPeople.layoutManager  = LinearLayoutManager(this)

        btnFetch.setOnClickListener {
            presenter.fetchPeople()
        }


    }

    override fun showLoading() {
        showMessage("LOADING")
    }

    override fun hideLoading() {
        showMessage("HIDE LOADING")
    }

    override fun bindPeople(people: People) {
        recyclerPeople.adapter = PeopleListAdapter(people)
    }

    override fun showError(message: String) {
        showMessage(message)
    }

    private fun createPresenter(): PeopleListPresenter = with(application as App) { PeopleListPresenter(singletons.peopleRepository, singletons.schedulers) }

    private fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
