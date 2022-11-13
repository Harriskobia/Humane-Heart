package com.harye.humaneheart.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.harye.humaneheart.data.Event
import com.harye.humaneheart.data.NODE_EVENTS

class EventViewModel : ViewModel() {

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = result

    fun addEvent(event: Event) {
        val dbEvents = FirebaseDatabase.getInstance().getReference(NODE_EVENTS)
        event.id = dbEvents.push().key
        dbEvents.child(event.id!!).setValue(event)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }

    }
}
