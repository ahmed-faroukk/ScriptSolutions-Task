package com.cyberdunkers.script_solution_task.presentation.home.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cyberdunkers.script_solution_task.presentation.AppCompnents.CircularIcon

@Composable
fun ListItem(icon : Int  , firstName : String , lastName : String ){
    Column {
        val color = if (isSystemInDarkTheme()) Color.White else Color.Black

        Row {

            CircularIcon(resID = icon, onClick = { /*TODO*/ })
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "$firstName ", fontWeight = FontWeight.Bold , color = color )
            Text(text = lastName  , fontWeight = FontWeight.Bold , color = color )



        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(color = color)
        Spacer(modifier = Modifier.height(5.dp))

    }
}