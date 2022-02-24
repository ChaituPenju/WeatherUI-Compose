package com.chaitupenju.weatherui.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.chaitupenju.weatherui.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chaitupenju.weatherui.models.WeatherWeekly
import com.chaitupenju.weatherui.ui.theme.BackBlue
import com.chaitupenju.weatherui.ui.theme.WeatherUITheme
import com.chaitupenju.weatherui.ui.theme.TextWhite
import kotlin.random.Random

@Composable
fun WeatherWeekColumnComponent(weatherWeek: WeatherWeekly) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = weatherWeek.weekName
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_water_drop),
                contentDescription = "Water drop icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Image(
                painter = painterResource(id = weatherWeek.maxIcon),
                contentDescription = "Max icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Image(
                painter = painterResource(id = weatherWeek.minIcon),
                contentDescription = "Max icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                text = "${weatherWeek.maxTemp}".plus("°")
            )
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                text = "${weatherWeek.minTemp}".plus("°")
            )
        }
    }

}

@Preview(showSystemUi = false)
@Composable
fun WCCPreview() {
    WeatherUITheme {
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = BackBlue,
        ) {
            WeatherWeekColumnComponent(
                WeatherWeekly(
                    weekName = "Monday",
                    precipitation = Random.nextInt(from = 0, until = 14),
                    minIcon = R.drawable.icon_cloud,
                    maxIcon = R.drawable.icon_sun,
                    minTemp = Random.nextInt(from = 18, until = 25),
                    maxTemp = Random.nextInt(from = 29, until = 38)
                )
            )
        }
    }
}