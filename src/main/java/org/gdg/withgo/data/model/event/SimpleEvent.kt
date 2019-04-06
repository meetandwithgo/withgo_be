package org.gdg.withgo.data.model.event

open class SimpleEvent(
        val id: Int,
        val title: String,
        val thumbnail: String,
        val startDate: String,
        val owner: String
)