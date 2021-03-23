package ru.myproject.android_2_kotlin.view.details

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_details.*
import okhttp3.*
import ru.myproject.android_2_kotlin.BuildConfig
import ru.myproject.android_2_kotlin.R
import ru.myproject.android_2_kotlin.model.Weather
import ru.myproject.android_2_kotlin.databinding.FragmentDetailsBinding
import ru.myproject.android_2_kotlin.model.WeatherDTO
import java.io.IOException

private const val PROCESS_ERROR = "Обработка ошибки"

// когда тариф "Тестовый" закончится, необходимо будет в запросе forecast поменять на informers
private const val MAIN_LINK = "https://api.weather.yandex.ru/v2/forecast?"
private const val REQUEST_API_KEY = "X-Yandex-API-Key"

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        getWeather()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getWeather() {
        mainView.visibility = View.GONE
        loadingLayout.visibility = View.VISIBLE
        val client = OkHttpClient() // Клиент
        val builder: Request.Builder = Request.Builder() // Создаём строителя запроса
        builder.header(REQUEST_API_KEY, BuildConfig.WEATHER_API_KEY) // Создаём заголовок запроса
        builder.url(MAIN_LINK + "lat=${weatherBundle.city.lat}&lon=${weatherBundle.city.lon}") // Формируем URL
        val request: Request = builder.build() // Создаём запрос
        val call: Call = client.newCall(request) // Ставим запрос в очередь и отправляем
        call.enqueue(object : Callback {
            val handler: Handler = Handler()

            // Вызывается, если ответ от сервера пришёл
            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                val serverResponse: String? = response.body()?.string()
                // Синхронизируем поток с потоком UI
                if (response.isSuccessful && serverResponse != null) {
                    handler.post {
                        renderData(Gson().fromJson(serverResponse, WeatherDTO::class.java))
                    }
                } else {
                    TODO(PROCESS_ERROR)
                }
            }

            // Вызывается при сбое в процессе запроса на сервер
            override fun onFailure(call: Call?, e: IOException?) {
                TODO(PROCESS_ERROR)
            }
        })
    }

    private fun renderData(weatherDTO: WeatherDTO) {
        binding.mainView.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE
        val fact = weatherDTO.fact
        if (fact == null || fact.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
            TODO(PROCESS_ERROR)
        } else {
            val city = weatherBundle.city
            binding.cityName.text = city.city
            binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            binding.temperatureValue.text = fact.temp.toString()
            binding.feelsLikeValue.text = fact.feels_like.toString()
            binding.weatherCondition.text = fact.condition
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}