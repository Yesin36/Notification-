package com.example.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notification.app.Companion.CHANNEL_ID1
import com.example.notification.app.Companion.CHANNEL_ID2
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.high.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            // Create the notification
            val notification = NotificationCompat.Builder(this, CHANNEL_ID1)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(binding.title.text.toString())
                .setContentText(binding.content.text.toString())

                .setPriority(NotificationCompat.PRIORITY_HIGH)  // for low sdk device but need same priority
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.MAGENTA)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_android_black_24dp,"back",pendingIntent)
                .addAction(R.drawable.ic_android_black_24dp,"play",null)
                .addAction(R.drawable.ic_android_black_24dp,"next",null)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build()  // Build the notification here

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1, notification)   // Send the notification
        }

        binding.low.setOnClickListener {
            val notification = NotificationCompat.Builder(this, CHANNEL_ID2)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(binding.title.text.toString())
                .setContentText(binding.content.text.toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)  // for low sdk device but need same priority
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.MAGENTA)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build()  // Build the notification here

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(2, notification)   // Send the notification
        }
    }

    }