package com.chaitupenju.weatherui.models

import androidx.annotation.DrawableRes

data class WeatherWeekly(
    val weekName: String,
    val precipitation: Int,
    @DrawableRes val minIcon: Int,
    @DrawableRes val maxIcon: Int,
    val minTemp: Int,
    val maxTemp: Int
)
