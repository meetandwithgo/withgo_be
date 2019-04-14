package org.gdg.withgo.data.repository.postgres.entity

import org.gdg.withgo.data.database.Tables.TICKET
import org.gdg.withgo.data.model.event.Ticket
import org.jooq.Record

object TicketEntityMapper {
    fun fromRecord(record: Record) = Ticket().apply {
        this.id = record.get(TICKET.ID)
        this.name = record.get(TICKET.NAME)
        this.description = record.get(TICKET.DESCRIPTION)
        this.max = record.get(TICKET.MAX)
        this.price = record.get(TICKET.PRICE)
        this.saleStartDate = record.get(TICKET.SALES_START)
        this.saleEndDate = record.get(TICKET.SALES_END)
    }
}