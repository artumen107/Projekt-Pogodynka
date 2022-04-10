package com.example.pogodynka.Fragments

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.pogodynka.R
import com.example.pogodynka.ViewModels.WeatherPageViewModel
import com.squareup.picasso.Picasso
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class weatherPage : Fragment() {


    companion object {
        fun newInstance() = weatherPage()
    }

    private lateinit var viewModel: WeatherPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.weather_page_fragment, container, false)

    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tempFormat = StringBuilder(((arguments?.get("Temperature") ?: String) as CharSequence))
        tempFormat.append("℃")


        val current = "06.04.2022"




        val pressure = StringBuilder(((arguments?.get("Pressure") ?: String) as CharSequence))
        pressure.append(" hPA")

        view.findViewById<TextView>(R.id.temperatureTextViewS)?.text = tempFormat
        view.findViewById<TextView>(R.id.dateTextViewS)?.text = current
        view.findViewById<TextView>(R.id.locationTextViewS)?.text = ((arguments?.get("Town") ?: String) as CharSequence?)
        //view?.findViewById<TextView>(R.id.weatherIcon)?.text = ((arguments?.get("Temperature") ?: String) as CharSequence?)
        view.findViewById<TextView>(R.id.shortDescriptionS)?.text = ((arguments?.get("Description") ?: String) as CharSequence?)
        view.findViewById<TextView>(R.id.pressureTextViewS)?.text = pressure
        Picasso.get().load("https://openweathermap.org/img/wn/"+((arguments?.get("Icon") ?: String) as CharSequence?)+"@2x.png").into(view.findViewById<ImageView>(R.id.weatherIconS))

        view.findViewById<Button>(R.id.backButtonS).apply {
            setOnClickListener{
                view.findNavController().navigate(R.id.action_weatherPage_to_mainPage)

            }


        }

    }

}