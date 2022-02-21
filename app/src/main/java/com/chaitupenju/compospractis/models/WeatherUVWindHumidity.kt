package com.chaitupenju.compospractis.models

import androidx.annotation.DrawableRes

data class WeatherUVWindHumidity(
    val uwhTitle: String,
    val uwhValue: String,
    @DrawableRes val uwhIcon: Int
)