package com.rifqi.gamespace.Presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifqi.gamespace.R
import com.rifqi.gamespace.adapter.CartAdapter
import com.rifqi.gamespace.data.model.CartItem
import com.rifqi.gamespace.databinding.ActivityCartBinding
import com.rifqi.gamespace.databinding.ActivityMainBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

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
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_POSTER = "extra_poster"
    }
}