package com.example.wb_week_8_3.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_8_3.R
import com.example.wb_week_8_3.databinding.FragmentMainBinding
import com.example.wb_week_8_3.model.data.CatModel
import com.example.wb_week_8_3.model.data.VoteModel
import com.example.wb_week_8_3.utils.showSnackBar
import com.example.wb_week_8_3.viewmodel.AppStateCat
import com.example.wb_week_8_3.viewmodel.MainViewModel


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataCat: CatModel
    private val subId = "moondi"

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.getData()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
            initButton()
        }
        return binding.root
    }

    private fun initButton() {
        binding.ivLike.setOnClickListener {
            if (this::dataCat.isInitialized) {
                postLike()
            } else
                Toast.makeText(context, R.string.post_error, Toast.LENGTH_SHORT).show()
        }
        binding.ivDisLike.setOnClickListener {
            viewModel.getCatFromRemoteSource()
        }
    }

    private fun postLike() {
        val vote = VoteModel(dataCat.id, subId)
        viewModel.postLikeToServer(vote)
        viewModel.saveFavoriteCatToDB(dataCat)
        viewModel.getCatFromRemoteSource()
    }

    private fun renderData(appStateCat: AppStateCat) {
        with(binding) {
            with(includedLoadingLayout) {
                when (appStateCat) {
                    is AppStateCat.Success -> {
                        loadingLayout.visibility = View.GONE
                        dataCat = appStateCat.dataCat
                        val image = appStateCat.dataCat.url
                        binding.ivCat.setImageURI(image)
                    }
                    is AppStateCat.Loading -> {
                        loadingLayout.visibility = View.VISIBLE
                    }
                    is AppStateCat.Error -> {
                        loadingLayout.visibility = View.GONE
                        mainFragmentRootView.showSnackBar(
                            getString(R.string.error),
                            getString(R.string.reload),
                            {
                                viewModel.getCatFromRemoteSource()
                            }
                        )

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

