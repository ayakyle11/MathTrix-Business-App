package com.mtcdb.stem.mathtrix

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private var currentNumber = ""
    private var result = 0.0
    private var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        displayTextView = findViewById(R.id.displayTextView)

        // Set click listeners for numeric and operator buttons
        setNumericButtonListeners()
        setOperatorButtonListeners()

        // Set click listener for the equals button
        val equalsButton: Button = findViewById(R.id.equalsButton)
        equalsButton.setOnClickListener {
            performCalculation()
        }

        // Set click listener for the clear button
        val clearButton: Button = findViewById(R.id.clearButton)
        clearButton.setOnClickListener {
            clearDisplay()
        }
    }

    private fun setNumericButtonListeners() {
        val numericButtons = listOf<Button>(
            findViewById(R.id.button0),
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        for (button in numericButtons) {
            button.setOnClickListener {
                appendToDisplay(button.text.toString())
            }
        }
    }

    private fun setOperatorButtonListeners() {
        val operatorButtons = listOf<Button>(
            findViewById(R.id.buttonPlus),
            findViewById(R.id.buttonMinus),
            findViewById(R.id.buttonMultiply),
            findViewById(R.id.buttonDivide)
        )

        for (button in operatorButtons) {
            button.setOnClickListener {
                setOperator(button.text.toString())
            }
        }
    }

    private fun appendToDisplay(value: String) {
        currentNumber += value
        displayTextView.text = currentNumber
    }

    private fun setOperator(operator: String) {
        if (currentOperator.isNotEmpty() && currentNumber.isNotEmpty()) {
            performCalculation()
        }
        currentOperator = operator
        result = currentNumber.toDouble()
        currentNumber = ""
    }

    private fun performCalculation() {
        if (currentNumber.isNotEmpty()) {
            val number = currentNumber.toDouble()
            when (currentOperator) {
                "+" -> result += number
                "-" -> result -= number
                "*" -> result *= number
                "/" -> {
                    if (number != 0.0) {
                        result /= number
                    } else {
                        displayTextView.text = "Error"
                        return
                    }
                }
            }
            displayTextView.text = result.toString()
            currentNumber = ""
            currentOperator = ""
        }
    }

    private fun clearDisplay() {
        currentNumber = ""
        result = 0.0
        currentOperator = ""
        displayTextView.text = ""
    }
}
