package com.rifqi.gamespace.Presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqi.gamespace.R
import com.rifqi.gamespace.adapter.CartAdapter
import com.rifqi.gamespace.data.model.CartItem
import com.rifqi.gamespace.databinding.ActivityCartBinding
import com.rifqi.gamespace.utils.AnimationConstant
import com.rifqi.gamespace.utils.popTap

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private var originalPrice: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameName = intent.getStringExtra(EXTRA_NAME) ?: ""
        val gamePrice = intent.getStringExtra(EXTRA_PRICE) ?: ""
        val gamePosterImage = intent.getIntExtra(EXTRA_POSTER, 0)

        val cartItems = listOf(CartItem(gameName, gamePrice, gamePosterImage))

        val adapter = CartAdapter(cartItems)
        binding.rvQuote.layoutManager = LinearLayoutManager(this)
        binding.rvQuote.adapter = adapter

        originalPrice = parsePrice(gamePrice)
        val formattedPrice = formatPriceThousand(originalPrice)
        binding.txtTotalPrice.text = formattedPrice

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            val discountFactor = if (isChecked) 0.8 else 1.0
            val discountedPrice = originalPrice * discountFactor
            binding.txtTotalPrice.text = formatPriceThousand(discountedPrice)
        }

        binding.btntopayment.setOnClickListener {
            it.popTap()
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, PaymentActivity::class.java).apply {
                    putExtra(EXTRA_NAME, gameName)
                    putExtra(EXTRA_PRICE, gamePrice)
                    putExtra(EXTRA_POSTER, gamePosterImage)
                }
                startActivity(intent)
            }, AnimationConstant.POP_ANIMATION)
        }
    }

    private fun formatPriceThousand(price: Double): String {
        return String.format("Rp %,d", price.toInt()).replace(",", ".")
    }

    private fun parsePrice(price: String): Double {
        return price.replace("[^0-9.]".toRegex(), "").toDoubleOrNull() ?: 0.0
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_POSTER = "extra_poster"
    }
}
