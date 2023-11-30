package com.example.jetpackdesign.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.jetpackdesign.component.AnimatedText
import com.example.jetpackdesign.component.CustomCalender
import com.example.jetpackdesign.component.ExposedDropdownMenuSample
import com.example.jetpackdesign.component.MainMonthView
import com.example.jetpackdesign.util.Util

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TestScreen(controller: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        //CustomCalender()
        MainMonthView(controller)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Test1Screen() {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomCalender()
    }
}