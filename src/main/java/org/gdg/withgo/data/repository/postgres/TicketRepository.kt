package org.gdg.withgo.data.repository.postgres

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.database.Tables.APPLICANT
import org.gdg.withgo.data.database.Tables.TICKET
import org.gdg.withgo.data.model.event.Ticket
import org.gdg.withgo.data.repository.postgres.entity.TicketEntityMapper
import org.gdg.withgo.domain.TicketUsecase
import org.gdg.withgo.service.Postgresql

class TicketRepository : TicketUsecase {
    override fun addTickets(eventId: Int, tickets: List<Ticket>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateTickets(eventId: Int, tickets: List<Ticket>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun applyEvent(eventId: Int, ticketId: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserTickets(uid: Int): Single<List<Ticket>> = Single.create {
        //        try {
//            Postgresql.dsl().use { dsl ->
//                dsl.selectFrom(APPLICANT).where(APPLICANT.USER_ID.eq(uid).join).fetch()
//                        .map {  }
//            }
//        } catch (e: Throwable) {
//            it.onError(e)
//        }
    }

    override fun getEventTickets(eventId: Int): Single<List<Ticket>> = Single.create {
        try {
            val tickets = Postgresql.dsl().use { dsl ->
                dsl.selectFrom(TICKET).where(TICKET.EVENT_ID.eq(eventId)).fetch().map { r -> TicketEntityMapper.fromRecord(r) }
            }
            it.onSuccess(tickets)
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

}