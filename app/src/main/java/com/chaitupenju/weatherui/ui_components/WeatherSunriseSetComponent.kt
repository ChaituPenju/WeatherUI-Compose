package com.chaitupenju.weatherui.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import com.chaitupenju.weatherui.R
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chaitupenju.weatherui.models.WeatherSunsetAndRise
import com.chaitupenju.weatherui.ui.theme.BackBlue
import com.chaitupenju.weatherui.ui.theme.WeatherUITheme
import com.chaitupenju.weatherui.ui.theme.TextWhite

@Composable
fun WeatherSunriseSetComponent(riseOrSet: WeatherSunsetAndRise) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = riseOrSet.sunriseOrSet,
            color = TextWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = riseOrSet.riseOrSetTime,
            color = TextWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.padding(all = 16.dp))
        Image(
            painter = painterResource(id = riseOrSet.riseOrSetIcon),
            contentDescription = "Sunrise Icon",
            modifier = Modifier.size(size = 128.dp)
        )
    }
}

@Preview(showSystemUi = false)
@Composable
fun SSCPreview() {
    WeatherUITheme {
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = BackBlue,
        ) {
            WeatherSunriseSetComponent(
                WeatherSunsetAndRise(
                    "Sunrise", "6:40 AM", R.drawable.icon_sunrise
                )
            )
        }
    }
}