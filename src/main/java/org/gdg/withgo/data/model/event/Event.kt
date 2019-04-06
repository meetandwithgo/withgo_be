package org.gdg.withgo.data.model.event


class Event(
        id: Int,
        title: String,
        thumbnail: String,
        startDate: String,
        owner: String,
        val endDate: String,
        val saleStartDate: String,
        val saleEndDate: String,
        val tickets: List<Ticket>
) : SimpleEvent(id, title, thumbnail, startDate, owner)