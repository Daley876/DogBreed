package com.example.dogbreedapp.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.dogbreedapp.R
import com.example.dogbreedapp.viewmodels.HomeScreenViewModel
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import coil.compose.AsyncImage
import java.util.Locale

var dogsList = emptyList<String>()
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initObservers()
        setContent {
            MainScreen()
        }


    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
    }
    private fun initObservers(){
        viewModel.dogBreedLiveData.observe(this) {
            dogsList = it?.message!!
        }
    }
    @Composable
    fun MainScreen() {
        Column {
            Banner()
            UserInteractions()

        }
    }

    @Composable
    fun UserInteractions() {

        Column {
            Row(modifier = Modifier.padding(top = 15.dp, start = 12.dp)) {
                val input = remember { mutableStateOf("") }
                TextField(
                    value = input.value,
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    singleLine = true,
                    onValueChange = { input.value = it },
                    label = { Text(text = "Dog Breed", fontSize = 16.sp) },
                    modifier = Modifier
                        .width(240.dp)
                        .wrapContentHeight()
                        .border(border = BorderStroke(2.dp, colorResource(id = R.color.black)))
                )

                Button(
                    onClick = {
                        viewModel.getDogsByBreed(input.value.lowercase(Locale.getDefault()))
                    },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 40.dp)
                ) {
                    Text(
                        text = "Fetch!",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            LazyColumn(modifier = Modifier.padding(top = 15.dp, start = 12.dp))
            {
                items(dogsList) { dogImgUrl ->
                    AsyncImage(
                        modifier = Modifier.clip(CircleShape),
                        model = dogImgUrl,
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                }
            }

        }

    }

    @Composable
    fun Banner() {
        Column(modifier = Modifier.padding(top = 20.dp)) {
            Text(
                text = "Dog Breed Finder",
                fontSize = 32.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(color = colorResource(id = R.color.purple_500))
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            )
        }
    }

    @Preview
    @Composable
    fun BasePreview() {
        Surface {
            MainScreen()
        }
    }
}