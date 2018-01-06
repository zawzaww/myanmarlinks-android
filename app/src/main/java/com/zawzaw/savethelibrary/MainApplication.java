package com.zawzaw.savethelibrary;

import android.app.Application;
import me.myatminsoe.mdetect.MDetect;
import com.zawzaw.savethelibrary.utils.FontEmbedder;
import com.zawzaw.savethelibrary.utils.Moulder;
import com.zawzaw.savethelibrary.utils.TypefaceManager;
import com.zawzaw.savethelibrary.utils.receiver.ConnectionReceiver;

/**
 * Created by zawzaw on 31/12/17.
 */

public class MainApplication extends Application {

    public static TypefaceManager typefaceManager;

    public static MainApplication mInstance;

    public static synchronized MainApplication getmInstance() {
        return mInstance;
    }

    public void setConnectionLinstener(ConnectionReceiver.ConnectionReciverLinstener linstener) {
        ConnectionReceiver.connectionReciverLinstener = linstener;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

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
