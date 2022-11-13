package com.harye.humaneheart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.harye.humaneheart.R
import com.harye.humaneheart.data.Event
import kotlinx.android.synthetic.main.fragment_event_add.*

/**
 * Created by Harris on 09/11/2022
 */

class EventAddFragment : Fragment() {

    private lateinit var viewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_add, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Observe result
        viewModel.result.observe(viewLifecycleOwner, Observer {

            val message = if (it == null) {
                getString(R.string.event_added)
            } else {
                getString(R.string.error, it.message)
            }
            val action = EventAddFragmentDirections.actionEventAddFragmentToEventListFragment()
            findNavController().navigate(action)
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        })

        save_action.setOnClickListener {
            val name = event_name.text.toString().trim()
            val description = event_description.text.toString().trim()
            val whatsAppLink = event_whatsapp_link.text.toString().trim()
            val organizerTel = event_organizer_phone.text.toString().trim()

            if (name.isEmpty() || description.isEmpty() || whatsAppLink.isEmpty()
                || (organizerTel.isEmpty() || organizerTel.length > 10)
            ) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_field_required),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener /* Stop further Execution*/
            }
            val event = Event()
            event.eventName = name
            event.eventDescription = description
            event.whatsAppLink = whatsAppLink
            event.OrganizerPhone = organizerTel

            viewModel.addEvent(event)
        }

    }

}