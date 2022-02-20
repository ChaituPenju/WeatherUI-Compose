package com.chaitupenju.compospractis.models

import androidx.annotation.DrawableRes

data class WeatherHourly(
    val time: String,
    @DrawableRes val icon: Int,
    val temp: String
)
