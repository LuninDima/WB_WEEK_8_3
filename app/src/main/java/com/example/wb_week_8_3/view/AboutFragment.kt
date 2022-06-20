package com.example.wb_week_8_3.view

import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wb_week_8_3.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        setupHyperlink()
        return binding.root
    }

    private fun setupHyperlink() {
        binding.tvGitHub.movementMethod = LinkMovementMethod.getInstance()
        binding.tvGitHub.setLinkTextColor(Color.BLUE)
    }
}