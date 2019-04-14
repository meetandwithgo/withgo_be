package org.gdg.withgo.handler.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.IncorrectInputResponse
import org.gdg.withgo.data.model.ResultModel
import org.gdg.withgo.data.model.event.EventSearchRequest
import org.gdg.withgo.data.repository.postgres.EventRepository
import org.gdg.withgo.domain.EventUsecase

class EventSearchHandler(private val eventRepo: EventUsecase = EventRepository()) : RequestHandler<EventSearchRequest, ResultModel> {
    override fun handleRequest(input: EventSearchRequest?, ctx: Context?): ResultModel =
        input?.let{
            val data = eventRepo.loadEvents(input.page, input.count).blockingGet()
            ResultModel(true, "", data)
        } ?: IncorrectInputResponse

}