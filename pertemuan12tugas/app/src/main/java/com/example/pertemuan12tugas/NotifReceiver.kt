package com.example.pertemuan12tugas

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotifReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "ACTION_LIKE" -> {
                MainActivity.likeCount++
            }
            "ACTION_DISLIKE" -> {
                MainActivity.dislikeCount++
            }
        }

        MainActivity.updateUI?.invoke()
    }
}
