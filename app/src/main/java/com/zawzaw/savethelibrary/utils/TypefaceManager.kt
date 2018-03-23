package com.zawzaw.savethelibrary.utils

import android.content.res.AssetManager
import android.graphics.Typeface
import android.util.LruCache

/**
 * Created by zawzaw on 01/01/18.
 */

class TypefaceManager(private val manager: AssetManager) {

    private val mCache: LruCache<String, Typeface>

    val shitUnicode: Typeface
        get() = getTypeface(PYIDAUNGSU)
    val shitZawgyi: Typeface
        get() = getTypeface(ZAWGYIONE)
    val digitFont: Typeface
        get() = getTypeface(DIGIT)
    val passcodeFont: Typeface
        get() = getTypeface(PASSCODEFONT)
    val robotoFont: Typeface
        get() = getTypeface(ROBOTO)
    val myanmarSager: Typeface
        get() = getTypeface(MYANMAR_SAGAR)

    init {
        val cacheSize = 4 * 1024 * 1024
        mCache = LruCache(cacheSize)
    }

    fun getTypeface(filename: String): Typeface {
        var typeface: Typeface? = mCache.get(filename)
        if (typeface == null) {
            typeface = Typeface.createFromAsset(manager, filename)
        }
        return typeface
    }

    companion object {

        val PYIDAUNGSU = "pyidaungsu.ttf"
        val ZAWGYIONE = "zawgyi_one.ttf"
        val PASSCODEFONT = "Font-Regular.ttf"
        val ROBOTO = "roboto.ttf"
        val DIGIT = "digit.ttf"
        val MYANMAR_SAGAR = "myanmar_sagar.ttf"
    }

}
