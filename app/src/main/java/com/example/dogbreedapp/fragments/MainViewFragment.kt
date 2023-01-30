package com.example.dogbreedapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.dogbreedapp.R
import com.example.dogbreedapp.viewmodels.HomeScreenViewModel
import java.util.*

private lateinit var viewModel: HomeScreenViewModel

class MainViewFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                MainScreen()
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
    }

    @Composable
    fun MainScreen() {
        Column {
            Banner()
            InputRow()
            ImageList()

        }
    }

    @Composable
    fun InputRow() {
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
                        val searchedTerm = input.value.lowercase(Locale.getDefault()).trim()
                        viewModel.getDogsByBreed(searchedTerm)
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

        }
    }

    @Composable
    fun ShowErrorMessage() {
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                modifier = Modifier.padding(13.dp),
                text = "NO RESULTS FOUND MATCHING THAT BREED",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.red),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        }
    }

    @Composable
    fun ImageList() {
        val dogsList = viewModel.dogBreedStateData.value //value changes after each call

        if (dogsList.message.isNotEmpty()) {
            LazyColumn(modifier = Modifier
                .padding(top = 15.dp, start = 11.dp))
            {
                items(dogsList.message) { dogImgUrl ->
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .border(BorderStroke(1.dp,colorResource(id = R.color.black)))
                            .clip(RectangleShape)
                            .wrapContentSize()
                    ) {
                        Row {
                            AsyncImage(
                                model = dogImgUrl,
                                contentScale = ContentScale.Fit,
                                contentDescription = null
                            )
                        }
                    }
                }
            }

        } else {
            ShowErrorMessage()
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