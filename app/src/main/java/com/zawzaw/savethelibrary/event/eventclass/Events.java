package com.zawzaw.savethelibrary.event.eventclass;

/**
 * Created by zawzaw on 03/01/18.
 */

public class Events {

    public static class NoInternetConection {

        private String message;

        public NoInternetConection (String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
