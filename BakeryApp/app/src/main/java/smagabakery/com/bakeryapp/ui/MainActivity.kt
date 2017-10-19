package smagabakery.com.bakeryapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import smagabakery.com.bakeryapp.R
import smagabakery.com.bakeryapp.configuration.App
import smagabakery.com.bakeryapp.data.remote.BakeryService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as App
        app.retrofit.create(BakeryService::class.java).getPeople()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(this, "${it.people.size}", Toast.LENGTH_SHORT).show()
                }, {
                    System.err.println(it)
                    System.err.println("ERRROR")
                })


    }
}
