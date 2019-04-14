package org.gdg.withgo.data.repository.postgres.entity

import org.gdg.withgo.data.database.Tables
import org.gdg.withgo.data.model.event.Event
import org.jooq.Record

object EventEntityMapper {
    fun fromRecord(record: Record) = Event().apply {
        this.id = record.get(Tables.EVENT.ID)
        this.title = record.get(Tables.EVENT.TITLE)
        this.thumbnail = record.get(Tables.EVENT.THUMBNAIL)
        this.place = record.get(Tables.EVENT.PLACE)
        this.content = record.get(Tables.EVENT.CONTENT)
        this.startDate = record.get(Tables.EVENT.START_DATE)
        this.owner = record.get(Tables.ACCOUNT.NAME)
        this.endDate = record.get(Tables.EVENT.END_DATE)
    }
}