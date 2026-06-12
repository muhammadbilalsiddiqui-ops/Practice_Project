package com.example.practice_project.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practice_project.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button to return to Home
        binding.btnBackProfile.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Setup menu option clicks with friendly feedback
        binding.menuSecurity.setOnClickListener {
            Toast.makeText(requireContext(), "Account Security options coming soon!", Toast.LENGTH_SHORT).show()
        }

        binding.menuSettings.setOnClickListener {
            Toast.makeText(requireContext(), "App Settings panel coming soon!", Toast.LENGTH_SHORT).show()
        }

        binding.menuHelp.setOnClickListener {
            Toast.makeText(requireContext(), "Help & Support center coming soon!", Toast.LENGTH_SHORT).show()
        }

        binding.menuLogout.setOnClickListener {
            Toast.makeText(requireContext(), "Logging out... (Simulated)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
