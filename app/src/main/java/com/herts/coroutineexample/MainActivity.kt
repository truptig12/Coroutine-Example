package com.herts.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            delay(3000L)
            Log.d(TAG, "Coroutine says hello from thread ${Thread.currentThread().name}")
        }
        Log.d(TAG, " hello from thread ${Thread.currentThread().name}")


        GlobalScope.launch(Dispatchers.IO) {
            val answer = doNetworkCall()
            withContext(Dispatchers.Main){
                val tv = findViewById<TextView>(R.id.tvd)
                tv.text =answer
            }
        }
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "this is dummy"
    }
}