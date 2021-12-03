package com.mohammadfayaz.ezetap.model

data class ApiResult<T>(
  val success: Boolean, val data: T?,
  val message: String = ""
)