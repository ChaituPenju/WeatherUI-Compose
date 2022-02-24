package com.chaitupenju.weatherui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.chaitupenju.weatherui.R
import com.chaitupenju.weatherui.repository.DataRepository
import com.chaitupenju.weatherui.ui.theme.BackBlue
import com.chaitupenju.weatherui.ui.theme.CardBack
import com.chaitupenju.weatherui.ui.theme.TextWhite
import com.chaitupenju.weatherui.ui.theme.WeatherUITheme
import com.chaitupenju.weatherui.ui_components.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackBlue,
                ) {
                    WeatherUI("Arora Nagar")
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun WeatherUI(locationName: String) {
    val dataRepository = DataRepository()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
    ) {
        // Icon Content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                Icons.Default.Menu, contentDescription = "Menu",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(48.dp, 60.dp)
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        // Weather Information content
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "32°",
                fontFamily = FontFamily.Monospace,
                fontSize = 60.sp,
                style = TextStyle(
                    textAlign = TextAlign.Start,
                    letterSpacing = 0.1.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Thin,
                    color = TextWhite
                ),
                modifier = Modifier.padding(start = 28.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.sun_image),
                contentDescription = "Sun Image",
                modifier = Modifier
                    .size(120.dp)
                    .padding(end = 10.dp)
            )

        }

        // Location of the Weather
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp)
        ) {
            Text(
                text = locationName,
                fontFamily = FontFamily.Serif,
                fontSize = 24.sp,
                fontWeight = FontWeight.W200,
                color = TextWhite
            )
            Icon(
                Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = TextWhite
            )
        }

        // Description of Weather
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        )
        {
            Column {
                Text(
                    fontFamily = FontFamily.Serif,
                    text = "33° / 20° Feels like 32°",
                    color = TextWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 24.dp)
                )

                Text(
                    fontFamily = FontFamily.Serif,
                    text = "Fri, ${SimpleDateFormat("hh:mm aa", Locale.ENGLISH).format(Date())}",
                    color = TextWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(32.dp))

        // Throughout Day Weather Information
        Card(
            shape = RoundedCornerShape(24.dp),
            backgroundColor = CardBack,
            contentColor = TextWhite,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.30f)
                .padding(horizontal = 16.dp)
        ) {
            LazyRow(
                modifier = Modifier.padding(8.dp),
                state = LazyListState(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dataRepository.weatherTimeTemps) { weatherItem ->
                    WeatherTimeRowComponent(weatherItem = weatherItem)
                }
            }
        }

        Spacer(modifier = Modifier.padding(all = 4.dp))

        // Information about tomorrow's temperature
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(80.dp)
                .fillMaxWidth(),
            backgroundColor = CardBack,
            contentColor = TextWhite,
            shape = RoundedCornerShape(24.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Tomorrow's Temperature"
                )
                Spacer(
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Except the same as today"
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        // Information about coming weeks
        Row {
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(250.dp)
                    .fillMaxWidth(),
                backgroundColor = CardBack,
                contentColor = TextWhite,
                shape = RoundedCornerShape(24.dp),
                elevation = 4.dp
            ) {
                LazyColumn(
                    state = LazyListState(),
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    items(dataRepository.weekWeatherInfo) { weatherWeekItem ->
                        WeatherWeekColumnComponent(weatherWeek = weatherWeekItem)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        // Information about Sunrise and Sunset
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            backgroundColor = CardBack,
            shape = RoundedCornerShape(24.dp),
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherSunriseSetComponent(
                    riseOrSet = dataRepository.sunriseSunsetInfo[0]
                )
                WeatherSunriseSetComponent(
                    riseOrSet = dataRepository.sunriseSunsetInfo[1]
                )
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        // Information about UV index, wind and humidity
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            backgroundColor = CardBack,
            shape = RoundedCornerShape(24.dp),
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherUVWindHumidComponent(weatherUVWindHumidity = dataRepository.uvWindHumidityInfo[0])
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 24.dp)
                        .width(1.dp)
                )
                WeatherUVWindHumidComponent(weatherUVWindHumidity = dataRepository.uvWindHumidityInfo[1])
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 24.dp)
                        .width(1.dp)
                )
                WeatherUVWindHumidComponent(weatherUVWindHumidity = dataRepository.uvWindHumidityInfo[2])

            }
        }

        // Information about other weather parameters
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(150.dp)
                .fillMaxWidth(),
            backgroundColor = CardBack,
            shape = RoundedCornerShape(24.dp),
            elevation = 4.dp
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                state = rememberLazyListState(),
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                items(dataRepository.weatherOtherInfos) { weatherOtherInfo ->
                    WeatherOtherDetailsColumnComponent(weatherOtherInfo = weatherOtherInfo)
                }
            }

        }

        // Name, date and time of the data
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(bottom = 4.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_joker),
                    contentDescription = "Joker image",
                    modifier = Modifier.size(size = 16.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = "Weather UI - Chaitanya Penjuri",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextWhite
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Updated 2/27 4:46 PM",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextWhite
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    WeatherUITheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackBlue,
        ) {
            WeatherUI("Arora Nagar")
        }
    }
}