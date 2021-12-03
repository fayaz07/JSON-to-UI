package com.mohammadfayaz.ezetap.ui

import android.app.Application
import com.mohammadfayaz.ezetap.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class EzeTapApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG){
      Timber.plant(Timber.DebugTree())
    }
  }

}