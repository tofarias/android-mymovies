package com.example.tiago.mymovies.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class WifiActiveService extends Service {

    private final static String TAG = WifiActiveService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WifiInfo info = wifiManager.getConnectionInfo();
                String mac = info.getMacAddress();
                String ssid = info.getSSID();
                if (Log.isLoggable(TAG, Log.VERBOSE)) {
                    Log.v(TAG, "O SSI e o MAC são: " + ssid + " " + mac);
                }
                createNotification(ssid, mac);
                stopSelf();
            }
        }, 5000);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotification(String ssid, String mac) {
        Notification n = new NotificationCompat.Builder(this)
                .setContentTitle("Conexão Wifi")
                .setContentText("Conectado à " + ssid)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Você está conectado à " + ssid + " de " + mac))

                .build();
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
                .notify(0, n);
    }
}
