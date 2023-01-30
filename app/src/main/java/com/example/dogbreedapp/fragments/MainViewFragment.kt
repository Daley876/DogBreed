package com.example.dogbreedapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
            Row(modifier = Modifier.padding(top = 55.dp, start = 12.dp)) {
                val input = remember { mutableStateOf("") }
                val isValid = remember { mutableStateOf(true) }
                Column {
                    TextField(
                        value = input.value,
                        trailingIcon = {
                            if (isValid.value) {
                                Icon(Icons.Default.Clear, contentDescription = "clear text",
                                    modifier = Modifier.clickable { input.value = "" })
                            } else {
                                Icon(
                                    Icons.Filled.Info, contentDescription = "error icon",
                                    tint = colorResource(id = R.color.red)
                                )
                            }
                        },
                        textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                        singleLine = true,
                        onValueChange = { input.value = it },
                        label = { Text(text = "Dog Breed", fontSize = 16.sp) },
                        modifier = Modifier
                            .width(240.dp)
                            .wrapContentHeight()
                            .border(border = BorderStroke(2.dp, colorResource(id = R.color.black)))
                    )
                    if (!isValid.value) {
                        Text(
                            text = "Breed cannot be blank!",
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 12.dp, top = 3.dp)
                        )
                    }
                }
                Button(
                    onClick = {
                        val searchedTerm = input.value.lowercase(Locale.getDefault()).trim()
                        if (searchedTerm.isEmpty()) isValid.value = false
                        else {
                            isValid.value = true
                            viewModel.getDogsByBreed(searchedTerm)
                        }
                    },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 40.dp, top = 10.dp)
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
            LazyColumn(
                modifier = Modifier
                    .padding(top = 30.dp, start = 11.dp, end = 11.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                itemsIndexed(dogsList.message) { index, dogImgUrl ->
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .border(BorderStroke(1.dp, colorResource(id = R.color.black)))
                            .clip(RectangleShape)
                            .wrapContentSize()
                    ) {
                        Column {
                            AsyncImage(
                                model = dogImgUrl,
                                contentScale = ContentScale.Fit,
                                contentDescription = null
                            )
                        }
                    }
                    if (index < dogsList.message.lastIndex) {
                        Divider(color = colorResource(id = R.color.black), thickness = 1.dp)
                    }
                }
            }

        } else {
            ShowErrorMessage()
        }
    }

    @Composable
    fun Banner() {
        Column {
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