package com.example.jetpackdesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.jetpackdesign.R
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.databinding.FragmentHomeBinding
import com.example.jetpackdesign.screens.HomeScreen
import com.example.jetpackdesign.screens.PeopleCard

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)

        setContent {
            binding.homeCompose.apply {
                setContent {
                    Text(text = "hello", style = MaterialTheme.typography.headlineLarge)
                }
            }
        }

    }
}
