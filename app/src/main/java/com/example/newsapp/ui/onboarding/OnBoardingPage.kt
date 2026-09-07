package com.example.newsapp.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.Background
import com.example.newsapp.ui.theme.PlayfairDisplay
import com.example.newsapp.ui.theme.PlusJakartaSans
import com.example.newsapp.ui.theme.Primary
import com.example.newsapp.ui.theme.TextSecondary


@Composable
fun OnBoardingPage(
    title: String,
    image: Int,
    description: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 25.dp,
                vertical = 25.dp,
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Text(
            text = "Newsly",
            color = Primary,
            fontFamily = PlusJakartaSans,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Background)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "header image",
                modifier = Modifier
                    .clip(RoundedCornerShape(9))
                    .padding(10.dp)
                    .background(Background)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(9.dp),
                        spotColor = Color.Black
                    )
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                color = Primary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontFamily = PlayfairDisplay,
                lineHeight = 30.sp
            )
            Text(
                text = description,
                color = TextSecondary,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                fontFamily = PlusJakartaSans,
                lineHeight = 20.sp
            )
        }
    }
}