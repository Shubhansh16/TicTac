package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import com.example.tictactoe.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                newScore(TAG_RESET)
                //clearing the titles again
                newGame()
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
                       //turn of the first player
                        btn.setImageResource(R.drawable.ic_o)
                        btn.tag= TAG_O
                        card0.strokeWidth =0
                        cardX.strokeWidth =2
                        //changing the turn
                        flag=1
                    }

                    1 -> {
                        //turn of the second player
                        btn.setImageResource(R.drawable.ic_x)
                        btn.tag = TAG_X
                        cardX.strokeWidth =0
                        cardX.strokeWidth =2
                        //changing the turn
                        flag=0
                    }
                }
                //using a lifecycle method to introduce a delay
                lifecycleScope.launch {
                    //checking the pre-defined conditions for winning the XO game
                    if(iv1.tag==iv2.tag && iv2.tag==iv3.tag && iv3.tag!=null) {
                       //find the winner of the game through the selected image view tag
                        newScore(iv1.tag.toString())
                        withAnimation(iv1,iv2,iv3)
                    }
                    else if(iv4.tag==iv5.tag && iv5.tag==iv6.tag && iv6.tag!=null) {
                        //find the winner of the game through the selected image view tag
                        newScore(iv4.tag.toString())
                        withAnimation(iv4,iv5,iv6)
                    }
                    else  if(iv7.tag==iv8.tag && iv8.tag==iv9.tag && iv9.tag!=null) {
                        //find the winner of the game through the selected image view tag
                        newScore(iv7.tag.toString())
                        withAnimation(iv7,iv8,iv9)
                    }
                    else  if(iv1.tag==iv5.tag && iv5.tag==iv9.tag && iv9.tag!=null) {
                        //find the winner of the game through the selected image view tag
                        newScore(iv1.tag.toString())
                        withAnimation(iv1,iv5,iv9)
                    }
                    else  if(iv3.tag==iv5.tag && iv5.tag==iv7.tag && iv7.tag!=null) {
                        //find the winner of the game through the selected image view tag
                        newScore(iv3.tag.toString())
                        withAnimation(iv3,iv5,iv7)
                    }
                    else  if(iv1.tag==iv4.tag && iv4.tag==iv7.tag && iv7.tag!=null) {
                        //find the winner of the game through the selected image view tag
                        newScore(iv1.tag.toString())
                        withAnimation(iv1,iv4,iv7)
                    }
                    else  if(iv3.tag==iv6.tag && iv6.tag==iv9.tag && iv9.tag!=null) {
                        //find the winner of the game through the selected image view tag
                        newScore(iv3.tag.toString())
                        withAnimation(iv3,iv6,iv9)
                    }
                    else  if(iv2.tag==iv5.tag && iv5.tag==iv8.tag && iv8.tag!=null) {
                        //find the winner of the game through the selected image view tag
                        newScore(iv2.tag.toString())
                        withAnimation(iv2,iv5,iv8)
                    }
                    else if(counter==9){
                        //if all the tiles are filled and there is no winner we empty the tiles
                        Toast.makeText(this@MainActivity, "No clear winner", Toast.LENGTH_SHORT).show()
                        newGame()
                    }
                }
            }
        }
    }

    private suspend fun withAnimation(viewOne:View, viewTwo:View, viewThree:View){
        //changing the color of the correct tiles to green one by one
        viewOne.setBackgroundResource(R.drawable.board_back_green)
        delay(200)
        viewTwo.setBackgroundResource(R.drawable.board_back_green)
        delay(200)
        viewThree.setBackgroundResource(R.drawable.board_back_green)
        delay(200)
        newGame()
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