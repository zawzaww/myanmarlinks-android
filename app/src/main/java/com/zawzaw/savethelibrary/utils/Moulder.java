package com.zawzaw.savethelibrary.utils;

import me.myatminsoe.mdetect.Rabbit;

/**
 * Created by zawzaw on 01/01/18.
 */

public class Moulder {

    private static boolean isUnicode;

    public static void init(boolean isUnicode) {
        Moulder.isUnicode = isUnicode;
    }

    public static String stringToUni(String text) {
        if (!isUnicode) {
            return Rabbit.zg2uni(text);
        }
        return text;
    }

    public static String mercyOnZgUser(String text) {
        if (isUnicode) {
            return text;
        } else {
            return Rabbit.uni2zg(text);
        }
    }


}
