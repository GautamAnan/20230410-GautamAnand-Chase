package com.gautam.weather.ui.location_picker

import android.app.Application
import android.view.View
import com.gautam.core.BaseViewModel


class LocationPageViewModel(
    application: Application,
    data: LocationPageData
) : BaseViewModel<LocationPageData, LocationPageEvent>(application, data) {

    val onActionDoneListener = { saveAndSearch() }

    val onSearchBtnClicked = View.OnClickListener {
        saveAndSearch()
    }

    val cancelBtnClicked = View.OnClickListener {
        updateEvent(LocationPageEvent.OnCloseBtn)
    }

    private fun saveAndSearch() {
        data.textToSearch.value?.run {
            updateEvent(LocationPageEvent.OnLocationSelected(this))
        }
    }

    val onClickHistory: (model: Any, viewId: Int, clickedPosition: Int) -> Unit =
        { model, _, _ ->
            updateEvent(LocationPageEvent.OnLocationSelected(model as String))
        }

    val historyData = data.listHistory

}