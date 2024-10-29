package com.example.ex2_simplelist

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtNumber = findViewById(R.id.edtNumber)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        btnShow.setOnClickListener {
            tvError.visibility = TextView.GONE
            val input = edtNumber.text.toString()

            // Kiểm tra nếu EditText trống
            if (input.isEmpty()) {
                tvError.text = "Vui lòng nhập một số"
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            // Chuyển đổi chuỗi thành số nguyên và kiểm tra số âm
            val n = input.toIntOrNull()
            if (n == null || n < 0) {
                tvError.text = "Vui lòng nhập số nguyên dương hợp lệ"
                tvError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            // Xử lý danh sách số theo loại số đã chọn
            val numbers = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioSquare.isChecked -> getSquareNumbers(n)
                else -> emptyList()
            }

            // Cập nhật ListView với các số thỏa mãn điều kiện
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
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
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}
