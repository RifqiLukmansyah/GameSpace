package com.rifqi.gamespace.Presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rifqi.gamespace.R
import com.rifqi.gamespace.databinding.ActivityPaymentSummaryBinding

class PaymentSummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Safeguard against null references
        val rootView = findViewById<View>(R.id.main)
        rootView?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val gameName = intent.getStringExtra(CartActivity.EXTRA_NAME) ?: ""
        val gamePrice = intent.getStringExtra(CartActivity.EXTRA_PRICE) ?: ""
        val fullName = intent.getStringExtra("EXTRA_FULL_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val date = intent.getStringExtra("EXTRA_DATE") ?: ""
        val address = intent.getStringExtra("EXTRA_ADDRESS") ?: ""
        val paymentMethod = intent.getStringExtra("EXTRA_PAYMENT_METHOD") ?: ""

        binding.tvGameName.text = gameName
        binding.tvGamePrice.text = gamePrice
        binding.tvFullName.text = fullName
        binding.tvEmail.text = email
        binding.tvDate.text = date
        binding.tvAddress.text = address
        binding.tvPaymentMethod.text = paymentMethod
    }
}
