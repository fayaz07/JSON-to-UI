package com.mohammadfayaz.ezetap.domain

import com.mohammadfayaz.ezetap.model.ApiResult
import com.mohammadfayaz.network.JsonUIService
import com.mohammadfayaz.network.models.JsonUIModel
import java.lang.Exception
import javax.inject.Inject

class JsonUIRepo @Inject constructor(private val service: JsonUIService) {

  suspend fun fetchJsonUI() : ApiResult<JsonUIModel> {
    return try {
      val response = service.getJsonUI()
      if (response.isSuccessful){
        ApiResult(success = true, response.body()!!)
      }else{
        ApiResult(success = false, null, "Unable to fetch data")
      }
    }catch (e: Exception){
      ApiResult(success = false, null, e.localizedMessage)
    }
  }

}
