package com.besterinulove.kotlin.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.besterinulove.kotlin.weather.Inject
import com.besterinulove.kotlin.weather.databinding.FragmentDetailWeatherBinding

class DetailWeatherFragment : Fragment() {

    lateinit var binding: FragmentDetailWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel =
            ViewModelProvider(
                viewModelStore,
                Inject.provideDetailViewModelFactory(requireContext())
            ).get(
                DetailViewModel::class.java
            )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        arguments?.let {
            val index = DetailWeatherFragmentArgs.fromBundle(it).index
            viewModel.getElementWithIndex(index)
        }

    }
}
