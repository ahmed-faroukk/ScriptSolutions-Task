package com.cyberdunkers.script_solution_task.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cyberdunkers.script_solution_task.presentation.home.ImagesList

@Composable
fun CardItem(modifier: Modifier = Modifier, img: Int, title: String) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(10.dp)
            .shadow(elevation = 20.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = title,
            Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Inside
        )
    }
}