package app.watanabe.wataso.juststop

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TERM_MILLISECOND: Long = 100
    }

    lateinit var figureText:TextView
    lateinit var secondText:TextView
    lateinit var startButton: Button
    lateinit var stopButton: Button
    lateinit var resetButton:Button

    var second =30

//    val timer : CountDownTimer = object : CountDownTimer(30000,1000){
//
//        override fun onFinish() {
//            secondText.isVisible = true
//            second=30
//            secondText.text= second.toString()
//            TODO("Not yet implemented")
//
//        }
//
//        override fun onTick(millisUntilFinished: Long) {
//            second = second -1
//            secondText.text = second.toString()
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        figureText = findViewById(R.id.figure)
        secondText = findViewById(R.id.secondText)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)

        var time = 0L
        val dataFormat = SimpleDateFormat("mm:ss.S", Locale.getDefault())


        val handler = Handler()
        val timer = object : Runnable {
            override fun run() {
                // timeに0.1秒を追加
                time += TERM_MILLISECOND
                var genshou=dataFormat.format(time)
                //時間を表示
                secondText.text =dataFormat.format(time)
                // 0.1秒で再度呼ばれるようにする
                handler.postDelayed(this, TERM_MILLISECOND)
            }
        }


        secondText.text =second.toString()

        startButton.setOnClickListener {
            secondText.isVisible=false

            var odai:Int= (1..29).random()
            figureText.text =odai.toString()
            handler.post(timer)

            startButton.isEnabled = false
            stopButton.isEnabled = true
            resetButton.isEnabled = false
        }
        stopButton.setOnClickListener{
            secondText.isVisible = true
            handler.removeCallbacks(timer)

            startButton.isEnabled = false
            stopButton.isEnabled = false
            resetButton.isEnabled = true


        }
        resetButton.setOnClickListener {
            secondText.isVisible = true
            second=30
            secondText.text= second.toString()

            startButton.isEnabled = true
            stopButton.isEnabled = false
            resetButton.isEnabled = false

        }


    }

}
