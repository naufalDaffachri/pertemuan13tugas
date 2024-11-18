package com.example.pertemuan12tugas

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan12tugas.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId = "TEST_NOTIF"

    companion object {
        var likeCount = 0
        var dislikeCount = 0
        var updateUI: (() -> Unit)? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUI = { updateCounts() }
        updateCounts()

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.buttonNotif.setOnClickListener {
            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }

            val likeIntent = Intent(this, NotifReceiver::class.java).apply {
                action = "ACTION_LIKE"
            }
            val likePendingIntent = PendingIntent.getBroadcast(this, 0, likeIntent, flag)

            val dislikeIntent = Intent(this, NotifReceiver::class.java).apply {
                action = "ACTION_DISLIKE"
            }
            val dislikePendingIntent = PendingIntent.getBroadcast(this, 1, dislikeIntent, flag)

            val notifImage = BitmapFactory.decodeResource(resources, R.drawable.hanip)
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Counter")
                .setContentText("Govannnnnnnnnnn gamtenk")
                .setAutoCancel(true)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(notifImage)
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Like", likePendingIntent)
                .addAction(0, "Dislike", dislikePendingIntent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(
                    channelId, "Counter", NotificationManager.IMPORTANCE_DEFAULT
                )
                notifManager.createNotificationChannel(notifChannel)
            }
            notifManager.notify(0, builder.build())
        }
    }

    private fun updateCounts() {
        binding.tvLikeCount.text = likeCount.toString()
        binding.tvDislikeCount.text = dislikeCount.toString()
    }
}
