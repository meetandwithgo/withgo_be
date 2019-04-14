package org.gdg.withgo.data.repository

import org.gdg.withgo.data.model.event.Event
import org.gdg.withgo.data.model.event.Ticket
import org.gdg.withgo.data.repository.postgres.EventRepository
import org.gdg.withgo.data.repository.postgres.TicketRepository
import org.junit.Assert
import org.junit.Test
import java.util.*

class EventRepositoryTest {

    @Test fun eventCRUDTest() {
        val ticketRepository = TicketRepository()
        val eventRepository = EventRepository(ticketRepository)
        val tickets = listOf(Ticket().apply {
            this.name = "테스트 티켓"
            this.description = "티켓 설명"
            this.max = 10
            this.price = 1000
            this.saleStartDate = Date()
            this.saleEndDate = Date()
        })
        val event = Event().apply {
            this.title = "테스트 이벤트"
            this.content = "이벤트 내용"
            this.startDate = Date()
            this.tickets = tickets
            this.endDate = Date()
        }
        val ownerId = 1
        val eventId = eventRepository.addEvent(ownerId, event).blockingGet()
        Assert.assertNotNull(eventId)
        val events = eventRepository.loadOwnEvents(ownerId).blockingGet()
        Assert.assertTrue(events.isNotEmpty())
        event.id = eventId
        event.title = "제목 22"
        eventRepository.updateEvent(event).blockingGet()?.let { throw it }
        val eventDetail = eventRepository.loadEvent(eventId).blockingGet()
        Assert.assertEquals(event.title, eventDetail.title)
        eventRepository.deleteEvent(eventId).blockingGet()?.let { throw it }
    }
}