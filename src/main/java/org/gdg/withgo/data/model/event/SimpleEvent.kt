package org.gdg.withgo.data.model.event

import java.util.*

open class SimpleEvent {
    var id: Int = -1
    var title: String = ""
    var thumbnail: String = ""
    var startDate: Date = Date()
    var owner: String = ""
    var place: String = ""
}