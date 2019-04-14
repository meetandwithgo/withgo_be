package org.gdg.withgo.data.repository.postgres

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.event.Ticket
import org.gdg.withgo.domain.TicketUsecase

class TicketRepository : TicketUsecase {
    override fun applyEvent(eventId: Int, ticketId: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserTickets(uid: Int): Single<List<Ticket>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEventTickets(eventId: Int): Single<List<Ticket>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}