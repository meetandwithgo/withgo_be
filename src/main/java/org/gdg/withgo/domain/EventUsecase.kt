package org.gdg.withgo.domain

import io.reactivex.Completable
import io.reactivex.Single
import org.gdg.withgo.data.model.account.Applicant
import org.gdg.withgo.data.model.event.Event
import org.gdg.withgo.data.model.event.SimpleEvent
import org.gdg.withgo.data.model.event.Ticket

interface EventUsecase : Usecase {
    fun loadEvents(page: Int, count: Int): Single<List<SimpleEvent>>
    fun loadEvent(id: Int): Single<List<Event>>
    fun addEvent(title: String,
                 thumbnail: String,
                 startDate: String,
                 endDate: String,
                 owner: String,
                 saleStartDate: String,
                 saleEndDate: String,
                 tickets: List<Ticket>): Completable
    fun updateEvent(id: Int,
                 title: String,
                 thumbnail: String,
                 startDate: String,
                 endDate: String,
                 owner: String,
                 saleStartDate: String,
                 saleEndDate: String,
                 tickets: List<Ticket>): Completable
    fun deleteEvent(id: Int): Completable
    fun checkOwn(email: String, id: Int): Single<Boolean>
    fun applyEvent(email:String, id: Int): Completable
    fun unapplyEvent(email:String, id: Int): Completable
    fun loadApplicants(email:String, id: Int): Single<List<Applicant>>
}