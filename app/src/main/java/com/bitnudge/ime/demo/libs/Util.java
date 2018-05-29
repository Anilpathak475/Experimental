package com.bitnudge.ime.demo.libs;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.bitnudge.ime.demo.core.CustomIME;

import java.text.MessageFormat;

/**
 * Created by Adhityan on 3/20/18.
 */

public class Util {
    private static String TAG = Util.class.getSimpleName();
    private static String BASE_TAG = CustomIME.class.getSimpleName();

    public static void makeTapSound(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        try {
            if(am != null) am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD, 0.9f);
        } catch (Exception e) { Util.logException(TAG, "onClick", e); }
    }

    public static void logDebug(String TAG, String message) {
        Log.d(MessageFormat.format("{0}:{1}", BASE_TAG, TAG), message);
    }

    public static void logException(String TAG, String source, Exception e) {
        if(e != null) Log.e(MessageFormat.format("{0}:{1}", BASE_TAG, TAG), source, e);
        else Log.e(MessageFormat.format("{0}:{1}", BASE_TAG, TAG), source);
    }

    public static void logException(String TAG, String source) { logException(TAG, source, null); }
    public static void logException(String source, Exception e) { logException("", source, e); }
    public static void logException(String source) { logException("", source, null); }
}
