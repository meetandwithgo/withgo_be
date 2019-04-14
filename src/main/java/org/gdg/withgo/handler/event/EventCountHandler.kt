package org.gdg.withgo.handler.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.ErrorResponse
import org.gdg.withgo.data.model.IncorrectInputResponse
import org.gdg.withgo.data.model.ResultModel
import org.gdg.withgo.data.model.event.EventSearchRequest
import org.gdg.withgo.data.repository.postgres.EventRepository
import org.gdg.withgo.domain.EventUsecase

class EventCountHandler(private val eventRepo: EventUsecase = EventRepository()) : RequestHandler<Any, ResultModel> {
    override fun handleRequest(input: Any?, ctx: Context?): ResultModel = try {
        val count = eventRepo.eventCount().blockingGet()
        ResultModel(true, "", count)
    } catch (e: Throwable){
        ErrorResponse(e)
    }

}