package com.cyberdunkers.script_solution_task.presentation.AppCompnents

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.cyberdunkers.script_solution_task.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldString(
    onNameChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    labelColor: Color = Color.Gray,
    value: String,
) {

    val color = if (isSystemInDarkTheme()) Color.LightGray else Color.Black
    val color2 = if (isSystemInDarkTheme()) Color.LightGray else Color.White
    var text by remember { mutableStateOf(TextFieldValue(value)) }

    OutlinedTextField(
        value = text,
        label = { Text(text = label, color = labelColor) },
        onValueChange = {
            text = it
            onNameChange(it.text)
        }, colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = color,
            unfocusedIndicatorColor = labelColor,
            containerColor = color2 ,
            cursorColor = color,
            textColor = Color.Black

        ), modifier = modifier, singleLine = true
    )
}
@Composable
fun CustomLoading(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp,
) {
    val color = if (isSystemInDarkTheme()) Color.LightGray else Color.Black

    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEachIndexed() { index, value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = color,
                        shape = CircleShape
                    )
            )
        }
    }

}

@Composable
fun CircularIcon(
    resID: Int,
    background: Color = Color.White,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imgSizeWithDP: Dp = 50.dp,
) {
    val color = if (isSystemInDarkTheme()) Color.DarkGray else Color.White

    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .background(color)
    ) {

        val iconPainter = painterResource(resID)
        Image(
            painter = iconPainter,
            contentDescription = null, // Provide a meaningful description if needed
            modifier = Modifier
                .size(imgSizeWithDP)
                .padding(8.dp)
                .clip(CircleShape) // Adjust the size as needed
            , contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun InfoDialog(
    title: String? = "Message",
    desc: String? = "Your Message",
    imgId: Int = R.drawable.nointernet,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = Color.Transparent,
                )
        ) {


            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                    )
                    .align(Alignment.BottomCenter),
            ) {

                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(),

                    )
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))

                    //.........................Text: title
                    Text(
                        text = title!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 130.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    //.........................Text : description
                    Text(
                        text = desc!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))

                    //.........................Button : OK button
                    RoundedBtn(
                        onClick = { onDismiss() },
                        text = "ok",
                        textColor = Color.White,
                        buttonColor = Color.Black
                    )


                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))

                }


            }

        }
    }
}

@Composable
fun RoundedBtn(
    onClick: () -> Unit,
    text: String,
    textColor: Color = Color.White,
    buttonColor: Color = Color.Black,
    modifier: Modifier = Modifier,
    textSizeSp: Int = 15,
) {

    Button(
        onClick = {
            onClick()
        }, shape = RoundedCornerShape(100.dp), colors = ButtonDefaults.buttonColors(
            buttonColor
        ), modifier = modifier
            .width(172.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

