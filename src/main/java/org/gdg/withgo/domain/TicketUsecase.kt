package org.gdg.withgo.domain

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.event.Ticket

interface TicketUsecase {
    fun getEventTickets(eventId: Int): Single<List<Ticket>>
    fun applyEvent(eventId: Int, ticketId: Int): Completable
    fun addTickets(eventId: Int, tickets: List<Ticket>): Completable
    fun updateTickets(eventId: Int, tickets: List<Ticket>): Completable
    fun updateTicket(eventId: Int, ticket: Ticket): Completable
    fun getUserTickets(uid: Int): Single<List<Ticket>>
}