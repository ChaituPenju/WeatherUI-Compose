package com.chaitupenju.compospractis.models

import androidx.annotation.DrawableRes

data class WeatherSunsetAndRise(
    val sunriseOrSet: String,
    val riseOrSetTime: String,
    @DrawableRes val riseOrSetIcon: Int
)