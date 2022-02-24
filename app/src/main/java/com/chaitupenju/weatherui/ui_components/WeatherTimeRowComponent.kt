package com.chaitupenju.weatherui.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chaitupenju.weatherui.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chaitupenju.weatherui.models.WeatherHourly
import com.chaitupenju.weatherui.ui.theme.BackBlue
import com.chaitupenju.weatherui.ui.theme.WeatherUITheme
import kotlin.random.Random

@Composable
fun WeatherTimeRowComponent(weatherItem: WeatherHourly) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = weatherItem.time.plus("AM"),
            )

            Image(
                painter = painterResource(id = weatherItem.icon),
                contentDescription = "Weather Status",
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .size(24.dp)
            )

            Text(
                text = weatherItem.temp.plus("°")
            )
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun TRCPreview() {
    WeatherUITheme {
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = BackBlue,
        ) {
            WeatherTimeRowComponent(
                WeatherHourly(
                    time = "10:30",
                    icon = R.drawable.icon_cloud,
                    temp = Random.nextInt(from = 18, until = 33).toString()
                )
            )
        }
    }
}