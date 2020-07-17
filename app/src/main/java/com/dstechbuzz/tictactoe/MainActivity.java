package com.dstechbuzz.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    boolean gameActive = true;
    int activePlayer = 0;
    int [] gameState = {2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2};
    int [] [] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    public void playerTap(View view)
    {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive)
        {
            gameReset(view);
        }
        if (gameState[tappedImage]==2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.ttt_x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            } else {
                img.setImageResource(R.drawable.ttt_o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for (int [] winPosition: winPositions)
        {
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] !=2)
            {
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0)
                {
                    winnerStr = "X has won";

                    player = MediaPlayer.create(this, R.raw.won);
                    player.start();
                    player.setVolume(100,100);
                    ShowAlert("Player 1 is winner");
                }
                else{
                    winnerStr = "O has won";

                    player = MediaPlayer.create(this, R.raw.won);
                    player.start();
                    player.setVolume(100,100);
                    ShowAlert("Player 2 is winner");
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }

        }

    }
    void ShowAlert(String Title){
        AlertDialog.Builder b = new AlertDialog.Builder(this, R.style.TransparentDialog);
        b.setTitle(Title)
                .setMessage("Start a new game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ResetGame();
                    }
                })
                .setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void ResetGame() {
        gameActive=true;
        activePlayer = 0;
        for (int i=0; i<gameState.length; i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }

    public void gameReset(View view)
    {
        gameActive=true;
        activePlayer = 0;
        for (int i=0; i<gameState.length; i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolBar_reg);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("TicTacToe");


        player = MediaPlayer.create(this, R.raw.bgmusic);
        player.setLooping(true);
        player.start();
        player.setVolume(50,50);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.reset:
                gameActive=true;
                activePlayer = 0;
                for (int i=0; i<gameState.length; i++)
                {
                    gameState[i] = 2;
                }
                ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
                ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        player.release();
        finish();
    }
}