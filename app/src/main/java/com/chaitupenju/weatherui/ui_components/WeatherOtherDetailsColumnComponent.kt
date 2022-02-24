package com.chaitupenju.weatherui.ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chaitupenju.weatherui.R
import com.chaitupenju.weatherui.models.WeatherOtherInfo
import com.chaitupenju.weatherui.ui.theme.BackBlue
import com.chaitupenju.weatherui.ui.theme.WeatherUITheme
import com.chaitupenju.weatherui.ui.theme.TextWhite

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

@Preview(showSystemUi = false)
@Composable
fun ODCCPreview() {
    WeatherUITheme {
        Surface(
            modifier = Modifier.wrapContentSize(),
            color = BackBlue,
        ) {
            WeatherOtherDetailsColumnComponent(
                WeatherOtherInfo(
                    infoIcon = R.drawable.icon_pollen,
                    infoTitle = "Pollen",
                    infoValue = "Low"
                )
            )
        }
    }
}