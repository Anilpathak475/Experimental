package com.bitnudge.ime.moneygram.libs;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bitnudge.ime.moneygram.R;
import com.bitnudge.ime.moneygram.core.CustomIME;
import com.crashlytics.android.Crashlytics;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getExpiryDate(Context context) {
        try {
            ApplicationInfo app = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = app.metaData;
            return bundle.getString("date_lock_expiry");
        }
        catch(Exception e) { Util.logException(TAG, "getExpiryDate failed", e); return null; }
    }

    public static String getLockIds(Context context) {
        try {
            ApplicationInfo app = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = app.metaData;
            return bundle.getString("device_lock_id");
        }
        catch(Exception e) { Util.logException(TAG, "getLockIds failed", e); return null; }
    }

    private static boolean checkExpiry(Context context) {
        try {
            String expiry_date = getExpiryDate(context);
            Crashlytics.setString("date_lock_expiry", expiry_date);

            if(expiry_date == null) return false;
            //else Util.logDebug(TAG, MessageFormat.format("Expired with: {0}", expiry_date));

            expiry_date = expiry_date.trim();
            if(expiry_date.equalsIgnoreCase("none")) return true;

            SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            Date current = new Date();
            Date expiry = parser.parse(expiry_date);

            if(current.after(expiry)) return false;
            else return true;
        }
        catch(Exception e) { Util.logException(TAG, "checkExpiry failed", e); return false; }
    }

    private static boolean matchId(Context context) {
        try {
            final String android_id = getDeviceId(context);
            Util.logDebug(TAG, MessageFormat.format("android id: {0}", android_id));
            Crashlytics.setUserIdentifier(android_id);

            String locked_id = getLockIds(context);
            Crashlytics.setString("device_lock_id", locked_id);
            if(locked_id == null) return false;
            //else Util.logDebug(TAG, MessageFormat.format("Bolted with: {0}", locked_id));

            locked_id = locked_id.trim();
            if(locked_id.equalsIgnoreCase("all")) return checkExpiry(context);

            if(!locked_id.contains(",")) return android_id.equalsIgnoreCase(locked_id);
            else {
                String[] ids = locked_id.split(",");
                for (String id : ids) {
                    id = id.trim();
                    if(android_id.equalsIgnoreCase(id)) return checkExpiry(context);
                }
                return false;
            }
        }
        catch(Exception e) { Util.logException(TAG, "matchId failed", e); return false; }
    }

    public static void loginGate(CustomIME context) {
        final String LICENCE_KEY = "W3sicGFja2FnZSI6IlduN2FxZGtHTWhaT2VNU1JMUDIrVXNZbkNGOFcyd0VuWklZU3BvcE1Pbjg9IiwiaWQiOjQsImtleSI6IlllaXBlaU5SZEJZVitvdWNjWHNSaXR5TW0wclwvak5jUjNjejZSSUR2MFNjTUswMkhDSXUwd1l2d3BTOUVleFU4bFwvdHFNYkxZK2w1WWU0XC9vNEMrWW9pRk9vSUE2XC96eEdaMUZZRFNibDdjc1JrcjFLanZBM0o0REp5czNtaXhTbVV5RkM4NzdpU0ZiWU9ZekpUUEFxeUhYNW1SVjZ0aGlcL0d4Y20xV1grTVwvTU8xdnB5ZHgrOGg1VjhkWit2YjNtMVlMcFpaTHpVNVlUTGR3MzNsN2ZLVzZ6eUloUk91Nlk2bnhKMndjVkFHUW1UaDRaWFwvSkdtK0lteHJXbXVLUmFreGZOYjRqQnlHa24rdzNyQ1BhSzBtbkVjTDNxMCtiTDFDbjg3ZFdRSkJxTFVKelhTTlRDV0Z4ZU9SaG5ZWUVUWnV0ZW81M1pjTytFODVubzZ4K29pOWc9PSJ9XQ==";

        if (!Util.matchId(context)) {
            Toast.makeText(context, "This ANDROID_ID is not allowed. Drop me a note at adhityan@gamechangesns.com for a new build.", Toast.LENGTH_LONG).show();
            //throw new InvalidConfigurationException("Not Registered");
        }
        else context.setLicenceKey(LICENCE_KEY);
    }

    public static void loginGate(Context context) {
        if (!Util.matchId(context)) {
            Toast.makeText(context, "This ANDROID_ID is not allowed. Drop me a note at adhityan@gamechangesns.com for a new build.", Toast.LENGTH_LONG).show();
            //throw new InvalidConfigurationException("Not Registered");
        }
        else Toast.makeText(context, "GameChange Moneygram Demo!", Toast.LENGTH_LONG).show();
    }

    public static Map<String, String> getHeader(String authToken, String macId) {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        Map<String, String> headerMap = new HashMap<>();

        return headerMap;
    }

}
