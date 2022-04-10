package com.example.pogodynka.Fragments

import android.os.Build
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
import com.example.pogodynka.ViewModels.LargeWeatherPageViewModel
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class large_weather_page : Fragment() {

    companion object {
        fun newInstance() = large_weather_page()
    }

    private lateinit var viewModel: LargeWeatherPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.large_weather_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tempFormat = StringBuilder(((arguments?.get("Temperature") ?: String) as CharSequence))

        val current = "06.04.2022"
        tempFormat.append("℃")
        val pressure = StringBuilder(((arguments?.get("Pressure") ?: String) as CharSequence))
        pressure.append(" hPA")

        view.findViewById<TextView>(R.id.dateTextView)?.text = current
        view.findViewById<TextView>(R.id.temperatureTextView)?.text = tempFormat
        view.findViewById<TextView>(R.id.locationTextView)?.text = ((arguments?.get("Town") ?: String) as CharSequence?)
        //view?.findViewById<TextView>(R.id.weatherIcon)?.text = ((arguments?.get("Temperature") ?: String) as CharSequence?)
        view.findViewById<TextView>(R.id.shortDescription)?.text = ((arguments?.get("Description") ?: String) as CharSequence?)
        view.findViewById<TextView>(R.id.pressureTextView)?.text = pressure

        view.findViewById<Button>(R.id.backLargeButton).apply {
            setOnClickListener{
                view.findNavController().navigate(R.id.action_large_weather_page_to_mainPage)

            }


        }

    }

}