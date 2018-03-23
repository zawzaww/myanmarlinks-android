package com.zawzaw.savethelibrary.utils

import me.myatminsoe.mdetect.Rabbit

/**
 * Created by zawzaw on 01/01/18.
 */

object Moulder {

    private var isUnicode: Boolean = false

    fun init(isUnicode: Boolean) {
        Moulder.isUnicode = isUnicode
    }

    fun stringToUni(text: String): String {
        return if (!isUnicode) {
            Rabbit.zg2uni(text)
        } else text
    }

    fun mercyOnZgUser(text: String): String {
        return if (isUnicode) {
            text
        } else {
            Rabbit.uni2zg(text)
        }
    }


}
