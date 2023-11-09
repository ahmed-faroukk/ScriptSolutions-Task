package com.cyberdunkers.script_solution_task.presentation.home

import com.cyberdunkers.script_solution_task.R

data class ImagesList(
    val image: Int ,
    val title: String
){
    companion object{
        val listOfImages = mutableListOf(
            ImagesList(R.drawable.we , "we"),
            ImagesList(R.drawable.vodafone , "vodafone"),
            ImagesList(R.drawable.orange , "orange"),
        )
    }
}
