package com.example.jetpackdesign.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpackdesign.component.AnimatedText
import com.example.jetpackdesign.component.ExposedDropdownMenuSample

@Composable
fun TestScreen() {
    Box(modifier = Modifier.fillMaxSize()){
        //AnimatedText()
        ExposedDropdownMenuSample()
    }
}