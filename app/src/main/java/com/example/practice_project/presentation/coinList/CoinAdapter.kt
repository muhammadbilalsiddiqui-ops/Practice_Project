package com.example.practice_project.presentation.coinList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice_project.databinding.ItemCoinBinding
import com.example.practice_project.domain.model.Coin

class CoinAdapter(
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private var coins: List<Coin> = emptyList()

    fun setCoins(newCoins: List<Coin>) {
        coins = newCoins
        notifyDataSetChanged()
    }

    inner class CoinViewHolder(private val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            binding.tvCoinName.text = coin.name
            binding.tvCoinSymbol.text = coin.id.uppercase()
            binding.tvCoinPrice.text = "$${coin.price}"
            
            Glide.with(binding.root.context)
                .load(coin.image)
                .into(binding.ivCoinImage)

            binding.root.setOnClickListener {
                onItemClick(coin.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    override fun getItemCount(): Int = coins.size
}
