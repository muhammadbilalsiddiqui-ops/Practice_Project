package com.example.practice_project.presentation.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.practice_project.databinding.FragmentCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: CoinViewModel by viewModels()
    private var coinId: String? = null

    companion object {
        private const val ARG_COIN_ID = "coin_id"
        fun newInstance(coinId: String) = CoinDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_COIN_ID, coinId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinId = arguments?.getString(ARG_COIN_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button
        binding.btnBackDetail.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        
        coinId?.let { viewModel.getCoinById(it) }
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel._coinValue.collectLatest { state ->
                binding.progressBarDetail.visibility = if (state.isloading) View.VISIBLE else View.GONE
                
                if (state.error.isNotBlank()) {
                    binding.tvDetailError.visibility = View.VISIBLE
                    binding.tvDetailError.text = state.error
                } else {
                    binding.tvDetailError.visibility = View.GONE
                }
                
                state.coinDetail?.let { coin ->
                    binding.tvDetailName.text = coin.name
                    binding.tvDetailSymbol.text = coin.id.uppercase()
                    binding.tvDetailDescription.text = coin.description
                    
                    Glide.with(requireContext())
                        .load(coin.image)
                        .into(binding.ivDetailImage)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
