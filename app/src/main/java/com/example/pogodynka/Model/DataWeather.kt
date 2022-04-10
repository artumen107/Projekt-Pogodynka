package com.example.pogodynka.Model

class Weather (

    val description: String,
    val icon: String,
        )

class Main (

    val temp: Double,
    val pressure: Int
        )
class Sys (

    val sunrise: Int,
    val sunset: Int,
    )

class DataWeather (

    val weather: ArrayList<Weather>,
    val sys: Sys,
    val main: Main


        )
