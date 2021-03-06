package com.chaitupenju.weatherui.repository

import com.chaitupenju.weatherui.R
import com.chaitupenju.weatherui.models.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class DataRepository {

    private val iconsList: List<Int> = listOf(
        R.drawable.icon_cloud,
        R.drawable.icon_full_sun,
        R.drawable.icon_sun,
        R.drawable.icon_halfmoon,
    )

    internal val currentDateTime
        get() = SimpleDateFormat("m/yy, h:mm aa", Locale.ENGLISH).format(Date())

    internal val weatherTimeTemps
        get() = (1..12).map { i ->
            WeatherHourly(
                time = "$i:".plus("${30 * (i % 2)}".padStart(2, '0')),
                icon = iconsList[Random.nextInt(from = 0, until = iconsList.size - 1)],
                temp = Random.nextInt(from = 18, until = 33).toString()
            )
        }

    internal val weekWeatherInfo
        get() = (1..7).map { i ->
            WeatherWeekly(
                weekName = when(i) {
                    1 -> "Today"
                    2 -> "Tomorrow"
                    else -> SimpleDateFormat("EEEE", Locale.ENGLISH)
                        .format(Calendar.getInstance().also {
                            it.time = Date()
                            it.add(Calendar.DATE, i - 1)
                        }.time)
                },
                precipitation = Random.nextInt(from = 0, until = 14),
                minIcon = iconsList[Random.nextInt(from = 0, until = iconsList.size - 1)],
                maxIcon = iconsList[Random.nextInt(from = 0, until = iconsList.size - 1)],
                minTemp = Random.nextInt(from = 18, until = 25),
                maxTemp = Random.nextInt(from = 29, until = 38)
            )
        }

    internal val sunriseSunsetInfo
        get() = listOf(
            WeatherSunsetAndRise(
                "Sunrise", "6:40 AM", R.drawable.icon_sunrise
            ),
            WeatherSunsetAndRise(
                "Sunset", "6:22 AM", R.drawable.icon_sunset
            )
        )

    internal val uvWindHumidityInfo
        get() = listOf(
            WeatherUVWindHumidity(
                uwhIcon = R.drawable.icon_uv,
                uwhTitle = "UV index",
                uwhValue = "Extreme"
            ),
            WeatherUVWindHumidity(
                uwhIcon = R.drawable.icon_wind,
                uwhTitle = "Wind",
                uwhValue = "${Random.nextInt(from = 1, until = 14)} km/h"
            ),
            WeatherUVWindHumidity(
                uwhIcon = R.drawable.icon_humidity,
                uwhTitle = "Humidity",
                uwhValue = "${Random.nextInt(from = 4, until = 57)} %"
            )
        )

    internal val weatherOtherInfos
        get() = listOf(
            WeatherOtherInfo(
                infoIcon = R.drawable.icon_aqi,
                infoTitle = "AQI",
                infoValue = "Unhealthy for Sensitive Groups (107)"
            ),
            WeatherOtherInfo(
                infoIcon = R.drawable.icon_pollen,
                infoTitle = "Pollen",
                infoValue = "Low"
            ),
            WeatherOtherInfo(
                infoIcon = R.drawable.icon_driving,
                infoTitle = "Driving difficulty",
                infoValue = "None"
            ),
            WeatherOtherInfo(
                infoIcon = R.drawable.icon_running,
                infoTitle = "Running",
                infoValue = "Fair"
            )
        )
}