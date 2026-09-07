package com.example.newsapp.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.PlusJakartaSans
import com.example.newsapp.ui.theme.Primary
import com.example.newsapp.ui.theme.Secondary
import kotlinx.coroutines.launch

data class OnboardingItem(val title: String, val description: String, val image: Int)

@Composable
fun OnboardingScreen(
    onFinished: () -> Unit
) {

    val pages = listOf(

        OnboardingItem(
            image = R.drawable.screen1,
            title = "Tailored Feeds &\n" +
                    "Distraction-Free Reading",
            description = "Bookmark your favorite stories, organize\n" +
                    "curated reading lists, and read anytime,\n" +
                    "anywhere without noise."
        ),

        OnboardingItem(
            image = R.drawable.screen,
            title = "Stay Informed with\n" +
                    "Trusted Global Journalism",
            description = "Curated daily breaking news, deep analytical\n" +
                    "reporting, and verified stories tailored for your\n" +
                    "daily routine."
        ),
    )

    val pagerState = rememberPagerState(
        pageCount = {
            pages.size
        }
    )

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->

            OnBoardingPage(
                image = pages[page].image,
                title = pages[page].title,
                description = pages[page].description,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pages.size) { index ->
                Text(
                    text = if (pagerState.currentPage == index) "●" else "○",
                    modifier = Modifier.padding(4.dp),
                    color = Primary
                )
            }
        }
        Spacer(modifier = Modifier.height(7.dp))
        Button(
            onClick = {
                if (pagerState.currentPage < pages.lastIndex) {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                } else {
                    onFinished()
                }
            },
            colors = ButtonColors(
                containerColor = Primary,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black
            ),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth()
                .size(54.dp)
                .shadow(
                    elevation = 7.dp,
                    shape = RoundedCornerShape(18.dp),
                    spotColor = Secondary
                )
        ) {
            Text(
                text = if (pagerState.currentPage == pages.lastIndex)
                    "Get Started"
                else
                    "Next",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                fontFamily = PlusJakartaSans,
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}