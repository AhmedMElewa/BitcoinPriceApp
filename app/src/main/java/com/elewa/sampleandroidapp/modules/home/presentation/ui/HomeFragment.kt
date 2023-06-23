package com.elewa.sampleandroidapp.modules.home.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.elewa.sampleandroidapp.R
import com.elewa.sampleandroidapp.base.BaseFragment
import com.elewa.sampleandroidapp.core.extention.toGone
import com.elewa.sampleandroidapp.core.extention.toVisible
import com.elewa.sampleandroidapp.databinding.FragmentHomeBinding
import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity
import com.elewa.sampleandroidapp.modules.home.presentation.adapter.CoinDeskAdapter
import com.elewa.sampleandroidapp.modules.home.presentation.uimodel.CoinDeskState
import com.elewa.sampleandroidapp.modules.home.presentation.uimodel.CoinDeskUiModel
import com.elewa.sampleandroidapp.modules.home.presentation.viewmodel.CoinDeskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: CoinDeskViewModel by viewModels()

    @Inject
    lateinit var adapter: CoinDeskAdapter

    val dataList = ArrayList<CoinDeskUiModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservation()
        initErrorObservation()
    }

    private fun initView() {
        binding.layoutError.btnRetry.setOnClickListener {
            viewModel.getCurrentPrice()
        }
    }

    private fun initObservation() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.coinDeskMutable.collect { state ->
                    if (state.isLoading) {
                        binding.layoutLoading.clLoading.toVisible()
                    } else {
                        binding.layoutLoading.clLoading.toGone()
                    }

                    if (state.data != null) {
                        if(dataList.size < 50) {
                            dataList.add(state.data)
                        } else {
                            dataList.removeFirstOrNull()
                            dataList.add(state.data)
                        }

                        binding.layoutLoading.clLoading.toGone()
                        binding.layoutError.clError.toGone()
                        binding.recPriceList.toVisible()
                        adapter.submitList(dataList)
                        binding.recPriceList.adapter = adapter
                    }
                }
            }
        }
    }

    private fun initErrorObservation() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collectLatest { error ->
                    binding.layoutLoading.clLoading.toGone()
                    binding.layoutError.clError.toVisible()
                    binding.layoutError.tvError.setText(error)
                }
            }

        }
    }

}