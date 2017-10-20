package smagabakery.com.bakeryapp.ui.peoplelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_person.view.*
import smagabakery.com.bakeryapp.R
import smagabakery.com.bakeryapp.data.model.People
import smagabakery.com.bakeryapp.data.model.Person
import smagabakery.com.bakeryapp.ui.colorlist.ColorResources
import smagabakery.com.bakeryapp.ui.colorlist.ViewColor
import smagabakery.com.bakeryapp.ui.hide
import smagabakery.com.bakeryapp.ui.show


class PeopleListAdapter(private var people: People, private val onPersonRowDeleteClicked: (Person) -> Unit, private val onRowClicked: (Person) -> Unit, coloredRows: List<Pair<Long, ViewColor>>) : RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {
    var coloredRows = coloredRows
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = people.all.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val currentPerson = people.all[position]

        val backgroundColor = with(coloredRows) {
            if (coloredRows.any { it.first == currentPerson.id }) {
                first { it.first == currentPerson.id }.second
            } else ViewColor.DEFAULT
        }
        holder.bind(people.all[position], backgroundColor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_person, parent, false)
        return PeopleViewHolder(view, onPersonRowDeleteClicked, onRowClicked)
    }


    inner class PeopleViewHolder(view: View, private val onPersonRowDeleteClicked: (Person) -> Unit, private val onRowClicked: (Person) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bind(person: Person, backgroundColor: ViewColor) {
            with(itemView) {
                setBackgroundColor(ColorResources.resolve(backgroundColor, context))

                txtFirstName.text = person.firstName
                txtLastName.text = person.lastName
                txtBirthDay.text = person.birthDay
                txtDescriptionValue.text = person.description

                imgDelete.setOnClickListener { onPersonRowDeleteClicked(person) }

                Glide.with(context)
                        .load(person.avatarUrl)
                        .apply(RequestOptions.circleCropTransform())
                        .apply(RequestOptions().placeholder(R.drawable.ic_default_avatar))
                        .into(imgAvatar)

                txtDescription.setOnClickListener {
                    when (txtDescriptionValue.visibility) {
                        View.VISIBLE -> {
                            txtDescription.text = context.getText(R.string.l_show_description)
                            txtDescriptionValue.hide()
                        }
                        View.GONE -> {
                            txtDescription.text = context.getText(R.string.l_hide_description)
                            txtDescriptionValue.show()
                        }

                    }
                }

                setOnClickListener { onRowClicked(person) }
            }
        }

    }
}

