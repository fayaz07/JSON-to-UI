package com.mohammadfayaz.ezetap.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.snackbar.Snackbar
import com.mohammadfayaz.ezetap.databinding.ActivityMainBinding
import com.mohammadfayaz.ezetap.ui.render.RenderUIActivity
import com.mohammadfayaz.ezetap.utils.Constants.FETCHED_UI_DATA_MESSAGE
import com.mohammadfayaz.ezetap.utils.Constants.FETCHING_UI_DATA_MESSAGE
import com.mohammadfayaz.ezetap.utils.Constants.RENDER_UI_TEXT
import com.mohammadfayaz.ezetap.utils.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)

    installSplashScreen()

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    supportActionBar?.title = "Home"

    registerViewEvents()
    addObservers()
  }

  private fun registerViewEvents(){
    binding.apply {
      mainButton.setOnClickListener {
        handleMainButtonClick()
      }
    }
  }

  private fun handleMainButtonClick() {
    if (viewModel.hasUiData()){
      RenderUIActivity.open(this, viewModel.getUiData())
    }else{
      viewModel.fetchUIJson()
      binding.apply {
        mainButton.isEnabled = false
        statusTextView.text = FETCHING_UI_DATA_MESSAGE
      }
    }
  }

  private fun addObservers(){
    viewModel.viewState.observe(this){
      when(it){
        is ViewState.Error<*> -> {
          showError(it.error)
        }
        ViewState.Idle -> {
          hideLoader()
        }
        ViewState.Loading -> {
          showLoader()
        }
        is ViewState.Success<*> -> {
          when(it.code){
            FETCHED_UI_DATA -> {
              updateUIAsFetchedUIData()
            }
          }
        }
      }
    }
  }

  private fun showError(error: String) {
    Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
  }

  private fun updateUIAsFetchedUIData(){
    hideLoader()
    binding.apply {
      statusTextView.text = FETCHED_UI_DATA_MESSAGE
      mainButton.text = RENDER_UI_TEXT
      mainButton.isEnabled = true
    }
  }

  private fun hideLoader(){
    binding.progressBar.visibility = View.GONE
  }

  private fun showLoader(){
    binding.progressBar.visibility = View.VISIBLE
  }

  companion object {
    const val FETCHED_UI_DATA  = 1
    const val UNABLE_TO_FETCH_UI_DATA  = 2
  }
}