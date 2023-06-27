package com.mtcdb.stem.mathtrix

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DictionaryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var dictionaryAdapter: ArrayAdapter<String>

    private val dictionaryList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        listView = findViewById(R.id.listView)
        dictionaryAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dictionaryList)

        // Set the adapter for the ListView
        listView.adapter = dictionaryAdapter

        // Add sample dictionary entries
        addSampleDictionaryEntries()
    }

    private fun addSampleDictionaryEntries() {
        // Add your actual dictionary entries here or fetch them from a data source
        dictionaryList.add("Asset")
        dictionaryList.add("Liability")
        dictionaryList.add("Equity")
        dictionaryList.add("Revenue")
        dictionaryList.add("Expenses")

        // Notify the adapter that the data set has changed
        dictionaryAdapter.notifyDataSetChanged()
    }
}
