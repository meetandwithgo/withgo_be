package org.gdg.withgo.data.repository.postgre

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.account.Applicant
import org.gdg.withgo.data.model.event.Event
import org.gdg.withgo.data.model.event.SimpleEvent
import org.gdg.withgo.data.model.event.Ticket
import org.gdg.withgo.data.model.organization.Organization
import org.gdg.withgo.domain.EventUsecase
import org.gdg.withgo.service.Postgresql
import java.sql.SQLException

class EventRepository : EventUsecase {

    override fun loadEvents(page: Int, count: Int): Single<List<SimpleEvent>> = Single.create {
        try {
            val res = Postgresql.create().use { connection ->
                val stmt = connection.prepareStatement("SELECT e.id, e.title, e.thumbnail, e.start_date, o.name from event e join organization o on e.id=o.id LIMIT ? OFFSET ? ")
                stmt.setInt(1, count)
                stmt.setInt(2, count * page)
                val resultSet = stmt.executeQuery()
                val eventList = ArrayList<SimpleEvent>()
                while (resultSet.next()) {
                    val id = resultSet.getInt(1)
                    val title = resultSet.getString(2)
                    val thumbnail = resultSet.getString(3)
                    val date = resultSet.getString(4)
                    val owner = resultSet.getString(5)
                    eventList.add(SimpleEvent(id, title, thumbnail, date, owner))
                }
                eventList
            }
            it.onSuccess(res)
        } catch (e: SQLException) {
            it.onError(e)
        }
    }

    override fun loadEvent(id: Int): Single<List<Event>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addEvent(title: String, thumbnail: String, startDate: String, endDate: String, owner: String, saleStartDate: String, saleEndDate: String, tickets: List<Ticket>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateEvent(id: Int, title: String, thumbnail: String, startDate: String, endDate: String, owner: String, saleStartDate: String, saleEndDate: String, tickets: List<Ticket>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteEvent(id: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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