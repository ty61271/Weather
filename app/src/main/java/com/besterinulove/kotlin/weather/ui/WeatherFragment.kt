package com.besterinulove.kotlin.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.besterinulove.kotlin.weather.Inject
import com.besterinulove.kotlin.weather.databinding.FragmentWeatherBinding
import com.besterinulove.kotlin.weather.db.WeatherDatabase
import com.besterinulove.kotlin.weather.model.Locations
import com.besterinulove.kotlin.weather.ui.adapter.WeatherAdapter
import kotlinx.coroutines.launch

class WeatherFragment : Fragment() {

    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWeatherBinding.inflate(layoutInflater)
        weatherAdapter = WeatherAdapter(this::onClicked)
        with(binding.weatherRecycler) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = weatherAdapter
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val locations=
                WeatherDatabase.getInstance(requireContext()).getWeatherLocationDao().getAll()
            if (locations.isNotEmpty()){
                Toast.makeText(requireContext(),"歡迎回來",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel =
            ViewModelProvider(
                viewModelStore,
                Inject.provideWeatherViewModelFactory(requireContext())
            ).get(
                WeatherViewModel::class.java
            )

        viewModel.getWeatherByLocationName(Locations.TaiPei.locationName)
        viewModel.weather.observe(viewLifecycleOwner, Observer {
            weatherAdapter.submit(it)
        })
    }

    private fun onClicked(index: Int) {
        findNavController().navigate(
            WeatherFragmentDirections.actionWeatherFragmentToDetailWeatherFragment(index)
        )
    }
}
