package com.example.mengl03.tictactoegame;

/*
*   CSCI 4325 Mobile Application Development
*   Chapter 3 Tic Tac Toe Game
*   Controller - MainActivity.java
*   Micheal English, 20180905
* */

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TicTacToe game;
    private ButtonGridAndTextView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new TicTacToe();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIDE;
        ButtonHandler bh = new ButtonHandler();
        gridView = new ButtonGridAndTextView(this, w, TicTacToe.SIDE, bh);  //
        gridView.setStatusText(game.result());
        setContentView(gridView);
    }
        public void showNewGameDialog(){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("This is fun");
            alert.setMessage("Play again? ");
            PlayDialog playAgain = new PlayDialog();
            alert.setPositiveButton("YES", playAgain);
            alert.setNegativeButton("NO", playAgain );
            alert.show();
        }

        private class ButtonHandler implements View.OnClickListener{
            public void onClick(View v){
                for(int row = 0; row < TicTacToe.SIDE; row++){
                    for(int column = 0; column <TicTacToe.SIDE; column++)
                    if(gridView.isButton((Button) v, row, column)){
                        int play = game.play(row, column);
                        if(play == 1)
                            gridView.setButtonText(row, column, "X");
                        else if(play == 2)
                            gridView.setButtonText(row, column, "O");
                        if(game.isGameOver()){
                            gridView.setBackgroundColor(Color.RED);
                            gridView.setEnabled(false);
                            gridView.setStatusText(game.result());
                            showNewGameDialog();                // offer to play again
                        }
                    }
                }
            }
        }


        private class PlayDialog implements DialogInterface.OnClickListener{
            public void onClick(DialogInterface dialog, int id){
                if(id == -1){
                    game.resetGame();
                    gridView.setEnabled(true);
                    gridView.resetButtons();
                    gridView.setBackgroundColor(Color.GREEN);
                    gridView.setStatusText(game.result());
                }
                else if(id == -2)
                    MainActivity.this.finish();
            }
        }

    }
