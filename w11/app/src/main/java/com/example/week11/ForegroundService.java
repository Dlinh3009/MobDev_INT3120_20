package com.example.week11;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {
    private boolean isServiceRunning = false;
    private String userText = "";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isServiceRunning && intent != null) {
            userText = intent.getStringExtra("user_text");
            if (userText != null && !userText.isEmpty()) {
                isServiceRunning = true;
                startForeground(1, buildForegroundNotification());
            }
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification buildForegroundNotification() {
        Intent notificationIntent = new Intent(this, ForegroundActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle("User Text Service")
                .setContentText("Text: " + userText)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();
    }
}


