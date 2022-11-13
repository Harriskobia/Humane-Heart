package com.harye.humaneheart.data

import com.google.firebase.database.Exclude

class Event(
    @get:Exclude
    var id: String? = null,
//    var image: Int? = null
    var eventName: String? = null,
//    var eventDate: Date? = null,          /* To check later how to push dates */
    var eventDescription: String? = null,
    var whatsAppLink: String? = null,
    var OrganizerPhone: String? = null
)