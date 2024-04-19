package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.children
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var flag=0
    private var counter=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            //reset click listener
            btnReset.setOnClickListener {
                //Resetting the score of both players
                //clearing the titles again
            }
        }
    }

    fun oxClick(view: View)
    {
        val btn = view as ImageView
        binding.apply {
            if (btn.drawable == null)
            {
               //one move
                counter++
                //assigning the turn to the player
                when(flag)
                {
                    0 -> {
                        
                    }
                }
            }
        }
    }

    private fun newScore(tag:String){
        when(tag)
        {
            TAG_X-> {
             //Current score of user x
                val xPoint= binding.boardXCount.text.toString().toInt()
                binding.boardXCount.text =(xPoint +1).toString()
            }
            TAG_O -> {
                //Current score of user 0
                val oPoint= binding.board0Count.text.toString().toInt()
                binding.board0Count.text =(oPoint +1).toString()
            }
            TAG_RESET -> {
              //Resetting the whole board
                binding.board0Count.text="0"
                binding.boardXCount.text="0"
            }
        }
    }

    private fun newGame(){
        //change the turn
        flag=0

        //resetting the number of moves
        counter=0
       //fina all image views and clear all the tags and views of the drawables from it
        binding.gridLayout.children.filterIsInstance<ImageView>().forEach { iv->
             iv.setImageDrawable(null)
            iv.tag = null
            iv.setBackgroundResource(R.drawable.board_back)

            //adding a green border to the user whose turn it is to play
            binding.card0.strokeWidth = 2
            binding.cardX.strokeWidth = 0
        }
    }
}