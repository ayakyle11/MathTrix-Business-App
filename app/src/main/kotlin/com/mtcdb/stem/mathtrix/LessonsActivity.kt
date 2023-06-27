package com.mtcdb.stem.mathtrix

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LessonsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var lessonAdapter: LessonAdapter
    private val lessonsList: ArrayList<Lesson> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        recyclerView = findViewById(R.id.recyclerView)
        lessonAdapter = LessonAdapter(lessonsList)

        // Set the layout manager and adapter for the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lessonAdapter

        // Add sample lessons to the list
        addSampleLessons()
    }

    private fun addSampleLessons() {
        // Add your actual lessons here or fetch them from a data source
        lessonsList.add(Lesson("Lesson 1", "Introduction to Business Mathematics"))
        lessonsList.add(Lesson("Lesson 2", "Time Value of Money"))
        lessonsList.add(Lesson("Lesson 3", "Profitability Analysis"))
        lessonsList.add(Lesson("Lesson 4", "Financial Statement Analysis"))

        // Notify the adapter that the data set has changed
        lessonAdapter.notifyDataSetChanged()
    }
}
