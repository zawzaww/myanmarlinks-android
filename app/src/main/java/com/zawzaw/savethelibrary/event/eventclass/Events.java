package com.zawzaw.savethelibrary.event.eventclass;

/**
 * Created by zawzaw on 27/12/17.
 */

public class Events
{
    public static class NoInternetConnection
    {
        private String message;

        public NoInternetConnection (String message)
        {
            this.message = message;
        }

        public String getMessage()
        {
            return message;
        }
    }

}
