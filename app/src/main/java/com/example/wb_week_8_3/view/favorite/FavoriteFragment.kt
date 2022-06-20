package com.example.wb_week_8_3.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_8_3.R
import com.example.wb_week_8_3.databinding.FragmentFavoriteBinding
import com.example.wb_week_8_3.utils.showSnackBar
import com.example.wb_week_8_3.viewmodel.AppStateFavoriteCatsList
import com.example.wb_week_8_3.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }
    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllFavoriteCats()
        binding.favoriteFragmentRecyclerview.adapter = adapter
        viewModel.favoriteLiveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
    }

    private fun renderData(appState: AppStateFavoriteCatsList) {
        when (appState) {
            is AppStateFavoriteCatsList.Success -> {
                binding.favoriteFragmentRecyclerview.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                adapter.setData(appState.dataCat)
            }
            is AppStateFavoriteCatsList.Loading -> {
                binding.favoriteFragmentRecyclerview.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppStateFavoriteCatsList.Error -> {
                binding.favoriteFragmentRecyclerview.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.favoriteFragmentRecyclerview.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getAllFavoriteCats()
                    })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}