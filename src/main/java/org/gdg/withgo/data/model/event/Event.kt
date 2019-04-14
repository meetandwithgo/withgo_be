package org.gdg.withgo.data.model.event

import java.util.*
import kotlin.collections.ArrayList


class Event: SimpleEvent() {
    var content: String = ""
    var endDate: Date = Date()
    var tickets: List<Ticket> = ArrayList()
}