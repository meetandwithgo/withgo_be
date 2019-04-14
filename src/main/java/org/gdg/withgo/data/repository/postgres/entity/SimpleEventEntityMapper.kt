package org.gdg.withgo.data.repository.postgres.entity

import org.gdg.withgo.data.database.Tables
import org.gdg.withgo.data.model.event.SimpleEvent
import org.jooq.Record

object SimpleEventEntityMapper {

    fun fromRecord(record: Record) = SimpleEvent().apply{
        this.id = record.get(Tables.EVENT.ID)
        this.title = record.get(Tables.EVENT.TITLE)
        this.thumbnail = record.get(Tables.EVENT.THUMBNAIL)
        this.startDate = record.get(Tables.EVENT.START_DATE)
        this.owner = record.get(Tables.ACCOUNT.NAME)
    }

}