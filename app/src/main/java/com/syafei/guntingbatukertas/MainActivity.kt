package com.syafei.guntingbatukertas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.syafei.guntingbatukertas.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var selected: Int? = null //playmode
    private var marker: Int = -1 //flag

    private var playerOneSelect: Int? = null
    private var playerTwoSelect: Int? = null

    private val TAG = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        letsPlay()
        reset()

    }

    private fun reset() {
        setBgUserSelection(-1)
        setBgComSelection(-1)
        playerOneSelect = null
        playerTwoSelect = null
        binding.ivreset.setImageResource(R.drawable.ic_refresh)
    }

    private fun letsPlay() {

        if (selected == 0) {
            marker = 0
            proccedPlayerOne()
            proccedPlayerTwo()
        } else {
            marker = 1
            proccedPlayerOne()
        }
    }

    private fun proccedPlayerTwo() {
        binding.include2.playerTwoBatu.setOnClickListener {
            Log.e(TAG, "com playing batu")
            playerTwoSelect = 0
            playerOneSelect?.let { it1 -> gameStart(it1, playerTwoSelect!!) }
        }

        binding.include2.playerTwoKertas.setOnClickListener {
            Log.e(TAG, "com playing kertas")
            playerTwoSelect = 1
            playerOneSelect?.let { it1 -> gameStart(it1, playerTwoSelect!!) }
        }

        binding.include2.playerTwoGunting.setOnClickListener {
            Log.e(TAG, "com playing gunting")
            playerTwoSelect = 2
            playerOneSelect?.let { it1 -> gameStart(it1, playerTwoSelect!!) }
        }
    }

    private fun proccedPlayerOne() {
        var random = 0
        binding.include.playerOneBatu.setOnClickListener {
            Log.e(TAG, "player one has selected batu")
            playerOneSelect = 0
            setBgUserSelection(0)

            if (selected != 0) {
                random = Random.nextInt(0, 3)
                setBgComSelection(random)
                gameStart(playerOneSelect!!, random)
            }
        }

        binding.include.playerOneKertas.setOnClickListener {
            Log.e(TAG, "player one has selected kertas")
            playerOneSelect = 1
            setBgUserSelection(1)

            if (selected != 0) {
                random = Random.nextInt(0, 3)
                setBgComSelection(random)
                gameStart(playerOneSelect!!, random)
            }
        }

        binding.include.playerOneGunting.setOnClickListener {
            Log.e(TAG, "player one has selected gunting")
            playerOneSelect = 2
            setBgUserSelection(2)

            if (selected != 0) {
                random = Random.nextInt(0, 3)
                setBgComSelection(random)
                gameStart(playerOneSelect!!, random)
            }
        }
    }

    private fun setBgComSelection(comRandom: Int) {
        when (Selection.fromSelection(comRandom)) {
            Selection.ROCK -> {
                binding.include2.playerTwoBatu.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.teal_700))
                binding.include2.playerTwoGunting.setBackgroundColor(0)
                binding.include2.playerTwoKertas.setBackgroundColor(0)
            }

            Selection.PAPER -> {
                binding.include2.playerTwoKertas.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.teal_700))
                binding.include2.playerTwoGunting.setBackgroundColor(0)
                binding.include2.playerTwoBatu.setBackgroundColor(2)
            }

            Selection.SCISSOR -> {
                binding.include2.playerTwoGunting.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.teal_700))
                binding.include2.playerTwoBatu.setBackgroundColor(0)
                binding.include2.playerTwoKertas.setBackgroundColor(0)
            }


            else -> {
                binding.include2.playerTwoBatu.setBackgroundColor(0)
                binding.include2.playerTwoKertas.setBackgroundColor(0)
                binding.include2.playerTwoGunting.setBackgroundColor(0)
            }

        }

    }


    private fun gameStart(playerOneSelected: Int, playerTwoSelected: Int) {
        if (playerOneSelected != null) {
            if ((playerOneSelected.plus(1)).mod(3) == playerTwoSelected) {
                Log.d(TAG, "Computer won")
                binding.tvResultCenter.setText("Computer WON")

            } else if (playerOneSelected.equals(playerTwoSelected)) {
                Log.d(TAG, " draw")
                binding.tvResultCenter.setText("draw")

            } else {
                Log.d(TAG, "User won")
                binding.tvResultCenter.setText("YOU WIN!!")

            }

        }
    }

    private fun setBgUserSelection(userSelectionBg: Int) {
        when (Selection.fromSelection(userSelectionBg)) {
            Selection.ROCK -> {
                binding.include.playerOneBatu.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.teal_700)
                )
                binding.include.playerOneGunting.setBackgroundColor(0)
                binding.include.playerOneKertas.setBackgroundColor(0)
            }

            Selection.PAPER -> {
                binding.include.playerOneKertas.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.teal_700)
                )
                binding.include.playerOneGunting.setBackgroundColor(0)
                binding.include.playerOneBatu.setBackgroundColor(2)
            }

            Selection.SCISSOR -> {
                binding.include.playerOneGunting.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.teal_700)
                )
                binding.include.playerOneBatu.setBackgroundColor(0)
                binding.include.playerOneKertas.setBackgroundColor(0)
            }


            else -> {
                binding.include.playerOneBatu.setBackgroundColor(0)
                binding.include.playerOneKertas.setBackgroundColor(0)
                binding.include.playerOneGunting.setBackgroundColor(0)
            }

        }
    }
}