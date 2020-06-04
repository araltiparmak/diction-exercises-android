package com.araltiparmak.diction.activity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.araltiparmak.diction.Constants.APP_LANG
import com.araltiparmak.diction.Constants.APP_SETTINGS
import com.araltiparmak.diction.Constants.EN
import com.araltiparmak.diction.Constants.ENGLISH
import com.araltiparmak.diction.Constants.TR
import com.araltiparmak.diction.Constants.TURKISH
import com.araltiparmak.diction.R
import com.araltiparmak.diction.Utils
import java.util.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.loadLocale(this)
        setContentView(R.layout.settings)
    }

    fun changeLang(view: View) {
        val languages = arrayOf(ENGLISH, TURKISH)

        AlertDialog.Builder(this).apply {

            setTitle(R.string.changeLang)

            setSingleChoiceItems(languages, -1) { dialogInterface, i ->
                when (i) {
                    0 -> {
                        languages[i]
                        setNewLocale(EN)
                    }
                    1 -> {
                        languages[i]
                        setNewLocale(TR)
                    }
                }
                Toast.makeText(
                    applicationContext,
                    getString(R.string.selected_lang) + " " + languages[i],
                    Toast.LENGTH_SHORT
                ).show()

                dialogInterface.dismiss()
            }

            setNeutralButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.cancel()
            }

            create()
            show()
        }
    }

    private fun setNewLocale(language: String) {
        setLocale(language)
        Intent(this, Main::class.java).also {
            startActivity(it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    private fun setLocale(lang: String) {

        Configuration().apply {
            locale = (Locale(lang))
            baseContext.resources.updateConfiguration(
                this,
                baseContext.resources.displayMetrics
            )
        }

        getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE).edit().apply {
            putString(APP_LANG, lang)
            apply()
        }
    }
}