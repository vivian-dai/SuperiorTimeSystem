package com.example.superiortimesystem

import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.superiortimesystem.datetime.DateTime


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val dateText: TextView = findViewById<TextView>(R.id.date);
        val timeText: TextView = findViewById<TextView>(R.id.time);
        val updateTime: Thread = object : Thread() {
            override fun run() {
                try {
                    while (!this.isInterrupted) {
                        sleep(1000)
                        runOnUiThread {
                            dateText.text = DateTime().ifcDate();
                            timeText.text = DateTime().decimalTime();
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }

        updateTime.start()
    }


}