package com.jkdajac.debounse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 1..3) {
            btButton.setOnClickListener {
                async {
                    delay(2000)
                    display()
                }
            }
        }
    }
    fun display(){
            val text  = tvText.text.toString()
            var count : Int = Integer.parseInt(text)
            count++
            tvText.text = count.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}