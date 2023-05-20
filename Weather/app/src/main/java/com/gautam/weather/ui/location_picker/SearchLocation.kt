package com.gautam.weather.ui.location_picker

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gautam.domain.usecase.HistoryResult

import com.gautam.weather.R
import com.gautam.weather.theme.WeatherAppTheme
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SearchLocation(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SearchLocationViewModel = koinViewModel()
) {
    val (searchTerm, updateSearchTerm) = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(topBar = {
        AppBar(searchTerm, updateSearchTerm)
    }) {
        viewModel.fetchTopics(searchTerm.text)
        when (val topicStatus = viewModel.topics.value) {
            is LocationState.Response -> {
                Log.d("Effects", "Call 2")
                LazyColumn(
                    modifier = modifier
                        .statusBarsPadding()
                        .fillMaxHeight()
                ) {

                    val topicList = topicStatus.result
                    items(topicList.size) {
                        Text(
                            text = topicList[it],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    val listItem = topicList[it]
                                    navController.navigate("Information/{$listItem}")
                                })
                                .padding(
                                    start = 16.dp,
                                    top = 8.dp,
                                    end = 16.dp,
                                    bottom = 8.dp
                                )
                                .wrapContentWidth(Alignment.Start)
                                .animateItemPlacement()
                        )
                    }
                }
            }

            is LocationState.Loading -> {
                Log.d("Effects", "Call 3")
                CircularProgressIndicator(
                    modifier = modifier
                        .padding(30.dp)
                        .then(Modifier.fillMaxWidth())
                        .then(Modifier.wrapContentSize(Alignment.Center))
                )
            }

            else -> {
                Text(
                    text = stringResource(id = R.string.list_empty),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }

        }
    }


}


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
private fun AppBar(
    searchTerm: TextFieldValue,
    updateSearchTerm: (TextFieldValue) -> Unit,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
) {
    TopAppBar(
        title = {
            BasicTextField(
                value = searchTerm,
                onValueChange = updateSearchTerm,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = LocalContentColor.current
                ),
                maxLines = 1,
                cursorBrush = SolidColor(LocalContentColor.current),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.label_search)
            )
        }

    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme() {
        //SearchLocation(emptyList())
    }
}



