package com.araltiparmak.diction.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.araltiparmak.diction.Constants.CONTENT_TYPE
import com.araltiparmak.diction.Constants.ITEMS
import com.araltiparmak.diction.Constants.KEY
import com.araltiparmak.diction.Constants.TITLE
import com.araltiparmak.diction.R
import com.araltiparmak.diction.Utils
import com.araltiparmak.diction.adapter.ExerciseAdapter
import com.araltiparmak.diction.transformer.ExercisePageTransformer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.exercise.*
import java.util.*
import kotlin.collections.ArrayList

class Exercise : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.loadLocale(this)
        setContentView(R.layout.exercise)
        loadData()
        ExercisePageTransformer.apply(contentViewPager, resources)
    }

    private fun loadData() {

        val key = intent.getStringExtra(KEY)
        val contentType = intent.getStringExtra(CONTENT_TYPE).toString().toLowerCase(Locale.ROOT)
        val lang = Utils.getLangCode(this)
        val dbRef = FirebaseDatabase.getInstance().getReference("$lang/$contentType/$ITEMS/$key")
        val contents = ArrayList<String>()

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataSnapshot.child(ITEMS).children.forEach {
                    contents.add(it.getValue(String::class.java)!!)
                }

                dataSnapshot.child(TITLE).value?.let {
                    contentTitle.text = it.toString()
                }

                ExerciseAdapter().apply {
                    setItem(contents)
                    contentViewPager.adapter = this
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}