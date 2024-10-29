package com.example.bt2_29_10

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumber = findViewById<EditText>(R.id.etNumber)
        val rbEven = findViewById<RadioButton>(R.id.rbEven)
        val rbOdd = findViewById<RadioButton>(R.id.rbOdd)
        val rbSquare = findViewById<RadioButton>(R.id.rbSquare)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnShow.setOnClickListener {
            val inputText = etNumber.text.toString()

            if (inputText.isEmpty()) {
                tvError.text = "Vui lòng nhập một số nguyên dương."
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n < 1) {
                tvError.text = "Dữ liệu không hợp lệ. Vui lòng nhập một số nguyên dương."
                return@setOnClickListener
            }

            tvError.text = ""
            val resultList = when {
                rbEven.isChecked -> getEvenNumbers(n)
                rbOdd.isChecked -> getOddNumbers(n)
                rbSquare.isChecked -> getSquareNumbers(n)
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).mapNotNull {
            val root = Math.sqrt(it.toDouble()).toInt()
            if (root * root == it) it else null
        }
    }
}
