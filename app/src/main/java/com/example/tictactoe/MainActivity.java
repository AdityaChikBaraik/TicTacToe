package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean GameActive = true;

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //State Meanings
    //0-X
    //1-O
    //2-Null

    int [][] WinPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int TappedImage = Integer.parseInt(img.getTag().toString());

        if(!GameActive)
        {
            GameReset(view);
        }


        if(gameState[TappedImage] == 2) {
            gameState[TappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn");
            }
            else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }






        // Check if any player has won.

        for(int[] WinPosition:WinPositions){
             if(gameState[WinPosition[0]] == gameState[WinPosition[1]] &&
                    gameState[WinPosition[1]] == gameState[WinPosition[2]] &&
                    gameState[WinPosition[0]] != 2 )
             {

                //Someone Won the match

                 GameActive=false;
                 String Winner;
                if(gameState[WinPosition[0]] == 0)
                {
                    Winner="X is the Winner";
                }
                else
                {
                    Winner="O is the Winner";
                }

                    TextView status = findViewById(R.id.status);
                    status.setText(Winner);
            }
        }

        // Restart Button


        Button restart = findViewById(R.id.restart_button);

        restart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){

                GameReset(view);
            }
        });

    }




    public void GameReset(View view)
    {
        GameActive = true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

        //-------------

        TextView status = findViewById(R.id.status);
        status.setText("Tap To Play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}