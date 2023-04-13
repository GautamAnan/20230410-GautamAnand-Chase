package com.gautam.weather.ui


import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gautam.domain.usecase.CurrentWeatherByCityUseCase
import com.gautam.domain.usecase.CurrentWeatherByLocationParams
import com.gautam.domain.usecase.CurrentWeatherByLocationUseCase
import com.gautam.domain.usecase.CurrentWeatherParams
import com.gautam.weather.utils.resultListMock
import com.gautam.weather.getOrAwaitValue
import com.google.common.truth.Truth
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.gautam.core.fundamentals.Error
import com.gautam.core.fundamentals.Result
import com.gautam.weather.utils.MainDispatcherRule
import com.google.android.gms.maps.model.LatLng

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var mViewModel: WeatherViewModel

    @Mock
    private lateinit var mCurrentCityUseCase: CurrentWeatherByCityUseCase

    @Mock
    private lateinit var mCurrentLocationUseCase: CurrentWeatherByLocationUseCase

    @Mock
    private lateinit var context: Application

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `verify Success for function getWeatherDataUsingCityName`() = runTest {
        whenever(mCurrentCityUseCase.execute(CurrentWeatherParams(SEARCH_CITY_QUERY))).thenReturn(
            Result.Success(
                resultListMock
            )
        )
        mViewModel = WeatherViewModel(
            context, WeatherData(), mCurrentCityUseCase, mCurrentLocationUseCase
        )
        mViewModel.getWeatherByName(SEARCH_CITY_QUERY)
        val data = mViewModel.data.model.getOrAwaitValue()

        Truth.assertThat(data.dt).isEqualTo(resultListMock.dt)
    }

    @Test
    fun `verify failure for function getWeatherDataUsingCityName`() = runTest {
        val exception = Error.RemoteError(3, "IOException")
        whenever(mCurrentCityUseCase.execute(CurrentWeatherParams(SEARCH_CITY_QUERY))).thenReturn(
            Result.Failure(exception)
        )
        mViewModel = WeatherViewModel(
            context, WeatherData(), mCurrentCityUseCase, mCurrentLocationUseCase
        )
        mViewModel.getWeatherByName(SEARCH_CITY_QUERY)
        val data = mViewModel.data.showError.getOrAwaitValue()
        Truth.assertThat(data).isEqualTo(true)
    }

    @Test
    fun `verify Success for function getWeatherDataUsingLatLon`() = runTest {
        whenever(mCurrentLocationUseCase.execute(CurrentWeatherByLocationParams(LatLng(SEARCH_LATITUDE, SEARCH_LONGITUDE)))).thenReturn(
            Result.Success(
                resultListMock
            )
        )
        mViewModel = WeatherViewModel(
            context, WeatherData(), mCurrentCityUseCase, mCurrentLocationUseCase
        )
        mViewModel.getWeatherByLocation(LatLng( SEARCH_LATITUDE, SEARCH_LONGITUDE))
        val data = mViewModel.data.model.getOrAwaitValue()

        Truth.assertThat(data.dt).isEqualTo(resultListMock.dt)
    }

    @Test
    fun `verify failure for function getWeatherDataUsingLatLon`() = runTest {
        val exception = Error.RemoteError(3, "IOException")
        whenever(mCurrentLocationUseCase.execute(CurrentWeatherByLocationParams(LatLng(SEARCH_LATITUDE, SEARCH_LONGITUDE)))).thenReturn(
            Result.Failure(exception)
        )
        mViewModel = WeatherViewModel(
            context, WeatherData(), mCurrentCityUseCase, mCurrentLocationUseCase
        )
        mViewModel.getWeatherByLocation(LatLng( SEARCH_LATITUDE, SEARCH_LONGITUDE))
        val data = mViewModel.data.showError.getOrAwaitValue()
        Truth.assertThat(data).isEqualTo(true)
    }

    companion object {
        const val SEARCH_CITY_QUERY = "Mumbai"
        const val SEARCH_LATITUDE = 18.79
        const val SEARCH_LONGITUDE = 73.75
    }
}
