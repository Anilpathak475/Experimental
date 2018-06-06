package com.bitnudge.ime.demo.libs;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;

import java.text.MessageFormat;

/**
 * Created by Adhityan on 3/20/18.
 */

public class Util {
    private final static String appId = "fd11e97e3f3803d2";
    private static String TAG = Util.class.getSimpleName();
    private static String BASE_TAG = CustomIME.class.getSimpleName();

    public static void makeTapSound(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        try {
            if (am != null) am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD, 0.9f);
        } catch (Exception e) {
            Util.logException(TAG, "onClick", e);
        }
    }

    public static void logDebug(String TAG, String message) {
        Log.d(MessageFormat.format("{0}:{1}", BASE_TAG, TAG), message);
    }

    public static void logException(String TAG, String source, Exception e) {
        if (e != null) Log.e(MessageFormat.format("{0}:{1}", BASE_TAG, TAG), source, e);
        else Log.e(MessageFormat.format("{0}:{1}", BASE_TAG, TAG), source);
    }

    public static void logException(String TAG, String source) {
        logException(TAG, source, null);
    }

    public static void logException(String source, Exception e) {
        logException("", source, e);
    }

    public static void logException(String source) {
        logException("", source, null);
    }

    public static void showView(Context context, final View view) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_down);
        //use this to make it longer:  animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }
        });

        view.startAnimation(animation);
    }

    public static Animation hideView() {
        Animation animation = new TranslateAnimation(0, -1000, 0, 0); //May need to check the direction you want.
        animation.setDuration(500);
        animation.setFillAfter(true);
        return animation;
    }

    public static boolean matchId(String givenId) {
        return appId.equalsIgnoreCase(givenId);

    }
}
