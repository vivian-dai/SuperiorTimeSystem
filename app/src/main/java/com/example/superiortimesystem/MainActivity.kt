package com.example.superiortimesystem

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.superiortimesystem.datetime.DateTime

/**
 * Main activity
 *
 * Where all the foreground tasks of the app runs
 * Inherits from `AppCompatActivity`
 */
class MainActivity : AppCompatActivity() {
    /**
     * Initiates things to do upon the app opening
     * Sets up the widgets' `BroadcastReceiver`s
     * Runs a thread that updates the date and time every second (1000 milliseconds)
     *
     * @param savedInstanceState the last state of the app
     * @throws InterruptedException if the thread is interrupted
     */
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