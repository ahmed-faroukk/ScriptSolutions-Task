package com.cyberdunkers.script_solution_task.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cyberdunkers.script_solution_task.presentation.AppCompnents.CircularIcon

@Composable
fun ListItem(icon : Int  , firstName : String , lastName : String ){
    Column {
        Row {
            CircularIcon(resID = icon, onClick = { /*TODO*/ })
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "$firstName ", fontWeight = FontWeight.Bold )
            Text(text = lastName  , fontWeight = FontWeight.Bold )



        }
        Divider()
    }
}