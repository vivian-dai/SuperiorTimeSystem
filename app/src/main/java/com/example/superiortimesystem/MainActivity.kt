package com.example.superiortimesystem

import android.content.res.Configuration
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.superiortimesystem.datetime.DateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val dateText: TextView = findViewById(R.id.date) as TextView;
        val timeText: TextView = findViewById(R.id.time) as TextView;
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