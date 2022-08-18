package com.example.superiortimesystem

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.superiortimesystem.datetime.DateTime


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val dtReceiver = DecimalTime();
        registerReceiver(dtReceiver, IntentFilter(Intent.ACTION_TIME_TICK));

        val ifcReceiver = InternationalFixedCalendarDate();
        registerReceiver(ifcReceiver, IntentFilter(Intent.ACTION_TIME_TICK));

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