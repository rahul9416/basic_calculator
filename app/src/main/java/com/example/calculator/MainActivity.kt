package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var value = ""
        var lastValue = ""

        btn7.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 7
        }
        btn8.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 8
        }
        btn9.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 9
        }
        btn4.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 4
        }
        btn5.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 5
        }
        btn6.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 6
        }
        btn1.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 1
        }
        btn2.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 2
        }
        btn3.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 3
        }
        btn0.setOnClickListener {
            tvExpression.text = tvExpression.text.toString() + 0
        }
        btnDot.setOnClickListener {
            lastValue = tvExpression.text.takeLast(1) as String
            if (lastValue.toIntOrNull() != null) {
                tvExpression.text = tvExpression.text.toString() + "."
            }
            else {
                tvExpression.text = tvExpression.text.toString() + ""
            }
        }
        btnC.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        btnAdd.setOnClickListener {
            if (tvExpression.text.toString().isBlank()) {
                tvExpression.text = ""
            }
            else {

                lastValue = tvExpression.text.takeLast(1) as String
                Log.d("MainActivity", "$lastValue")
                if (lastValue == "+") {
                    tvExpression.text = tvExpression.text.toString().dropLast(1)
                }
                if (lastValue == "-" || lastValue == "*" || lastValue == "/") {
                    tvExpression.text = tvExpression.text.toString().dropLast(1)
                }
                Log.d("MainActivity", "${tvExpression.text}")
                tvExpression.text = tvExpression.text.toString() + "+"
            }
        }

        btnSubtract.setOnClickListener {
            lastValue = tvExpression.text.takeLast(1) as String
            Log.d("MainActivity", "$lastValue")
            if (lastValue == "-") {
                tvExpression.text = tvExpression.text.toString().dropLast(1)
            }
            if (lastValue == "+" || lastValue == "*" || lastValue == "/") {
                tvExpression.text = tvExpression.text.toString().dropLast(1)
            }
            Log.d("MainActivity", "${tvExpression.text}")
            tvExpression.text = tvExpression.text.toString() + "-"
        }

        btnMultiply.setOnClickListener {
            if (tvExpression.text.toString().isBlank()) {
                tvExpression.text = ""
            } else {
                lastValue = tvExpression.text.takeLast(1) as String
                Log.d("MainActivity", "$lastValue")
                if (lastValue == "*") {
                    tvExpression.text = tvExpression.text.toString().dropLast(1)
                }
                if (lastValue == "+" || lastValue == "-" || lastValue == "/") {
                    tvExpression.text = tvExpression.text.toString().dropLast(1)
                }
                Log.d("MainActivity", "${tvExpression.text}")
                tvExpression.text = tvExpression.text.toString() + "*"
            }
        }

        btnDivide.setOnClickListener {
            if (tvExpression.text.toString().isBlank()) {
                tvExpression.text = ""
            } else {
                lastValue = tvExpression.text.takeLast(1) as String
                Log.d("MainActivity", "$lastValue")
                if (lastValue == "/") {
                    tvExpression.text = tvExpression.text.toString().dropLast(1)
                }
                if (lastValue == "+" || lastValue == "*" || lastValue == "-") {
                    tvExpression.text = tvExpression.text.toString().dropLast(1)
                }
                Log.d("MainActivity", "${tvExpression.text}")
                tvExpression.text = tvExpression.text.toString() + "/"
            }
        }
        btnEquals.setOnClickListener {
            if (tvExpression.text.toString().isBlank()) {
                tvExpression.text = ""
            } else {
                val finalValue = tvExpression.text.toString()
                if (finalValue.takeLast(1).toIntOrNull() == null) {
                    tvResult.text = ""
                } else {
                    try {
                        val expression = ExpressionBuilder(finalValue).build()
                        Log.d("Main Activity", "$expression")
                        val result = expression.evaluate()
                        Log.d("Main Activity", "$result")
                        val longResult = result.toLong()
                        Log.d("MainActivity", "$result")
                        if (result == longResult.toDouble()) {
                            tvResult.text = longResult.toString()
                        } else {
                            tvResult.text = result.toString()
                        }
                    }
                    catch (e: ArithmeticException) {
                        tvResult.text = "Cannot be divided by 0"
                    }

                }
            }
        }
    }
}