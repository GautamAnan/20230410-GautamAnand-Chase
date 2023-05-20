package com.gautam.weather.ui.view_weather

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.activity.compose.ReportDrawn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.gautam.core.utils.ImagesCache
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.weather.R
import com.gautam.weather.ui.WeatherState
import org.koin.androidx.compose.koinViewModel

@Composable
fun InformationPage(
    navController: NavController,
    argumentSearch: String?,
    viewModel: InformationPageViewModel = koinViewModel()
) {
    if (!argumentSearch.isNullOrEmpty()) {
        viewModel.getWeatherByName(argumentSearch)
    } else
        AccompanistPermissionsScreen(viewModel)
    GetData(viewModel,navController)
}


@Composable
fun GetData(viewModel: InformationPageViewModel, navController: NavController) {
    when (val result = viewModel.result.observeAsState().value) {
        is WeatherState.Response -> {
            Log.d("Effects", "Call 2")
            InfoScreen(result.result, viewModel){
                navController.navigate("search")
            }
        }

        is WeatherState.Loading -> {
            Log.d("Effects", "Call 3")
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(30.dp)
                    .then(Modifier.fillMaxWidth())
                    .then(Modifier.wrapContentSize(Alignment.Center))
            )
        }

        else -> {
            EmptyList(onBackClick = {

            })
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun InfoScreen(value: CurrentWeatherModel, viewModel: InformationPageViewModel,onSearchClick: () -> Unit) {
    val iconName = value.weatherModelItems?.get(0)?.icon
    Scaffold(
        topBar = { value.name?.let { WeatherDetailsToolbar(it,onSearchClick ) } }
    ) {
        val image = viewModel.imageOffline.observeAsState().value
        if (image != null || viewModel.checkImageIfAvailable(iconName)) {
            val temp = value.mainModel?.temp
            if (image != null && temp != null) {
                SetProfileHeader(image, temp)
            }
        } else {
            Glide.with(LocalContext.current).asBitmap()
                .load("https://openweathermap.org/img/wn/$iconName@2x.png")
                .apply(
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .centerCrop()
                        .dontTransform()
                )
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        bitmap: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        Log.d("Effects", "Call 4")
                        ImagesCache.addImageToWarehouse(iconName, bitmap)
                        viewModel.imageOffline.postValue(bitmap)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                })
        }
    }
}


@Composable
fun SetProfileHeader(bitmap: Bitmap, temperature: Double) {
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)) {
        Column(
        ) {

            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Image",
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = temperature.toString(),
                modifier = Modifier
                    .padding(20.dp),
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray
                )
            )

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeatherDetailsToolbar(
    placeName: String,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(shadowElevation = 3.dp) {
        TopAppBar(
            modifier = modifier.statusBarsPadding(),
            title = {
                Text(
                    text = placeName,
                    style = MaterialTheme.typography.headlineLarge,
                    // As title in TopAppBar has extra inset on the left, need to do this: b/158829169
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }, navigationIcon = {
                IconButton(
                    onSearchClick,
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null
                    )
                }
            }

        )
    }
}


@Composable
private fun EmptyList(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    // Calls reportFullyDrawn when this composable is composed.
    ReportDrawn()
    Column(
        modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.list_empty),
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(20.dp)
        )
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer),
            onClick = onBackClick,
            modifier = modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                text = stringResource(id = R.string.exit)
            )
        }

    }
}

