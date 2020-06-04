package com.araltiparmak.diction.activity

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araltiparmak.diction.Constants.CONTENT_TYPE
import com.araltiparmak.diction.Constants.ITEMS
import com.araltiparmak.diction.Constants.KEY
import com.araltiparmak.diction.Constants.LABEL
import com.araltiparmak.diction.R
import com.araltiparmak.diction.Utils
import com.araltiparmak.diction.adapter.RecyclerViewAdapter
import com.araltiparmak.diction.dto.Button
import com.araltiparmak.diction.enums.ContentType
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.twisters.*
import java.util.*
import kotlin.collections.ArrayList

class Twisters : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.loadLocale(this)
        setContentView(R.layout.twisters)
        loadData(this)
    }

    private fun loadData(twisters: Twisters) {
        val lang = Utils.getLangCode(this)

        val dbRef = FirebaseDatabase.getInstance()
            .getReference("$lang/${ContentType.TWISTERS.lowerCase()}/$ITEMS")

        var viewManager = GridLayoutManager(twisters, 2)


        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val categories: ArrayList<Button> = ArrayList()

                dataSnapshot.children.forEach {

                    val label = (it.value as HashMap<*, *>)[LABEL].toString()

                    categories.add(Button(it.key!!, label))
                    viewAdapter = RecyclerViewAdapter(getWidth(), categories) { s: String ->
                        itemClicked(s)
                    }

                    twistersRecyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = viewManager
                        adapter = viewAdapter
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun getWidth(): Int {
        DisplayMetrics().also {
            windowManager.defaultDisplay.getMetrics(it)
            return (it.widthPixels / it.density).toInt()
        }
    }

    private fun itemClicked(s: String) {
        Intent(applicationContext, Exercise::class.java).apply {
            putExtra(KEY, s)
            putExtra(CONTENT_TYPE, ContentType.TWISTERS.name)
            startActivity(this)
        }
    }
}