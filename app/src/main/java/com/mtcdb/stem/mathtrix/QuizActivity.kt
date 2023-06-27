package com.mtcdb.stem.mathtrix

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var option1RadioButton: RadioButton
    private lateinit var option2RadioButton: RadioButton
    private lateinit var option3RadioButton: RadioButton
    private lateinit var option4RadioButton: RadioButton
    private lateinit var nextButton: Button
    private lateinit var radioGroup: RadioGroup

    private var currentQuestionIndex = 0
    private var score = 0

    private val questionsList: ArrayList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.questionTextView)
        option1RadioButton = findViewById(R.id.option1RadioButton)
        option2RadioButton = findViewById(R.id.option2RadioButton)
        option3RadioButton = findViewById(R.id.option3RadioButton)
        option4RadioButton = findViewById(R.id.option4RadioButton)
        nextButton = findViewById(R.id.nextButton)
        radioGroup = findViewById(R.id.radioGroup)

        // Set click listener for the next button
        nextButton.setOnClickListener {
            checkAnswer()
            showNextQuestion()
        }

        // Add sample questions to the list
        addSampleQuestions()

        // Display the first question
        showQuestion()
    }

    private fun addSampleQuestions() {
        // Add your actual questions here or fetch them from a data source
        questionsList.add(
            Question(
                "What is the capital of France?",
                "London",
                "Paris",
                "Madrid",
                "Rome",
                2
            )
        )
        questionsList.add(
            Question(
                "Which planet is known as the Red Planet?",
                "Venus",
                "Mars",
                "Jupiter",
                "Saturn",
                2
            )
        )
        questionsList.add(
            Question(
                "Who painted the Mona Lisa?",
                "Pablo Picasso",
                "Leonardo da Vinci",
                "Vincent van Gogh",
                "Michelangelo",
                2
            )
        )
    }

    private fun showQuestion() {
        val question = questionsList[currentQuestionIndex]

        questionTextView.text = question.question
        option1RadioButton.text = question.option1
        option2RadioButton.text = question.option2
        option3RadioButton.text = question.option3
        option4RadioButton.text = question.option4

        // Clear the selection in the radio group
        radioGroup.clearCheck()
    }

    private fun checkAnswer() {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        if (selectedRadioButtonId != -1) {
            val selectedOptionIndex = radioGroup.indexOfChild(findViewById<RadioButton>(selectedRadioButtonId))
            val question = questionsList[currentQuestionIndex]
            if (selectedOptionIndex == question.correctOptionIndex) {
                score++
            }
        }
    }

    private fun showNextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questionsList.size) {
            showQuestion()
        } else {
            showQuizResult()
        }
    }

    private fun showQuizResult() {
        questionTextView.visibility = View.GONE
        radioGroup.visibility = View.GONE
        nextButton.visibility = View.GONE

        val resultTextView: TextView = findViewById(R.id.resultTextView)
        resultTextView.visibility = View.VISIBLE
        resultTextView.text = "Quiz Completed!\nYour Score: $score out of ${questionsList.size}"
    }
}
