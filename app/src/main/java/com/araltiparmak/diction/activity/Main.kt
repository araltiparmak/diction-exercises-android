package com.araltiparmak.diction.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.araltiparmak.diction.Constants.KEY
import com.araltiparmak.diction.R
import com.araltiparmak.diction.Utils
import com.araltiparmak.diction.enums.ContentType

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.loadLocale(this)
        setContentView(R.layout.main)
    }

    fun exercises(view: View) {
        Intent(applicationContext, Exercises::class.java).apply {
            putExtra(KEY, ContentType.EXERCISES.lowerCase())
            startActivity(this)
        }
    }

    fun twisters(view: View) {
        val intent = Intent(applicationContext, Twisters::class.java)
        startActivity(intent)
    }

    fun settings(view: View) {
        val intent = Intent(applicationContext, Settings::class.java)
        startActivity(intent)
    }
}