package smagabakery.com.bakeryapp.ui.colorlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_color_list.*
import smagabakery.com.bakeryapp.R
import smagabakery.com.bakeryapp.ui.peoplelist.MainActivity


class ColorListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_list)

        viewBlue.setOnClickListener { finishWithCustomResult(ViewColor.BLUE) }
        viewRed.setOnClickListener { finishWithCustomResult(ViewColor.RED) }
        viewGreen.setOnClickListener { finishWithCustomResult(ViewColor.GREEN) }
        viewDefault.setOnClickListener { finishWithCustomResult(ViewColor.DEFAULT) }
    }

    private fun finishWithCustomResult(color: ViewColor) {
        val intent = Intent()
        intent.putExtra(MainActivity.KEY_COLOR, color.name)
        intent.putExtra(MainActivity.KEY_PERSON_ID, getIntent().getLongExtra(MainActivity.KEY_PERSON_ID, -1))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}