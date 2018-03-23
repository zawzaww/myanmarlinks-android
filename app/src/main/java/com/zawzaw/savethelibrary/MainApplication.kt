package com.zawzaw.savethelibrary

import android.app.Application
import me.myatminsoe.mdetect.MDetect
import com.zawzaw.savethelibrary.utils.FontEmbedder
import com.zawzaw.savethelibrary.utils.Moulder
import com.zawzaw.savethelibrary.utils.TypefaceManager

/**
 * Created by zawzaw on 31/12/17.
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MDetect.init(this)
        typefaceManager = TypefaceManager(assets)

        if (MDetect.isUnicode()) {
            FontEmbedder.init(typefaceManager.shitUnicode)
            Moulder.init(true)
        } else {
            FontEmbedder.init(typefaceManager.shitZawgyi)
            Moulder.init(false)
        }
    }

    companion object {

        lateinit var typefaceManager: TypefaceManager
    }

}
