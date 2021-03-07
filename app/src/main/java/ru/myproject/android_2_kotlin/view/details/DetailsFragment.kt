//package ru.myproject.android_2_kotlin.view
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import ru.myproject.android_2_kotlin.R
//import ru.myproject.android_2_kotlin.databinding.FragmentMainBinding
//import ru.myproject.android_2_kotlin.model.Weather
//import ru.myproject.android_2_kotlin.viewmodel.AppState
//import ru.myproject.android_2_kotlin.viewmodel.MainViewModel
//import com.google.android.material.snackbar.Snackbar
//
//class DetailsFragment : Fragment() {
//
//    private var _binding: FragmentMainBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var viewModel: MainViewModel
//
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentMainBinding.inflate(inflater, container, false)
//        return binding.getRoot()
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
//        viewModel.getWeatherFromLocalSource()
//    }
//
//    private fun renderData(appState: AppState) {
//        when (appState) {
//            is AppState.Success -> {
//                val weatherData = appState.weatherData
//                binding.loadingLayout.visibility = View.GONE
//                setData(weatherData)
//            }
//            is AppState.Loading -> {
//                binding.loadingLayout.visibility = View.VISIBLE
//            }
//            is AppState.Error -> {
//                binding.loadingLayout.visibility = View.GONE
//                Snackbar
//                        .make(binding.mainView, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
//                        .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSource() }
//                        .show()
//            }
//        }
//    }
//
//    private fun setData(weatherData: Weather) {
//        binding.cityName.text = weatherData.city.city
//        binding.cityCoordinates.text = String.format(
//                getString(R.string.city_coordinates),
//                weatherData.city.lat.toString(),
//                weatherData.city.lon.toString()
//        )
//        binding.temperatureValue.text = weatherData.temperature.toString()
//        binding.feelsLikeValue.text = weatherData.feelsLike.toString()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    companion object {
//        fun newInstance() = DetailsFragment()
//    }
//}