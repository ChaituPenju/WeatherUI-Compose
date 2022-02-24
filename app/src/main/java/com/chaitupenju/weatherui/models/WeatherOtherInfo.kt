package com.chaitupenju.weatherui.models

import androidx.annotation.DrawableRes

data class WeatherOtherInfo(
    @DrawableRes val infoIcon: Int,
    val infoTitle: String,
    val infoValue: String
)
