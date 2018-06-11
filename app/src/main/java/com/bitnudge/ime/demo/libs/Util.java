package com.bitnudge.ime.demo.libs;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bitnudge.ime.demo.R;
import com.bitnudge.ime.demo.core.CustomIME;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Adhityan on 3/20/18.
 */

public class Util {
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

    public static void showView(final CustomIME context, final View view) {
        try {
            context.showCustomView(view);
        }
        catch (Exception e) { Util.logException(TAG, "showView", e); }

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        view.startAnimation(animation);
    }

    public static void hideView(final CustomIME context, final View view) {
        /*Animation animation = new TranslateAnimation(0, -1000, 0, 0); //May need to check the direction you want.
        animation.setDuration(500);
        animation.setFillAfter(true);*/

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public static String getStringFromDate(Date date) {
        String formatDate = "dd/MM/yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formatDate, Locale.ENGLISH);
        return dateFormatter.format(date);
    }

    public static String getDay(Date date) {
        String dateFormat = "EEEE";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        return dateFormatter.format(date);
    }

    public static void sendNotification(Context context, String name, String description) {
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_icon);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "BitNudge")
                .setSmallIcon(R.drawable.ic_launcher_icon)
                .setLargeIcon(bm)
                .setContentTitle(name)
                .setContentText(description)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(description))
                .setPriority(NotificationCompat.PRIORITY_MAX);


        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 21) mBuilder.setVibrate(new long[0]);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("BitNudge", name, NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setBypassDnd(true);
            channel.setShowBadge(false);

            mNotificationManager.createNotificationChannel(channel);
        }

        mNotificationManager.notify(0, mBuilder.build());
    }

    public static void sendNotification(Context context, String name, String description, Bitmap background) {
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_icon);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "BitNudge")
                .setBadgeIconType(R.drawable.ic_launcher_icon)
                .setSmallIcon(R.drawable.ic_launcher_icon)
                .setLargeIcon(bm)
                .setContentTitle(name)
                .setContentText(description)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(background))
                .setPriority(NotificationCompat.PRIORITY_MAX);


        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 21) mBuilder.setVibrate(new long[0]);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("bitnudge-demo-bank", context.getApplicationContext().getPackageName(), NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.YELLOW);
            channel.enableVibration(true);
            channel.setBypassDnd(true);
            channel.setShowBadge(false);

            mNotificationManager.createNotificationChannel(channel);
        }

        mNotificationManager.notify(0, mBuilder.build());
    }
}
