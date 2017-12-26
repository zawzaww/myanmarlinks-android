package com.zawzaw.savethelibrary.utils;


/**
 * Created by zawzaw on 26/12/17.
 */

public class Modular
{
    public static boolean isUnicode;

    public static void init(boolean isUnicode)
    {
        Modular.isUnicode = isUnicode;
    }

    public static String stringToUni(String text)
    {
        if (!isUnicode)
        {
            return Rabbit.zg2uni(text);
        }
        return text;
    }

    public static String mercyOnZgUser(String text)
    {
        if (isUnicode)
        {
            return text;
        }
        else {
            return Rabbit.uni2zg(text);
        }
    }

}
