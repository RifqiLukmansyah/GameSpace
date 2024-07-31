package com.rifqi.gamespace.Presentation

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rifqi.gamespace.R
import java.util.*

class PaymentActivity : AppCompatActivity() {

    private lateinit var tvDate: TextView
    private lateinit var spinnerPayment: Spinner
    private var selectedDate: String? = null
    private var selectedPaymentMethod: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val gameName = intent.getStringExtra(CartActivity.EXTRA_NAME) ?: ""
        val gamePrice = intent.getStringExtra(CartActivity.EXTRA_PRICE) ?: ""

        tvDate = findViewById(R.id.tv_date)
        val chooseDate: LinearLayout = findViewById(R.id.choose_date)
        spinnerPayment = findViewById(R.id.spinnerPayment)

        chooseDate.setOnClickListener {
            showDatePickerDialog()
        }

        setupSpinner()

        findViewById<View>(R.id.btntopayment).setOnClickListener {
            navigateToPaymentSummary(gameName, gamePrice)
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            tvDate.text = selectedDate
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun setupSpinner() {
        val paymentMethods = arrayOf("Dana", "GoPay", "OVO", "BNI", "BCA")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentMethods)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPayment.adapter = adapter

        spinnerPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedPaymentMethod = paymentMethods[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedPaymentMethod = null
            }
        }
    }

    private fun navigateToPaymentSummary(gameName: String, gamePrice: String) {
        val fullName = findViewById<TextView>(R.id.edtName).text.toString()
        val email = findViewById<TextView>(R.id.edtEmail).text.toString()
        val address = findViewById<TextView>(R.id.edtAddress).text.toString()

        val intent = Intent(this, PaymentSummaryActivity::class.java).apply {
            putExtra(CartActivity.EXTRA_NAME, gameName)
            putExtra(CartActivity.EXTRA_PRICE, gamePrice)
            putExtra("EXTRA_FULL_NAME", fullName)
            putExtra("EXTRA_EMAIL", email)
            putExtra("EXTRA_DATE", selectedDate)
            putExtra("EXTRA_ADDRESS", address)
            putExtra("EXTRA_PAYMENT_METHOD", selectedPaymentMethod)
        }
        startActivity(intent)
    }
}
