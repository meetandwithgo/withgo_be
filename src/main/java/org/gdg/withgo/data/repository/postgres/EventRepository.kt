package org.gdg.withgo.data.repository.postgres

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.Single.create
import org.gdg.withgo.data.database.Tables.*
import org.gdg.withgo.data.model.account.Applicant
import org.gdg.withgo.data.model.event.Event
import org.gdg.withgo.data.model.event.SimpleEvent
import org.gdg.withgo.data.model.event.Ticket
import org.gdg.withgo.data.repository.postgres.entity.EventEntityMapper
import org.gdg.withgo.data.repository.postgres.entity.SimpleEventEntityMapper
import org.gdg.withgo.domain.EventUsecase
import org.gdg.withgo.domain.TicketUsecase
import org.gdg.withgo.service.Postgresql
import java.sql.Date
import java.sql.SQLException

class EventRepository(val ticketRepository: TicketUsecase = TicketRepository()) : EventUsecase {

    override fun loadEvents(page: Int, count: Int): Single<List<SimpleEvent>> = Single.create {
        try {
            val events = Postgresql.dsl().use { dsl ->
                dsl.selectFrom(EVENT.join(ACCOUNT).on(EVENT.OWNER_ID.eq(ACCOUNT.ID))).fetch()
                        .map { record -> SimpleEventEntityMapper.fromRecord(record) }
            }
            it.onSuccess(events)
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun loadOwnEvents(ownerId: Int): Single<List<SimpleEvent>> = Single.create {
        try {
            val events = Postgresql.dsl().use { dsl ->
                dsl.selectFrom(EVENT.join(ACCOUNT).on(EVENT.OWNER_ID.eq(ACCOUNT.ID)))
                        .where(EVENT.OWNER_ID.eq(ownerId))
                        .fetch()
                        .map { r -> SimpleEventEntityMapper.fromRecord(r) }
            }
            it.onSuccess(events)
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    override fun loadEvent(id: Int): Single<Event> = create { sub ->
        try {
            val event = Postgresql.dsl().use { dsl ->
                val record = dsl.selectFrom(EVENT.join(ACCOUNT).on(EVENT.OWNER_ID.eq(ACCOUNT.ID))).where(EVENT.ID.eq(id)).first()
                val tickets = ticketRepository.getEventTickets(id).blockingGet()
                EventEntityMapper.fromRecord(record).apply {
                    this.tickets = tickets
                }
            }
            sub.onSuccess(event)
        } catch (e: SQLException) {
            sub.onError(e)
        }
    }

    override fun addEvent(ownerId: Int, event: Event): Single<Int> = Single.create {
        try {
            val eventId = Postgresql.dsl().use { dsl ->
                val eventId = dsl.insertInto(EVENT, EVENT.TITLE, EVENT.THUMBNAIL, EVENT.CONTENT, EVENT.START_DATE, EVENT.END_DATE, EVENT.SALES_START, EVENT.SALES_END, EVENT.OWNER_ID)
                        .values(event.title, event.thumbnail, event.content, Date(event.startDate.time), Date(event.endDate.time), Date(event.saleStartDate.time), Date(event.saleEndDate.time), ownerId)
                        .returningResult(EVENT.ID)
                        .fetchOne()
                        .get(EVENT.ID)
                val query = dsl.insertInto(TICKET, TICKET.EVENT_ID, TICKET.NAME, TICKET.DESCRIPTION, TICKET.MAX, TICKET.PRICE)
                event.tickets.forEach { query.values(eventId, it.name, it.description, it.max, it.price) }
                eventId
            }
            it.onSuccess(eventId)
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    override fun updateEvent(event: Event) = Completable.create {
        try {
            val res = Postgresql.dsl().use {
                val res = it.update(EVENT)
                        .set(EVENT.TITLE, event.title)
                        .set(EVENT.THUMBNAIL, event.thumbnail)
                        .set(EVENT.CONTENT, event.content)
                        .set(EVENT.START_DATE, Date(event.startDate.time))
                        .set(EVENT.SALES_START, Date(event.saleStartDate.time))
                        .set(EVENT.SALES_END, Date(event.saleEndDate.time))
                        .where(EVENT.ID.eq(event.id))
                        .execute()
                res > 0
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failure update event ${event.id}"))
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    override fun deleteEvent(id: Int) = Completable.create {
        try {
            val res = Postgresql.dsl().use { dsl ->
                val res = dsl.deleteFrom(EVENT).where(EVENT.ID.eq(id)).execute()
                res > 0
            }
            if (res) {
                it.onComplete()
            } else {
                it.onError(Throwable("Failure delete event $id"))
            }
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    override fun checkOwn(email: String, id: Int): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun applyEvent(email: String, id: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unapplyEvent(email: String, id: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadApplicants(email: String, id: Int): Single<List<Applicant>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}