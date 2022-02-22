package com.chaitupenju.compospractis

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
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.chaitupenju.compospractis.models.*
import com.chaitupenju.compospractis.repository.DataRepository
import com.chaitupenju.compospractis.ui.theme.BackBlue
import com.chaitupenju.compospractis.ui.theme.CardBack
import com.chaitupenju.compospractis.ui.theme.ComposPractisTheme
import com.chaitupenju.compospractis.ui.theme.TextWhite
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposPractisTheme {
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

    Column(modifier = Modifier
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
                    items (dataRepository.weekWeatherInfo) { weatherWeekItem ->
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
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(bottom = 4.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
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
                modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
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

@Composable
fun WeatherOtherDetailsColumnComponent(weatherOtherInfo: WeatherOtherInfo) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = weatherOtherInfo.infoIcon),
                contentDescription = "Text icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Text(
                text = weatherOtherInfo.infoTitle,
                fontWeight = FontWeight.Bold,
                color = TextWhite,
                fontSize = 16.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = weatherOtherInfo.infoValue,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFD3D3D3)
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposPractisTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackBlue,
        ) {
//            WeatherUI("Arora Nagar")
        }
    }
}