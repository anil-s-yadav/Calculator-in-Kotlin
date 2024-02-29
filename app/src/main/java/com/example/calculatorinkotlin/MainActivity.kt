package com.example.calculatorinkotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtInput: TextView
    private lateinit var txtSolution: TextView

    private var input = ""
    private var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtInput = findViewById(R.id.txtInput)
        txtSolution = findViewById(R.id.txtSolution)

        val digitButtons = arrayOf(
            findViewById<Button>(R.id.btnZero),
            findViewById(R.id.btnOne),
            findViewById(R.id.btnTwo),
            findViewById(R.id.btnThree),
            findViewById(R.id.btnFour),
            findViewById(R.id.btnFive),
            findViewById(R.id.btnSix),
            findViewById(R.id.btnSeven),
            findViewById(R.id.btnEight),
            findViewById(R.id.btnNine),
            findViewById(R.id.btnDecimal)
        )

        val operatorButtons = arrayOf(
            findViewById<Button>(R.id.btnAdd),
            findViewById(R.id.btnSubtract),
            findViewById(R.id.btnMultiply),
            findViewById(R.id.btnDivide)
        )

        val backspaceImageView = findViewById<ImageView>(R.id.backSpace)
        backspaceImageView.setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.substring(0, input.length - 1)
                updateInputText()
            }
        }


        val equalsButton = findViewById<Button>(R.id.btnEquals)
        val clearButton = findViewById<Button>(R.id.btnClear)

        digitButtons.forEach { button ->
            button.setOnClickListener { onDigitClick(button) }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener { onOperatorClick(button) }
        }

        equalsButton.setOnClickListener { onEqualsClick(it) }
        clearButton.setOnClickListener { onClearClick(it) }
    }

    private fun onDigitClick(button: Button) {
        val digit = button.text.toString()
        input += digit
        updateInputText()
    }

    private fun onOperatorClick(button: Button) {
        currentOperator = button.text.toString()
        input += currentOperator
        updateInputText()
    }

    private fun onEqualsClick(view: View) {
        if (input.isNotEmpty() && input.contains(currentOperator)) {
            try {
                val result: Double = evaluateExpression()
                txtSolution.text = "= $result"
            } catch (e: Exception) {
                txtSolution.text = "Error"
            }
        }
    }

    private fun onClearClick(view: View) {
        input = ""
        currentOperator = ""
        updateInputText()
        txtSolution.text = ""
    }

    private fun updateInputText() {
        txtInput.text = input
    }

        private fun evaluateExpression(): Double {
        val parts = input.split(currentOperator)
        val operand1 = parts[0].toDouble()
        val operand2 = parts[1].toDouble()

        return when (currentOperator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}

