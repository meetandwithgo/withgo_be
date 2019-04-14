package org.gdg.withgo.handler.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.ErrorResponse
import org.gdg.withgo.data.model.ResultModel
import org.gdg.withgo.data.model.SuccessResponse
import org.gdg.withgo.data.model.event.Event
import org.gdg.withgo.data.repository.postgres.EventRepository
import org.gdg.withgo.domain.EventUsecase

class EventUpdateHandler(private val eventRepo: EventUsecase = EventRepository()) : RequestHandler<Event, ResultModel> {
    override fun handleRequest(input: Event?, ctx: Context?): ResultModel {
        return input?.let {
            eventRepo.updateEvent(input).blockingGet()?.let { ErrorResponse(it) } ?: SuccessResponse
        } ?: ResultModel(false, "Request doesn't correct")
    }

}
