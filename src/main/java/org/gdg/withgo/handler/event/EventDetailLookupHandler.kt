package org.gdg.withgo.handler.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.IncorrectInputResponse
import org.gdg.withgo.data.model.ResultModel
import org.gdg.withgo.data.model.event.EventIdRequest
import org.gdg.withgo.data.repository.postgres.EventRepository
import org.gdg.withgo.domain.EventUsecase

class EventDetailLookupHandler(private val eventRepo: EventUsecase = EventRepository()) : RequestHandler<EventIdRequest, ResultModel> {
    override fun handleRequest(input: EventIdRequest?, ctx: Context?): ResultModel =
        input?.let{
            val data = eventRepo.loadEvent(input.eventId).blockingGet()
            ResultModel(true, "", data)
        } ?: IncorrectInputResponse

}