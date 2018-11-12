package com.example.mengl03.tictactoegame;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;



public class ButtonGridAndTextView extends GridLayout {

    private int side; // create a variable for the number of sides ( length of 3)
    private Button[][] buttons; // create 2 dimensional array to use for each row/column of buttons, 0 1 2 in each direction
    private TextView status; // create a TextView object for the current status of the game

    public ButtonGridAndTextView (Context context, int width, int newSide, OnClickListener listener) {
        super( context ); // what does context do?
        side = newSide;
        // set # of rows and columns for this GridLayout

        setColumnCount(side); // sets up the column and row counts to receive the 2D array (with side length of 3)
        setRowCount(side + 1);

        buttons = new Button[side][side];
        for(int row = 0; row < side; row++)
        {
            for(int col = 0; col < side; col++)
            {
                buttons[row][col] = new Button(context);
                buttons[row][col].setTextSize((int)(width * .2));
                buttons[row][col].setOnClickListener(listener);
                addView(buttons[row][col], width, width);
            }
        }

        status = new TextView(context); // create an object that will contain the game status
        Spec rowSpec = GridLayout.spec(side, 1);    //

        Spec columnSpec = GridLayout.spec(0, side);
        LayoutParams lpStatus = new LayoutParams(rowSpec, columnSpec);
        status.setLayoutParams(lpStatus);

        status.setWidth(side * width);
        status.setHeight(width);
        status.setGravity(Gravity.CENTER); // a views involved with the status object will be oriented towards the center
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int)(width * .15));

        addView(status);

    }

    public void setStatusText (String text)
    {
        status.setText(text);
    }

    public void setBackgroundColor(int color)
    {
        status.setBackgroundColor(color);
    }

    public void setButtonText(int row, int column, String text)
    {
        buttons[row][column].setText(text);
    }

    public boolean isButton(Button b, int row, int column)
    {
        return(b == buttons[row][column]);
    }

    public void resetButtons(){
        for(int row = 0; row < side; row++)
            for(int col = 0; col < side; col++)
                buttons[row][col].setEnabled(isEnabled());
    }
}
