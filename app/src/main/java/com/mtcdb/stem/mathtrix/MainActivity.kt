package com.mtcdb.stem.mathtrix

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_calculator -> {
                    startActivity(Intent(this, CalculatorActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_lessons -> {
                    startActivity(Intent(this, LessonsActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dictionary -> {
                    startActivity(Intent(this, DictionaryActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_quiz -> {
                    startActivity(Intent(this, QuizActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // Set the initial selected tab
        navView.selectedItemId = R.id.navigation_calculator
    }
}
