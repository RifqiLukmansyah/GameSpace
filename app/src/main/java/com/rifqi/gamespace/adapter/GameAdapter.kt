package com.rifqi.gamespace.adapter

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifqi.gamespace.R
import com.rifqi.gamespace.utils.AnimationConstant
import com.rifqi.gamespace.utils.popTap
import com.rifqi.gamespace.data.model.Game
import com.rifqi.gamespace.databinding.ItemGameBinding
import com.rifqi.gamespace.utils.formatPriceThousand
import com.rifqi.gamespace.utils.formatThousand
import com.rifqi.gamespace.utils.toRating

class GameAdapter(private val onClick: (Game) -> Unit) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var listGame = ArrayList<Game>()

    fun setData(listGame: List<Game>) {
        this.listGame.clear()
        this.listGame.addAll(listGame)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = listGame[position]
        holder.bind(game)

    }

    override fun getItemCount(): Int = listGame.size

    inner class GameViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.root.setOnClickListener {
                it.popTap()
                Handler(Looper.getMainLooper()).postDelayed({
                    onClick(game)
                }, AnimationConstant.POP_ANIMATION)
            }
            Glide.with(binding.root)
                .load(game.posterImage)
                .apply(RequestOptions())
                .into(binding.imgGame)

            binding.tvGameName.text = game.name
            binding.tvGameCategory.text = game.categories
            binding.tvGameRating.text = game.rating.toRating()
            binding.tvGameTotalReview.text = binding.root.context.getString(
                R.string.total_reviews,
                game.totalReviews.formatThousand()
            )
            binding.tvGamePrice.text = game.price.formatPriceThousand()
        }
    }
}