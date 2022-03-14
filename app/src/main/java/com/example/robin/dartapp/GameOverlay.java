package com.example.robin.dartapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class GameOverlay extends AppCompatActivity {

    List<Throw> throwSaves;
    List<Player> players;
    Player player;
    GameCasual game;
    int whatPlayer;
    int round;
    int arrow;
    int roundPoints;
    PosCalc posCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameoverlay);

        createGame();


//-----------------------------------------------------------------------------------------------------------------------
//Bildschirmaufloesung initaliesieren und speichern
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int x = displayMetrics.widthPixels;
        final int y = displayMetrics.heightPixels;
        final int xDart = x/2;
        posCalc = new PosCalc(x, x);                              //Objekt fuer die Berechnung der beruehrten Punkte auf der Dartscheibe
//------------------------------------------------------------------------------------------------------------------------
//Grafische Oberflaeche einrichten

        RelativeLayout mLayout = new RelativeLayout(this);
        mLayout.setBackgroundColor(getResources().getColor(R.color.black));
        final ImageView dartScheibe = new ImageView(this);
        final TextView infoPlayer = new TextView(this);
        final TextView infoRoundPoints = new TextView(this);
        final ImageView pfeile = new ImageView(this);
        final TextView infoRunden = new TextView(this);
        final TextView infoGeworfen = new TextView(this);
        final Button btnMenu = new Button(this);
        final Button btnNeuesSpiel = new Button(this);
        final Button btnZurueck = new Button(this);


        dartScheibe.setImageResource(R.drawable.dartscheibe800x800neu);
      //  dartScheibe.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        infoPlayer.setText(player.getName() + ": " + player.getScore());
        infoPlayer.setId(ViewCompat.generateViewId());
        infoPlayer.setTextSize(x/50);
        infoPlayer.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer.setGravity(Gravity.LEFT);
        infoPlayer.setGravity(Gravity.CENTER_VERTICAL);

        infoRoundPoints.setText("Ges: " + roundPoints);
        infoRoundPoints.setId(ViewCompat.generateViewId());
        infoRoundPoints.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoRoundPoints.setTextSize(x/50);
        infoRoundPoints.setGravity(Gravity.LEFT);
        infoRoundPoints.setGravity(Gravity.CENTER_VERTICAL);

        pfeile.setImageResource(R.drawable.pfeiledrei);

        infoRunden.setText("Runde: " + round);
        infoRunden.setId(ViewCompat.generateViewId());
        infoRunden.setTextSize(x/50);
        infoRunden.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoRunden.setGravity(Gravity.LEFT);
        infoRunden.setGravity(Gravity.CENTER_VERTICAL);

        infoGeworfen.setText("Geworfen: ");
        infoGeworfen.setId(ViewCompat.generateViewId());
        infoGeworfen.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoGeworfen.setTextSize(x/50);
        infoGeworfen.setGravity(Gravity.LEFT);
        infoGeworfen.setGravity(Gravity.CENTER_VERTICAL);

        btnMenu.setText("Menü");
        btnMenu.setTextSize(x/70);
        btnMenu.setWidth(x/2-(x/5/2)-10);
        btnMenu.setGravity(Gravity.CENTER);

        btnNeuesSpiel.setText("Neues Spiel");
        btnNeuesSpiel.setTextSize(x/70);
        btnNeuesSpiel.setWidth(x/2-(x/5/2)-10);
        btnNeuesSpiel.setGravity(Gravity.CENTER);

        btnZurueck.setText("<-");
        btnZurueck.setTextColor(getResources().getColor(R.color.inactive));
        btnZurueck.setTextSize(x/50);
        btnZurueck.setWidth(x/5-10);
        btnZurueck.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpDart = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPfeile = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpRunden = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpGeworfen = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpMenu = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNeuesSpiel = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpZurueck = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        mLayout.setLayoutParams(lp);
        lpPlayer1.addRule(RelativeLayout.ALIGN_LEFT);
        lpPlayer1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        infoPlayer.setLayoutParams(lpPlayer1);
        mLayout.addView(infoPlayer);

        lpPfeile.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lpPfeile.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        pfeile.setLayoutParams(lpPfeile);
        mLayout.addView(pfeile);

        lpPlayer2.addRule(RelativeLayout.RIGHT_OF, infoPlayer.getId());
        lpPlayer2.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        infoRoundPoints.setLayoutParams(lpPlayer2);
        mLayout.addView(infoRoundPoints);

        lpRunden.addRule(RelativeLayout.ALIGN_LEFT);
        lpRunden.addRule(RelativeLayout.BELOW, infoPlayer.getId());
        infoRunden.setLayoutParams(lpRunden);
        mLayout.addView(infoRunden);

        lpGeworfen.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lpGeworfen.addRule(RelativeLayout.BELOW, infoRoundPoints.getId());
        infoGeworfen.setLayoutParams(lpGeworfen);
        mLayout.addView(infoGeworfen);

        lpMenu.addRule(RelativeLayout.ALIGN_LEFT);
        lpMenu.addRule(RelativeLayout.BELOW, infoRunden.getId());
        btnMenu.setLayoutParams(lpMenu);
        mLayout.addView(btnMenu);

        lpNeuesSpiel.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lpNeuesSpiel.addRule(RelativeLayout.BELOW, infoGeworfen.getId());
        btnNeuesSpiel.setLayoutParams(lpNeuesSpiel);
        mLayout.addView(btnNeuesSpiel);

        lpZurueck.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpZurueck.addRule(RelativeLayout.BELOW, infoGeworfen.getId());
        btnZurueck.setLayoutParams(lpZurueck);
        mLayout.addView(btnZurueck);

        lpDart.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpDart.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dartScheibe.setLayoutParams(lpDart);
        mLayout.addView(dartScheibe);

        setContentView(mLayout);

        dartScheibe.getLayoutParams().width = x;
        dartScheibe.getLayoutParams().height = x;
        infoPlayer.getLayoutParams().width = x/2;
        infoPlayer.getLayoutParams().height = y/10;
        infoRoundPoints.getLayoutParams().width = x/4;
        infoRoundPoints.getLayoutParams().height = y/10;
        pfeile.getLayoutParams().width = x/4;
        pfeile.getLayoutParams().height = y/10;
        infoRunden.getLayoutParams().width = x/2;
        infoRunden.getLayoutParams().height = y/10;
        infoGeworfen.getLayoutParams().width = x/2;
        infoGeworfen.getLayoutParams().height = y/10;

