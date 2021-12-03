package com.mohammadfayaz.ezetap.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammadfayaz.ezetap.domain.JsonUIRepo
import com.mohammadfayaz.ezetap.ui.main.MainActivity.Companion.FETCHED_UI_DATA
import com.mohammadfayaz.ezetap.ui.main.MainActivity.Companion.UNABLE_TO_FETCH_UI_DATA
import com.mohammadfayaz.ezetap.utils.ViewState
import com.mohammadfayaz.ezetap.utils.error
import com.mohammadfayaz.ezetap.utils.load
import com.mohammadfayaz.ezetap.utils.success
import com.mohammadfayaz.network.models.JsonUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: JsonUIRepo) : ViewModel() {

  private val _viewState = MutableLiveData<ViewState>()
  val viewState: LiveData<ViewState> = _viewState

  private lateinit var uiData: JsonUIModel

  fun fetchUIJson() {
    viewModelScope.launch {
      _viewState.load()
      val response = repo.fetchJsonUI()
      if (response.success){
        uiData = response.data!!
        _viewState.success(response.data,"Fetched UI data", FETCHED_UI_DATA)
      }else{
        _viewState.error(response.message,UNABLE_TO_FETCH_UI_DATA, null)
      }
    }
  }

  fun hasUiData(): Boolean {
    return ::uiData.isInitialized
  }

  fun getUiData(): JsonUIModel {
    return uiData
  }
}

