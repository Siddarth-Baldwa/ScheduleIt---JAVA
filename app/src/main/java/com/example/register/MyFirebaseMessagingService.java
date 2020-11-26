package com.example.register;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification2(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification2(String title, String message) {
        String id = "main_channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        CharSequence name = "Channel Name";
        String description = "Channel Description";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel = new NotificationChannel(id, name, importance);
        notificationChannel.setDescription(description);
        notificationChannel.enableVibration(true);

        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent resintent = new Intent(this, Notif_Main.class);
        PendingIntent respenindent = PendingIntent.getActivity(this, 1, resintent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id);
        builder.setSmallIcon(R.drawable.icons8_image_96);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(respenindent);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1000, builder.build());
    }




}