//-------------------------------------------------------------------------------------------------------------------
        dartScheibe.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent mEvent)
            {
                Integer xCoord = (int)mEvent.getX();
                Integer yCoord = (int)mEvent.getY();
                ThrowResult throwResult = posCalc.calcAll(xCoord, yCoord); //Position berechnen
                player = players.get(whatPlayer);
                if(!game.doThrow(player, throwResult)){
                    resetPlayer(player, arrow, throwSaves);
                    nextPlayer();
                    arrow = 1;
                    roundPoints = 0;
                    Toast busted = Toast.makeText(getApplicationContext(), "BUSTED!", Toast.LENGTH_SHORT);
                    busted.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    busted.show();
                }else {
                    if (player.getScore() == 0) {
                        infoPlayer.setBackgroundColor(getResources().getColor(R.color.green));
                        dartScheibe.setEnabled(false);

                    }
                    roundPoints+=throwResult.getSum();
                    throwSaves.add(new Throw(player, throwResult, arrow, round, roundPoints, whatPlayer));
                    if(player.getScore() != 0) {
                        nextArrow();
                    }else{
                        arrow++;
                    }
                }
                updateInfo(infoPlayer, infoGeworfen, null, infoRoundPoints, throwResult.toString(), pfeile);
                /*infoPlayer.setText(player.getName()+ ": " +player.getScore());
                infoGeworfen.setText("Geworfen: "+throwResult.getPoints());
                infoRoundPoints.setText("Ges: "+ roundPoints);*/

                if(arrow == 1) {
                    dartScheibe.setEnabled(false);
                    pfeile.setImageResource(R.drawable.pfeilenull);
                    player = players.get(whatPlayer);
                    roundPoints = 0;
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            updateInfo(infoPlayer, infoGeworfen, infoRunden, infoRoundPoints, "0", pfeile);
                          /*  infoPlayer.setText(player.getName()+ ": " +player.getScore());
                            infoGeworfen.setText("Geworfen: "+ 0);
                            infoRunden.setText("Runde: "+ round);
                            infoRoundPoints.setText("Ges: "+ roundPoints);*/
                            dartScheibe.setEnabled(true);
                        }
                    }, 1000);
                }


                return false;
            }

        });

        btnNeuesSpiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGame();
                updateInfo(infoPlayer, infoGeworfen, infoRunden, infoRoundPoints, "0", pfeile);
                infoPlayer.setBackgroundColor(getResources().getColor(R.color.transparent));
                dartScheibe.setEnabled(true);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(GameOverlay.this, ChoosePlayers.class);
                finish();
                //startActivity(intent);
            }
        });

        btnZurueck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(throwSaves.size() > 1) {
                    undoLastThrow();
                    Throw lastThrow = throwSaves.get(throwSaves.size() - 1);
                    roundPoints = lastThrow.getRoundPoints();
                    updateInfo(infoPlayer, infoGeworfen, infoRunden, infoRoundPoints, lastThrow.getThrowResult().toString(), pfeile);
                    dartScheibe.setEnabled(true);
                    infoPlayer.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });
    }
