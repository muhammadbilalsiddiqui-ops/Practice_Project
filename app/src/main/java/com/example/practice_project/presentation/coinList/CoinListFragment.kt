package com.example.practice_project.presentation.coinList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice_project.R
import com.example.practice_project.databinding.FragmentCoinListBinding
import com.example.practice_project.presentation.coin.CoinDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var coinAdapter: CoinAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()

        // Back button
        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        
        viewModel.getAllCoins("1")
        
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.coinListValue.collectLatest { state ->
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                
                if (state.error.isNotBlank()) {
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.text = state.error
                } else {
                    binding.tvError.visibility = View.GONE
                }
                
                if (state.coinList.isNotEmpty()) {
                    coinAdapter.setCoins(state.coinList)
                }
            }
        }
    }
    
    private fun setupRecyclerView() {
        coinAdapter = CoinAdapter { coinId ->
            val fragment = CoinDetailFragment.newInstance(coinId)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        binding.rvCoins.apply {
            adapter = coinAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
