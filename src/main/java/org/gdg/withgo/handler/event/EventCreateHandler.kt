package org.gdg.withgo.handler.event

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.gdg.withgo.data.model.ResultModel
import org.gdg.withgo.data.model.event.Event
import org.gdg.withgo.data.repository.postgres.EventRepository
import org.gdg.withgo.domain.EventUsecase

class EventCreateHandler(private val eventRepo: EventUsecase = EventRepository()) : RequestHandler<Event, ResultModel> {
    override fun handleRequest(input: Event?, ctx: Context?): ResultModel {
        val uid = 1
        return input?.let {
            val res = eventRepo.addEvent(uid, input).blockingGet()
            ResultModel(true, "Event created id : $res")
        } ?: ResultModel(false, "Request doesn't correct")
    }

}
