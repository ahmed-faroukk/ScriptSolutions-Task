package com.cyberdunkers.script_solution_task.presentation.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cyberdunkers.script_solution_task.presentation.Greeting
import com.cyberdunkers.script_solution_task.presentation.home.ImagesList.Companion.listOfImages
import com.cyberdunkers.script_solution_task.presentation.home.components.CardItem
import com.cyberdunkers.script_solution_task.presentation.home.components.ImagesIndicator
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    LazyColumn(
        Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .fillMaxSize()
    ) {
        item {
            CardSection()
        }
        item {

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardSection() {
    val pageState = rememberPagerState(
        pageCount = { listOfImages.size }
    )
    val scope = rememberCoroutineScope()


    Column(
        Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pageState,
        ) { index ->
            val cardOffset =
                (pageState.currentPage - index) + pageState.currentPageOffsetFraction
            val imageSize = animateFloatAsState(
                targetValue = if (cardOffset != 0.0f) 0.90f else 1f,
                animationSpec = tween(300), label = ""
            )

            if (pageState.currentPage == index)
                CardItem(
                    img = listOfImages[index].image,
                    title = listOfImages[index].title,
                    modifier = Modifier.graphicsLayer {
                        scaleX = imageSize.value
                        scaleY = imageSize.value
                    }
                )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .navigationBarsPadding(), horizontalArrangement = Arrangement.Center
        ) {
            ImagesIndicator(
                modifier = Modifier.width(80.dp),
                pageSize = listOfImages.size,
                selectedPage = pageState.currentPage,
            )
        }
    }
}