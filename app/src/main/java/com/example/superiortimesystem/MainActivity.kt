package com.example.superiortimesystem

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timeText: TextView = findViewById(R.id.date_and_time) as TextView;
        val updateTime: Thread = object : Thread() {
            override fun run() {
                try {
                    while (!this.isInterrupted) {
                        sleep(1000)
                        runOnUiThread {
                            timeText.text = Calendar.getInstance().timeInMillis.toString();
                            // TODO: display as decimal time and IFC
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }

        updateTime.start()
    }

}