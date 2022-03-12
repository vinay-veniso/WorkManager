package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val inputDataForPeriodicReq = workDataOf("a" to String)

        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<MyWorkerClass>(15, TimeUnit.MINUTES)
                 .build()


        val oneTimeworkRequest = OneTimeWorkRequestBuilder<MyWorkerClass>()
            .setInitialDelay(2, TimeUnit.MINUTES)
             .build()

        pButton.setOnClickListener {

            WorkManager.getInstance(this).enqueue(periodicWorkRequest)


        }
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(periodicWorkRequest.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Toast.makeText(this, "periodic Work succeeded", Toast.LENGTH_LONG).show()
                }
            })


        oButton.setOnClickListener {
            WorkManager.getInstance(this).enqueue(oneTimeworkRequest)
        }

    }
}