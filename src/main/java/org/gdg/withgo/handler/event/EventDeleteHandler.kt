package org.gdg.withgo.handler.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.ErrorResponse
import org.gdg.withgo.data.model.IncorrectInputResponse
import org.gdg.withgo.data.model.ResultModel
import org.gdg.withgo.data.model.SuccessResponse
import org.gdg.withgo.data.model.event.EventIdRequest
import org.gdg.withgo.data.repository.postgres.EventRepository
import org.gdg.withgo.domain.EventUsecase

class EventDeleteHandler(private val eventRepo: EventUsecase = EventRepository()) : RequestHandler<EventIdRequest, ResultModel> {
    override fun handleRequest(input: EventIdRequest?, ctx: Context?): ResultModel =
        input?.let {
            eventRepo.deleteEvent(it.eventId).blockingGet()?.let { ErrorResponse(it) } ?: SuccessResponse
        } ?: IncorrectInputResponse

}