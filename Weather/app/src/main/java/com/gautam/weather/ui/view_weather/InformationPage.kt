package com.gautam.weather.ui.view_weather

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.gautam.core.utils.ImagesCache
import com.gautam.domain.model.CurrentWeatherModel
import com.gautam.weather.ui.WeatherState
import com.gautam.weather.ui.WeatherViewModel
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.compose.koinViewModel

@Composable
fun InformationPage(
    navController: NavController,
    sharedViewModel: WeatherViewModel,
    viewModel: InformationPageViewModel = koinViewModel()
) {
    AccompanistPermissionsScreen {
        sharedViewModel.getWeatherByLocation(LatLng(it.latitude, it.longitude))
    }

    GetData(sharedViewModel, viewModel)

}

@Composable
fun GetData(sharedViewModel: WeatherViewModel, viewModel: InformationPageViewModel) {
    when (val result = sharedViewModel.result.observeAsState().value) {
        is WeatherState.Response -> {
            RowInfo(result.result, viewModel)
        }

        is WeatherState.Loading -> {
            CircularProgressIndicator()
        }

        else -> {

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalGlideComposeApi::class
)
@Composable
private fun RowInfo(value: CurrentWeatherModel, viewModel: InformationPageViewModel) {
    val iconName = value.weatherModelItems?.get(0)?.icon
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Weather") }) }
    ) {
        viewModel.checkImageIfAvailable(iconName)
        val image = viewModel.imageOffline.observeAsState().value
        if (image != null) {
            value.mainModel?.temp?.let { it1 -> SetProfileHeader(image, it1) }
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
                        //viewModel.imageOffline.postValue(bitmap)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                })
        }


        /*     LazyColumn(
                 modifier = modifierManual2.layoutId("text2"),
                 content = {
                     grouped.forEach {
                         stickyHeader {
                             Text(text = "")
                         }
                     }*/
        //modifier = Modifier.clickable(onClick = onClick)
        Box() {
            Card(elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)) {
                /* Image(
                     painter = painterResource(id = R.drawable.ic_launcher_background),
                     contentDescription = "dddd"
                 )
*/
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Hello !",
                        modifier = Modifier.clickable {

                        }
                    )
                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        text = "Hello!",
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }
        }
    }
}


@Composable
fun SetProfileHeader(bitmap: Bitmap, temperature: Double) {
    BoxWithConstraints {
        val constraints = decoupledConstraints()
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = constraints
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Image",
                modifier = Modifier.layoutId("icon")
            )

            Text(
                text = temperature.toString(),
                modifier = Modifier
                    .padding(20.dp)
                    .layoutId("text"),
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray
                )
            )

        }
    }
}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val text1 = createRefFor("icon")
        val text2 = createRefFor("text")
        createHorizontalChain(text1, text2, chainStyle = ChainStyle.Packed)
    }
}

