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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chaitupenju.weatherui.models.*
import com.chaitupenju.weatherui.ui.theme.BackBlue
import com.chaitupenju.weatherui.ui.theme.WeatherUITheme
import com.chaitupenju.weatherui.ui.theme.TextWhite

@Composable
fun WeatherUVWindHumidComponent(weatherUVWindHumidity: WeatherUVWindHumidity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(id = weatherUVWindHumidity.uwhIcon),
            contentDescription = "The Icon",
            modifier = Modifier.size(size = 64.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = weatherUVWindHumidity.uwhTitle,
            color = TextWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = weatherUVWindHumidity.uwhValue,
            color = TextWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showSystemUi = false)
@Composable
fun UWHCPreview() {
    WeatherUITheme {
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = BackBlue,
        ) {
            WeatherUVWindHumidComponent(
                WeatherUVWindHumidity(
                    uwhIcon = R.drawable.icon_uv,
                    uwhTitle = "UV index",
                    uwhValue = "Extreme"
                )
            )
        }
    }
}