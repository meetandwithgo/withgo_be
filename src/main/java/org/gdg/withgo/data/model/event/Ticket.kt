package org.gdg.withgo.data.model.event

import java.util.*

class Ticket {
    var id: Int = -1
    var name: String = ""
    var description: String = ""
    var max: Int = 0
    var price: Int = 0
    var saleStartDate: Date = Date()
    var saleEndDate: Date = Date()
}