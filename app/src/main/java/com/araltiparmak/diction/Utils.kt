package com.araltiparmak.diction

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.araltiparmak.diction.Constants.APP_LANG
import com.araltiparmak.diction.Constants.APP_SETTINGS
import com.araltiparmak.diction.Constants.EN
import java.util.*

object Utils {

    fun loadLocale(activity: AppCompatActivity) {
        setLocale(activity)
        activity.supportActionBar!!.title = activity.resources.getString(R.string.app_name)
    }

    fun getLangCode(activity: AppCompatActivity): String {
        return activity.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            .getString(APP_LANG, EN)!!
    }

    private fun setLocale(activity: AppCompatActivity) {
        val prefs = activity.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)

        val lang = prefs.getString(APP_LANG, EN)!!

        val configuration = Configuration()
        configuration.locale = (Locale(lang))

        activity.baseContext.resources.updateConfiguration(
            configuration,
            activity.baseContext.resources.displayMetrics
        )

        prefs.edit().apply {
            putString(APP_LANG, lang)
            apply()
        }
    }
}