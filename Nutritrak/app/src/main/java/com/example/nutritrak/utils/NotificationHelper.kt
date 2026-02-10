package com.example.nutritrak.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.nutritrak.R

object NotificationHelper {

    private const val CHANNEL_ID = "nutritrak_notifications"
    private const val CHANNEL_NAME = "Nutritrak Notifications"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifications for meal tracking and meal plans"
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    fun showNotification(
        context: Context,
        notificationId: Int,
        title: String,
        message: String,
        smallIcon: Int = R.mipmap.ic_launcher
    ) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(smallIcon)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager?.notify(notificationId, notification)
    }

    fun showMealSavedNotification(context: Context, mealName: String) {
        showNotification(
            context,
            1,
            "Meal Saved",
            "$mealName has been added to your nutrition tracker"
        )
    }

    fun showPlanCreatedNotification(context: Context, planName: String) {
        showNotification(
            context,
            2,
            "Plan Created",
            "$planName has been successfully created"
        )
    }

    fun showCalorieGoalNotification(context: Context, remaining: Int) {
        showNotification(
            context,
            3,
            "Calorie Goal Update",
            "You have $remaining calories remaining today"
        )
    }
}