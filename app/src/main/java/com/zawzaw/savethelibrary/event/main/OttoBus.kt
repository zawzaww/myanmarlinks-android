package com.zawzaw.savethelibrary.event.main

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

/**
 * Created by zawzaw on 31/12/17.
 */

object OttoBus {

    private var bus: Bus? = null

    fun getBus(): Bus {
        if (bus == null) {
            bus = Bus(ThreadEnforcer.MAIN)
        }

        return bus
    }

}
