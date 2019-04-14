package org.gdg.withgo.data.model

open class ResultModel(val isSuccessed: Boolean, val message: String = "", val data: Any? = null)

object IncorrectInputResponse : ResultModel(false, "Input doesn't correct")
object SuccessResponse : ResultModel(true, "success")
class ErrorResponse(throwable: Throwable) : ResultModel(false, throwable.message ?: "error")