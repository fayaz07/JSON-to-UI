package com.mohammadfayaz.ezetap.ui.render

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.mohammadfayaz.ezetap.R
import com.mohammadfayaz.ezetap.databinding.ActivityRenderUiactivityBinding
import com.mohammadfayaz.network.models.JsonUIModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ViewWithFragmentComponent
import java.net.URI

@AndroidEntryPoint
class RenderUIActivity : AppCompatActivity() {

  private lateinit var binding: ActivityRenderUiactivityBinding
//  val dataUiTypeMap = HashMap<String, View>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityRenderUiactivityBinding.inflate(layoutInflater)
    setContentView(binding.root)

//    dataUiTypeMap["label"] = MaterialTextView(this)
//    dataUiTypeMap["edittext"] = EditText(this)
//    dataUiTypeMap["button"] = MaterialButton(this)

    supportActionBar?.title = "Render UI"

    renderUI()
  }

  private fun addLogo(data: JsonUIModel){
    if (data.logoUrl.isNotEmpty() && URI.create(data.logoUrl).isAbsolute){
      val imgView = ImageView(this)
      appendViewItemToParentLayout(imgView)
      Glide.with(imgView).load(data.logoUrl).into(imgView)
    }
  }

  private fun addHeader(data: JsonUIModel){
    val header = MaterialTextView(this)
    header.text = data.headingText
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      header.setTextAppearance(R.style.TextAppearance_AppCompat_Title)
    }
    header.gravity = Gravity.CENTER
    header.setPadding(0,8,0,16)
    appendViewItemToParentLayout(header)
  }

  private fun renderUI() {
    val data = intent.getSerializableExtra(DATA_OBJ_KEY) as JsonUIModel

    addLogo(data)
    addHeader(data)
    addOtherChildren(data)
  }

  private fun addOtherChildren(data: JsonUIModel) {

    data.uiData.forEach { dataItem ->

      when(dataItem.uiType){
        "label" -> {
          val v = MaterialTextView(this)
          v.text = dataItem.value
          v.setPadding(0,16,0,0)
//          v.labelFor = dataItem.key
          appendViewItemToParentLayout(v)
        }
        "edittext" ->{
          val v = EditText(this)
          v.hint = dataItem.hint
          appendViewItemToParentLayout(v)
        }
        "button" -> {
          val v = MaterialButton(this)
          v.text = dataItem.value
          v.setPadding(0,16,0,0)
          appendViewItemToParentLayout(v)
        }
      }
    }

  }


  private fun appendViewItemToParentLayout(item: View){
    binding.parentLinearLayout.addView(item)
  }

  private fun registerViewEvents() {

  }

  companion object {

    const val DATA_OBJ_KEY = "data"

    fun open(from: Activity, data: JsonUIModel){
      val intent = Intent(from, RenderUIActivity::class.java)
      intent.putExtra(DATA_OBJ_KEY, data)
      from.startActivity(intent)
    }
  }

}
