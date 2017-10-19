package smagabakery.com.bakeryapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import smagabakery.com.bakeryapp.R
import smagabakery.com.bakeryapp.configuration.App

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "is empty? ${(application as App).retrofit}", Toast.LENGTH_LONG).show()

    }
}
