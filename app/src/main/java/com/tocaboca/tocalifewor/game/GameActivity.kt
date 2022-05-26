package com.tocaboca.tocalifewor.game

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import com.tocaboca.tocalifewor.R

class GameActivity : AppCompatActivity() {
    private val listImages: List<Int> = listOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e,
        R.drawable.f,
        R.drawable.g,
    )
    private lateinit var iv1: ImageView
    private lateinit var iv2: ImageView
    private lateinit var iv3: ImageView
    private lateinit var btSpin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        btSpin = findViewById(R.id.btSpin)
        iv1 = findViewById(R.id.iv1)
        iv2 = findViewById(R.id.iv2)
        iv3 = findViewById(R.id.iv3)

        btSpin.setOnClickListener {
            btSpin.isEnabled = false
            spin(iv1, listImages)
            spin(iv2, listImages)
            spin(iv3, listImages)
        }
    }

    private fun spin(iv: ImageView, lisImages: List<Int>) {
        ValueAnimator.ofInt(1, 100).apply {
            duration = 1000

            addUpdateListener {
                val index = lisImages.indices.random()
                iv.setImageResource(lisImages[index])
                iv.tag = index
            }
            addListener(onEnd = {
                btSpin.isEnabled = true
                var congrats = ""
                congrats = if (iv1.tag == iv2.tag && iv2.tag == iv3.tag) {
                    "You won"
                } else {
                    "You lose"
                }
                Toast.makeText(this@GameActivity, congrats, Toast.LENGTH_SHORT).show()
            })
            start()
        }
    }
}