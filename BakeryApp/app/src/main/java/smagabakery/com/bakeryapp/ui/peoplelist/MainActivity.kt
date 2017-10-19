package smagabakery.com.bakeryapp.ui.peoplelist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import smagabakery.com.bakeryapp.R
import smagabakery.com.bakeryapp.configuration.App
import smagabakery.com.bakeryapp.data.model.People
import smagabakery.com.bakeryapp.data.model.Person
import smagabakery.com.bakeryapp.ui.colorlist.ColorListActivity
import smagabakery.com.bakeryapp.ui.colorlist.ViewColor
import smagabakery.com.bakeryapp.ui.hide
import smagabakery.com.bakeryapp.ui.show

private const val CURRENT_RECYCLER_POSITION_KEY = "recycler_view_position"

class MainActivity : AppCompatActivity(), PeopleListView {

    private lateinit var presenter: PeopleListPresenter
    private lateinit var adapter: PeopleListAdapter
    private var coloredRows: MutableList<Pair<Int, ViewColor>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        presenter = createPresenter()
        presenter.attachView(this)
        presenter.fetchPeople()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(CURRENT_RECYCLER_POSITION_KEY, (recyclerPeople.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
        outState?.putParcelableArrayList(KEY_COLORED_ROWS, coloredRows.parcelled())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.also {
            val recyclerPosition = it.getInt(CURRENT_RECYCLER_POSITION_KEY)
            (recyclerPeople.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(recyclerPosition, 0)
            coloredRows = it.getParcelableArrayList<ParcelledColoredRow>(KEY_COLORED_ROWS).map { it.toPair() }.toMutableList()
        }
    }

    override fun showLoading() {
        recyclerPeople.hide()
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
        recyclerPeople.show()
    }

    override fun bindPeople(people: People) {
        adapter = PeopleListAdapter(people, {  showConfirmationDialog(it) }, { openColorListActivity(it) }, coloredRows)
        recyclerPeople.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == KEY_COLOR_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            handleRowColorChosen(data)
        }
    }

    override fun showError(message: String) {
        showMessage(message)
    }

    companion object {
        val KEY_COLOR_REQUEST_CODE = 11
        val KEY_COLOR = "color_key"
        val KEY_ROW_INDEX = "row_index_key"
        private val KEY_COLORED_ROWS = "colored_rows_key"
    }

    private fun handleRowColorChosen(data: Intent?) {
        data?.getStringExtra(KEY_COLOR)?.also {
            val color = ViewColor.valueOf(it)
            val rowIndex = data.getIntExtra(KEY_ROW_INDEX, -1)
            if (rowIndex != -1) {
                coloredRows.firstOrNull {
                    it.first == rowIndex
                }?.also {
                    coloredRows.remove(it)
                }
                coloredRows.add(Pair(rowIndex, color))
                adapter.coloredRows = coloredRows
                adapter.notifyItemChanged(rowIndex)
            }
        }
    }

    private fun openColorListActivity(rowIndex: Int) {
        val intent = Intent(this, ColorListActivity::class.java)
        intent.putExtra(KEY_ROW_INDEX, rowIndex)
        startActivityForResult(intent, KEY_COLOR_REQUEST_CODE)
    }

    private fun initRecyclerView() {
        recyclerPeople.setHasFixedSize(true)
        recyclerPeople.layoutManager = LinearLayoutManager(this)
        recyclerPeople.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
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

private fun MutableList<Pair<Int, ViewColor>>.parcelled(): ArrayList<ParcelledColoredRow> = ArrayList(map { ParcelledColoredRow(it.first, it.second) }.toList())


@Parcelize
private class ParcelledColoredRow(val rowIndex: Int, val viewColor: ViewColor) : Parcelable {
    fun toPair() = Pair(rowIndex, viewColor)
}