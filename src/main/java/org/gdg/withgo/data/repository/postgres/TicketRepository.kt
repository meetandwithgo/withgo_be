package org.gdg.withgo.data.repository.postgres

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.database.Tables.APPLICANT
import org.gdg.withgo.data.database.Tables.TICKET
import org.gdg.withgo.data.model.event.Ticket
import org.gdg.withgo.data.repository.postgres.entity.TicketEntityMapper
import org.gdg.withgo.domain.TicketUsecase
import org.gdg.withgo.service.Postgresql
import java.sql.Date

class TicketRepository : TicketUsecase {
    override fun addTickets(eventId: Int, tickets: List<Ticket>) = Completable.create {
        if (tickets.isEmpty()) {
            it.onComplete()
        }
        try {
            val res = Postgresql.dsl().use { dsl ->
                var query = dsl.insertInto(TICKET, TICKET.EVENT_ID, TICKET.NAME, TICKET.DESCRIPTION, TICKET.MAX, TICKET.PRICE, TICKET.SALES_START, TICKET.SALES_END)
                tickets.forEach { query = query.values(eventId, it.name, it.description, it.max, it.price, Date(it.saleStartDate.time), Date(it.saleEndDate.time)) }
                query.execute() == tickets.size
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failed add tickets to event"))
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    override fun updateTickets(eventId: Int, tickets: List<Ticket>): Completable {
        val recentTickets = tickets.filter { updateTicket(eventId, it).blockingGet() != null }
        return this.addTickets(eventId, recentTickets)
    }

    override fun updateTicket(eventId: Int, ticket: Ticket) = Completable.create {
        try {
            val res = Postgresql.dsl().use { dsl ->
                val count = dsl.selectFrom(TICKET).where(TICKET.ID.eq(ticket.id).and(TICKET.EVENT_ID.eq(eventId))).count()
                if (count == 0) {
                    false
                }
                dsl.update(TICKET)
                        .set(TICKET.NAME, ticket.name)
                        .set(TICKET.DESCRIPTION, ticket.description)
                        .set(TICKET.MAX, ticket.max)
                        .set(TICKET.PRICE, ticket.price)
                        .set(TICKET.SALES_START, Date(ticket.saleStartDate.time))
                        .set(TICKET.SALES_END, Date(ticket.saleEndDate.time))
                        .where(TICKET.ID.eq(ticket.id).and(TICKET.EVENT_ID.eq(eventId)))
                        .execute() > 0
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failed update ticket"))
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
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