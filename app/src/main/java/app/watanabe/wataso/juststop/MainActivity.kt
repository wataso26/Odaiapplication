package app.watanabe.wataso.juststop

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    lateinit var secondText:TextView
    lateinit var startButton: Button

    var second =60

    val timer : CountDownTimer = object : CountDownTimer(60000,1000){

        override fun onFinish() {
            secondText.isVisible = true
            second=60
            secondText.text= second.toString()
            TODO("Not yet implemented")

        }

        override fun onTick(millisUntilFinished: Long) {
            second = second -1
            secondText.text = second.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        secondText = findViewById(R.id.secondText)


        secondText.text =second.toString()

        startButton.setOnClickListener {
            secondText.isVisible=false

            timer.start()
        }
    }

}
