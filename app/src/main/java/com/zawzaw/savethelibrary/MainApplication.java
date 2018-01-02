package com.zawzaw.savethelibrary;

import android.app.Application;
import me.myatminsoe.mdetect.MDetect;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.Moulder;
import com.zawzaw.savethelibrary.utils.TypefaceManager;

/**
 * Created by zawzaw on 31/12/17.
 */

public class MainApplication extends Application {

    public static TypefaceManager typefaceManager;

    @Override
    public void onCreate() {
        super.onCreate();

        MDetect.INSTANCE.init(this);
        typefaceManager = new TypefaceManager(getAssets());

        if (MDetect.INSTANCE.isUnicode()) {
            FontEmbedder.init(typefaceManager.getShitUnicode());
            Moulder.init(true);
        } else {
            FontEmbedder.init(typefaceManager.getShitZawgyi());
            Moulder.init(false);
        }
    }

}
