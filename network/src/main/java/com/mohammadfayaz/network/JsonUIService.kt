package com.mohammadfayaz.network

import com.mohammadfayaz.network.models.JsonUIModel
import retrofit2.Response
import retrofit2.http.GET

interface JsonUIService {
  @GET(EndPoints.JSON_UI)
  suspend fun getJsonUI(): Response<JsonUIModel>
}
