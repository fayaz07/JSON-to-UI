package com.mohammadfayaz.ezetap.utils

import androidx.lifecycle.MutableLiveData

sealed class ViewState {
  object Idle : ViewState()
  data class Success<T>(val data: T? = null, val code: Int, val message: String) : ViewState() {
    override fun toString(): String {
      return "Success(data=$data, code=$code, message='$message')"
    }
  }

  object Loading : ViewState()
  data class Error<T>(val error: String, val code: Int, val data: T?) : ViewState() {
    override fun toString(): String {
      return "Error(error=$error, code=$code)"
    }
  }

}

fun MutableLiveData<ViewState>.load() {
  this.postValue(ViewState.Loading)
}

fun MutableLiveData<ViewState>.idle() {
  this.postValue(ViewState.Idle)
}

fun MutableLiveData<ViewState>.success(data: Any?, message: String, code: Int) {
  this.postValue(ViewState.Success(data = data, code, message))
}

fun MutableLiveData<ViewState>.error(error: String, code: Int, data: Any?) {
  this.postValue(ViewState.Error(error, code, data))
}

fun MutableLiveData<ViewState>.networkError() {
  this.postValue(ViewState.Error(error = "INTERNET_ERROR", 1, null))
}