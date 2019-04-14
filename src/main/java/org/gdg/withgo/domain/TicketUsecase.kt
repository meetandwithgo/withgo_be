package org.gdg.withgo.domain

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.event.Ticket

interface TicketUsecase {
    fun getEventTickets(eventId: Int): Single<List<Ticket>>
    fun applyEvent(eventId: Int, ticketId: Int): Completable
    fun getUserTickets(uid: Int): Single<List<Ticket>>
}