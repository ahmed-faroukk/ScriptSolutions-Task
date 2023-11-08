package com.cyberdunkers.script_solution_task.presentation.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyberdunkers.script_solution_task.R
import com.cyberdunkers.script_solution_task.presentation.AppCompnents.CustomLoading
import com.cyberdunkers.script_solution_task.presentation.AppCompnents.OutLineTextFieldString
import com.cyberdunkers.script_solution_task.presentation.home.ImagesList.Companion.listOfImages
import com.cyberdunkers.script_solution_task.presentation.home.components.CardItem
import com.cyberdunkers.script_solution_task.presentation.home.components.ImagesIndicator
import com.cyberdunkers.script_solution_task.presentation.home.components.ListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val searchText = viewModel.searchText.collectAsState()
    val persons = viewModel.persons.collectAsState()
    val isSearching = viewModel.isSearching.collectAsState()
    val icon = remember {
        mutableIntStateOf(R.drawable.we)
    }
    val lazyListState = rememberLazyListState()
    LazyColumn(
        Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
            .fillMaxSize(),
        state = lazyListState
    ) {
        item {
            CardSection(icon)
        }
        stickyHeader {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutLineTextFieldString(
                    value = searchText.value,
                    label = "search ",
                    onNameChange = viewModel::onSearchTextChange, modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(25.dp))
                if (isSearching.value) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(20.dp))
                        CustomLoading(modifier = Modifier.align(Alignment.Center))
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
            }

        }

        items(persons.value) { person ->
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ListItem(
                    icon = icon.intValue,
                    firstName = person.firstName,
                    lastName = person.lastName
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardSection(myIcon: MutableState<Int>) {
    val pageState = rememberPagerState(
        pageCount = { listOfImages.size }
    )
    rememberCoroutineScope()


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


            if (pageState.currentPage == index) {
                CardItem(
                    img = listOfImages[index].image,
                    title = listOfImages[index].title,
                    modifier = Modifier.graphicsLayer {
                        scaleX = imageSize.value
                        scaleY = imageSize.value
                    }
                )
                if (index == 0)
                    myIcon.value = R.drawable.we
                else if (index == 1)
                    myIcon.value = R.drawable.vodafone
                else
                    myIcon.value = R.drawable.orange

            }
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



