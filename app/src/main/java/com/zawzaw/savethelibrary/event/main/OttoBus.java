package com.zawzaw.savethelibrary.event.main;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by zawzaw on 31/12/17.
 */

public class OttoBus {

    private static Bus bus;

    public static Bus getBus() {
        if (bus == null) {
            bus = new Bus(ThreadEnforcer.MAIN);
        }

        return bus;
    }

}