/*
Player1
10
20

 */

    void nextPlayer(){
        if(whatPlayer == players.size()-1){
            whatPlayer = 0;
            round++;
        }else{
            whatPlayer++;
        }
    }
    void nextArrow(){
        if(arrow == 3){
            arrow = 1;
            nextPlayer();
        }else{
            arrow++;
        }
    }
    void resetPlayer(Player cPlayer, int cArrow, List<Throw> listThrows){
        for(int i = cArrow; i > 1; i--) {
            Throw lastThrow = listThrows.get(listThrows.size() - 1);
            cPlayer.addScore(lastThrow.getThrowResult().getSum());
            listThrows.remove(lastThrow);
        }
    }
    void undoLastThrow(){
        Throw lastThrow = throwSaves.get(throwSaves.size()-1);
        player = lastThrow.getPlayer();
        player.addScore(lastThrow.getThrowResult().getSum());
        arrow = lastThrow.getArrow();
        round = lastThrow.getRound();
        whatPlayer = lastThrow.getWhatPlayer();
        throwSaves.remove(lastThrow);
    }
    void createGame(){
        throwSaves = new ArrayList<>();
        players = new ArrayList<>();
        whatPlayer = 0;
        round = 1;
        arrow = 1;
        roundPoints = 0;
        //-----------------------------------------------------------------------------------------------------------------------
        //Daten aus vorheriger Activity empfangen

        final int countPlayers = getIntent().getIntExtra("COUNTPLAYERS", -1);
        final int extra = getIntent().getIntExtra("EXTRA", -1);
        final int totalScore = getIntent().getIntExtra("SCORE", 0);

        //-----------------------------------------------------------------------------------------------------------------------
        //Objekte initialisieren
        for(int i = 1; i < countPlayers+1; i++){
            players.add(new Player(getIntent().getStringExtra("PLAYER"+i), totalScore));
        }

        player = players.get(0);
        game = new GameCasual(extra, totalScore);    //Objekt des Spiels
        throwSaves.add(new Throw(player, new ThrowResult(0, 1), arrow, round, roundPoints, whatPlayer));
    }
    void updateInfo(TextView infoSpieler, TextView infoGeworfen, TextView infoRunden, TextView infoRundenPunkte, String geworfen, ImageView pfeile){
        if(infoSpieler != null){
            infoSpieler.setText(player.getName() + ": " + player.getScore());
        }
        if(infoGeworfen != null){
            infoGeworfen.setText("Geworfen: "+ geworfen);
        }
        if(infoRunden != null){
            infoRunden.setText("Runde: " + round);
        }
        if(infoRundenPunkte != null){
            infoRundenPunkte.setText("Ges: " + roundPoints);
        }
        switch (arrow){
            case 1:
                pfeile.setImageResource(R.drawable.pfeiledrei);
                break;
            case 2:
                pfeile.setImageResource(R.drawable.pfeilezwei);
                break;
            case 3:
                pfeile.setImageResource(R.drawable.pfeileeins);
                break;
            case 4:
                pfeile.setImageResource(R.drawable.pfeilenull);
                break;
            default:
                break;
        }
    }

}
