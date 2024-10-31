package com.example.bai1danhsachdongian

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Khai báo các thành phần từ giao diện
    private lateinit var inputNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết các biến với thành phần giao diện
        inputNumber = findViewById(R.id.inputNumber)
        radioGroup = findViewById(R.id.radioGroup)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)
        tvError = findViewById(R.id.tvError)

        // Thiết lập sự kiện khi nhấn nút "Show"
        btnShow.setOnClickListener {
            // Gọi hàm xử lý khi nhấn nút
            handleShowButtonClick()
        }
    }

    // Hàm xử lý khi nhấn nút "Show"
    private fun handleShowButtonClick() {
        // Lấy giá trị từ EditText và chuyển thành số nguyên
        val input = inputNumber.text.toString()

        // Kiểm tra xem người dùng có nhập số hợp lệ hay không
        if (input.isEmpty()) {
            tvError.text = "Vui lòng nhập một số nguyên dương."
            return
        }

        // Chuyển đổi chuỗi nhập vào thành số nguyên
        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            // Hiển thị lỗi nếu giá trị không hợp lệ
            tvError.text = "Số nhập vào không hợp lệ. Vui lòng nhập số nguyên dương."
            return
        }

        // Xóa thông báo lỗi nếu có
        tvError.text = ""

        // Lấy loại số mà người dùng đã chọn
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        // Danh sách chứa các số sẽ hiển thị
        val numberList = mutableListOf<Int>()

        // Kiểm tra RadioButton nào được chọn và tạo danh sách tương ứng
        when (selectedRadioButtonId) {
            R.id.radioEven -> {
                // Nếu chọn số chẵn: Lấy các số chẵn từ 0 đến n
                for (i in 0..n step 2) {
                    numberList.add(i)
                }
            }
            R.id.radioOdd -> {
                // Nếu chọn số lẻ: Lấy các số lẻ từ 1 đến n
                for (i in 1..n step 2) {
                    numberList.add(i)
                }
            }
            R.id.radioSquare -> {
                // Nếu chọn số chính phương: Lấy các số chính phương từ 0 đến n
                var i = 0
                while (i * i <= n) {
                    numberList.add(i * i)
                    i++
                }
            }
            else -> {
                // Nếu không chọn RadioButton nào, hiển thị lỗi
                tvError.text = "Vui lòng chọn một loại số."
                return
            }
        }

        // Hiển thị danh sách số trong ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
        listView.adapter = adapter
    }
}