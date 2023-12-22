package com.dicoding.plantwiseapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.plantwiseapp.R
import com.dicoding.plantwiseapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.descriptionApp
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        binding.button.setOnClickListener { onButtonClick() }
        return root
    }

    private fun onButtonClick(){
        findNavController().navigate(R.id.action_navigation_home_to_mapsFragment1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}