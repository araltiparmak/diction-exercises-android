package com.araltiparmak.diction.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.araltiparmak.diction.Constants.CONTENT_TYPE
import com.araltiparmak.diction.Constants.KEY
import com.araltiparmak.diction.R
import com.araltiparmak.diction.Utils
import com.araltiparmak.diction.enums.ContentType

class Exercises : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.loadLocale(this)
        setContentView(R.layout.exercises)
    }

    fun exercise(view: View) {
        Intent(applicationContext, Exercise::class.java).apply {
            putExtra(KEY, view.tag.toString())
            putExtra(CONTENT_TYPE, ContentType.EXERCISES.name)
            startActivity(this)
        }
    }
}