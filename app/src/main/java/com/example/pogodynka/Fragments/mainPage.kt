package com.example.pogodynka.Fragments



import io.reactivex.rxjava3.core.*
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.beust.klaxon.Klaxon
import com.example.pogodynka.MainActivity
import com.example.pogodynka.Model.DataWeather
import com.example.pogodynka.R
import com.example.pogodynka.ViewModels.MainPageViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

import com.example.pogodynka.Fragments.mainPageDirections

class mainPage : Fragment() {

    private lateinit var viewModel: MainPageViewModel
    lateinit var url : URL
    lateinit var jsonText : String
    var townInput: String = "Gliwice"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_page_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.nextButton).apply {
            setOnClickListener {

                    townInput = view.findViewById<EditText>(R.id.writelocationEditText).text.toString()
                    Single.fromCallable {
                        url =
                            URL("https://api.openweathermap.org/data/2.5/weather?q=$townInput,pl&APPID=8f53320eb8fa017ea0be58db047263bd&lang=pl&units=metric")
                        val connection = url.openConnection()
                        return@fromCallable connection.getInputStream()
                            .bufferedReader(Charset.forName("UTF-8")).readLine()
                    }
                        .subscribeOn(Schedulers.io())

                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ text ->
                            jsonText = text
                            println(jsonText)


                            val result = Klaxon()
                                .parse<DataWeather>(jsonText)


                           if (result != null) {
                                println(result.weather[0].description)

                                if (!view.findViewById<ToggleButton>(R.id.toggleButton).isChecked) {
                                    val action = mainPageDirections
                                        .actionMainPageToWeatherPage(
                                            townInput,
                                            result.weather[0].description,
                                            result.main.pressure.toString(),
                                            result.sys.sunrise.toString(),
                                            result.sys.sunset.toString(),
                                            result.main.temp.toInt().toString(),
                                            result.weather[0].icon


                                        )
                                    view.findNavController().navigate(action)


                                } else {
                                    val action = mainPageDirections
                                        .actionMainPageToLargeWeatherPage(
                                            townInput,
                                            result.main.temp.toInt().toString(),
                                            result.weather[0].description,
                                            result.sys.sunrise.toString(),
                                            result.sys.sunset.toString(),
                                            result.main.pressure.toString(),
                                            result.weather[0].icon

                                        )
                                    view.findNavController().navigate(action)
                                }
                            }

                        }, {
                            it.printStackTrace()
                        })






            }

        }

    }



}