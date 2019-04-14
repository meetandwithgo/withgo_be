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
    override fun eventCount(): Single<Int> = Single.create {
        try {
            val count = Postgresql.dsl().use { dsl -> dsl.fetchCount(EVENT) }
            it.onSuccess(count)
        } catch (e: Throwable) {
            it.onError(e)
        }
    }

    override fun loadEvents(page: Int, count: Int): Single<List<Event>> = Single.create {
        try {
            val events = Postgresql.dsl().use { dsl ->
                dsl.selectFrom(EVENT.join(ACCOUNT).on(EVENT.OWNER_ID.eq(ACCOUNT.ID))).fetch()
                        .map { record -> EventEntityMapper.fromRecord(record) }
            }
            it.onSuccess(events)
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun loadOwnEvents(ownerId: Int): Single<List<Event>> = Single.create {
        try {
            val events = Postgresql.dsl().use { dsl ->
                dsl.selectFrom(EVENT.join(ACCOUNT).on(EVENT.OWNER_ID.eq(ACCOUNT.ID)))
                        .where(EVENT.OWNER_ID.eq(ownerId))
                        .fetch()
                        .map { r -> EventEntityMapper.fromRecord(r) }
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

    override fun addEvent(ownerId: Int, event: Event): Single<Int> = Single.create { sub ->
        try {
            val eventId = Postgresql.dsl().use { dsl ->
                val eventId = dsl.insertInto(EVENT, EVENT.TITLE, EVENT.THUMBNAIL, EVENT.PLACE, EVENT.CONTENT, EVENT.START_DATE, EVENT.END_DATE, EVENT.OWNER_ID)
                        .values(event.title, event.thumbnail, event.place, event.content, Date(event.startDate.time), Date(event.endDate.time), ownerId)
                        .returningResult(EVENT.ID)
                        .fetchOne()
                        .get(EVENT.ID)
                eventId
            }
            eventId?.let {
                ticketRepository.addTickets(it, event.tickets).blockingGet()?.let {
                    this.deleteEvent(eventId)
                    throw it
                }
                sub.onSuccess(eventId)
            } ?: sub.onError(Throwable("Failed create event"))
        } catch (e: Throwable) {
            sub.onError(e)
        }
    }

    override fun updateEvent(event: Event) = Completable.create { sub ->
        try {
            val res = Postgresql.dsl().use { dsl ->
                val res = dsl.update(EVENT)
                        .set(EVENT.TITLE, event.title)
                        .set(EVENT.THUMBNAIL, event.thumbnail)
                        .set(EVENT.PLACE, event.place)
                        .set(EVENT.CONTENT, event.content)
                        .set(EVENT.START_DATE, Date(event.startDate.time))
                        .where(EVENT.ID.eq(event.id))
                        .execute()
                res > 0
            }
            if (res) {
                ticketRepository.updateTickets(event.id, event.tickets).blockingGet()?.let { throw it }
                sub.onComplete()
            } else {
                sub.onError(Throwable("Failure update event ${event.id}"))
            }
        } catch (e: Throwable) {
            sub.onError(e)
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