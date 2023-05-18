package com.gautam.weather.ui.location_picker

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LocationPageViewModel() : ViewModel() {

    val listHistory: MutableLiveData<List<String>> = MutableLiveData(emptyList())

    val onActionDoneListener = { saveAndSearch() }

    val onSearchBtnClicked = View.OnClickListener {
        saveAndSearch()
    }

    val cancelBtnClicked = View.OnClickListener {
        //updateEvent(LocationPageEvent.OnCloseBtn)
    }

    private fun saveAndSearch() {
       /* data.textToSearch.value?.run {
            updateEvent(LocationPageEvent.OnLocationSelected(this))
        }*/
    }

    val onClickHistory: (model: Any, viewId: Int, clickedPosition: Int) -> Unit =
        { model, _, _ ->
          ///  updateEvent(LocationPageEvent.OnLocationSelected(model as String))
        }

    //val historyData = data.listHistory

}