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

public class GameOverlay extends AppCompatActivity {

    public static int lastScore = 0;
    public int roundPoints = 0;
    public int roundPointsCounter = 1;
    static boolean resetAllowed = false;
    GameThreeHundret game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameoverlay);
//-----------------------------------------------------------------------------------------------------------------------
//Daten aus vorheriger Activity empfangen

        final int countPlayers = getIntent().getIntExtra("COUNTPLAYERS", -1);
        final String extra1 = getIntent().getStringExtra("EXTRA1");
        final String extra2 = getIntent().getStringExtra("EXTRA2");
        final String mode = getIntent().getStringExtra("MODE");
        final String[] playerArr = new String[countPlayers];

        for(int i = 1; i <= countPlayers; i++){
            playerArr[i-1] = getIntent().getStringExtra("PLAYER"+i);
        }


//-----------------------------------------------------------------------------------------------------------------------
//Bildschirmaufloesung initaliesieren und speichern
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int x = displayMetrics.widthPixels;
        final int y = displayMetrics.heightPixels;
        final int xDart = x/2;

//-----------------------------------------------------------------------------------------------------------------------
//Objekte initialisieren
        final PosCalc posCalc = new PosCalc(x, x);                              //Objekt fuer die Berechnung der beruehrten Punkte auf der Dartscheibe

        final int totalScore;
        if(mode.equals("301")){
            totalScore = 301;
        }else if(mode.equals("501")){
            totalScore = 501;
        }else{
            totalScore = 999;
        }
        final int extra;
        if(extra1 == null && extra2 == null){
            extra = GameThreeHundret.NORMAL;
        }else if(extra1 != null && extra2 == null){
            extra = GameThreeHundret.DOUBLE_IN;
        }else if(extra1 == null && extra2 != null){
            extra = GameThreeHundret.DOUBLE_OUT;
        }else{
            extra = GameThreeHundret.DOUBLE_IN_OUT;
        }


        game = new GameThreeHundret(playerArr, extra, totalScore);    //Objekt des Spiels

//------------------------------------------------------------------------------------------------------------------------
//Grafische Oberflaeche einrichten

        RelativeLayout mLayout = new RelativeLayout(this);
        mLayout.setBackgroundColor(getResources().getColor(R.color.black));
        ImageView dartScheibe = new ImageView(this);
        final TextView infoPlayer = new TextView(this);
        final TextView infoRoundPoints = new TextView(this);
        final TextView infoRunden = new TextView(this);
        final TextView infoGeworfen = new TextView(this);
        final Button btnMenu = new Button(this);
        final Button btnNeuesSpiel = new Button(this);
        final Button btnZurueck = new Button(this);


        dartScheibe.setImageResource(R.drawable.dartscheibe800x800neu);
      //  dartScheibe.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        infoPlayer.setText(playerArr[0] + ": " + game.getScore(0));
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

        infoRunden.setText("Runde: " + game.getRound());
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

        lpPlayer2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
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
        infoRoundPoints.getLayoutParams().width = x/2;
        infoRoundPoints.getLayoutParams().height = y/10;
        infoRunden.getLayoutParams().width = x/2;
        infoRunden.getLayoutParams().height = y/10;
        infoGeworfen.getLayoutParams().width = x/2;
        infoGeworfen.getLayoutParams().height = y/10;

//-------------------------------------------------------------------------------------------------------------------
/*
        ImageView dartScheibe = (ImageView) findViewById(R.id.dartImage);
        final TextView infoPlayer = (TextView) findViewById(R.id.p1);
        final TextView infoRoundPoints = (TextView) findViewById(R.id.p2);
        final TextView infoRunden = (TextView) findViewById(R.id.runde);
        final TextView infoGeworfen = (TextView) findViewById(R.id.geworfen);
        Button btnDelete = (Button) findViewById(R.id.delete);
        Button btnEnter = (Button) findViewById(R.id.enter);
        Button btnNeuesSpiel = (Button) findViewById(R.id.neuesSpiel);
        Button btnMenu = (Button) findViewById(R.id.menu);

        dartScheibe.getLayoutParams().width = x;
        dartScheibe.getLayoutParams().height = x;
        infoPlayer.getLayoutParams().width = x/2;
        infoPlayer.getLayoutParams().height = y/10;
        infoRoundPoints.getLayoutParams().width = x/2;
        infoRoundPoints.getLayoutParams().height = y/10;
        infoRunden.getLayoutParams().width = x/2;
        infoRunden.getLayoutParams().height = y/10;
        infoGeworfen.getLayoutParams().width = x/2;
        infoGeworfen.getLayoutParams().height = y/10;

        btnDelete.getLayoutParams().width = x/2;
        btnDelete.getLayoutParams().height = y/10;
        btnEnter.getLayoutParams().width = x/2;
        btnEnter.getLayoutParams().height = y/10;
        btnMenu.getLayoutParams().width = x/2;
        btnMenu.getLayoutParams().height = y/10;
        btnNeuesSpiel.getLayoutParams().width = x/2;
        btnNeuesSpiel.getLayoutParams().height = y/10;
*/
//-------------------------------------------------------------------------------------------------------------------
        dartScheibe.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent mEvent)
            {
                Integer xCoord = (int)mEvent.getX();
                Integer yCoord = (int)mEvent.getY();
                //infoPlayer.setText("x: " + xCoord.toString() + "   y:" + yCoord.toString());

                //Point touched = posCalc.touchCalc(xCoord, yCoord);

                //Integer result = posCalc.checkPos(touched.x, touched.y);
                //int distance = posCalc.checkDistance(xCoord, yCoord);

               /* infoPlayer.setText(result.toString());
                if(distance > xDart)
                    infoPlayer.setText("0");*/
               if(game.getWinner() == 99) {
                   posCalc.calcAll(xCoord, yCoord); //Position berechnen
                   Integer result = posCalc.getResult(); //geworfene Zahl
                   lastScore = result;
                   Integer state = posCalc.getState(); //double triple oder normal
                   boolean busted = game.hit(result, state);
                   resetAllowed = true;
                   if (busted == false) {
                       Toast bust = Toast.makeText(getApplicationContext(), "BUSTED!", Toast.LENGTH_SHORT);
                       bust.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                       bust.show();
                   }

                   if(roundPointsCounter > 3){
                       roundPoints = result;
                       roundPointsCounter = 2;
                   }else{
                       roundPoints += result;
                       roundPointsCounter++;
                   }

                   infoPlayer.setText(playerArr[game.getWhatPlayer()] + ": " + game.getScore(game.getWhatPlayer()));
                   infoRoundPoints.setText("Ges: " + roundPoints);
                   infoGeworfen.setText("Geworfen: " + result);
                   infoRunden.setText("Runde: " + game.getRound());

                   if(game.getWinner() != 99){
                       /*switch (game.getWinner()){
                           case 0:
                               Toast win = Toast.makeText(getApplicationContext(), "Der Gewinner ist: "+ p1, Toast.LENGTH_SHORT);
                               win.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                               win.show();
                               infoPlayer.setBackgroundColor(getResources().getColor(R.color.green));
                               infoRoundPoints.setBackgroundColor(getResources().getColor(R.color.red));
                               break;
                           case 1:
                               Toast win2 = Toast.makeText(getApplicationContext(), "Der Gewinner ist: "+ p2, Toast.LENGTH_SHORT);
                               win2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                               win2.show();
                               infoRoundPoints.setBackgroundColor(getResources().getColor(R.color.green));
                               infoPlayer.setBackgroundColor(getResources().getColor(R.color.red));
                               break;
                           default:
                               break;
                       }*/
                       infoPlayer.setBackgroundColor(getResources().getColor(R.color.green));
                       Toast win = Toast.makeText(getApplicationContext(), "Der Gewinner ist: "+ playerArr[game.getWinner()], Toast.LENGTH_SHORT);
                       win.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                       win.show();
                       infoPlayer.setText(playerArr[game.getWinner()]);
                   }
               }

                return false;
            }

        });

        btnNeuesSpiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = new GameThreeHundret(playerArr, extra, totalScore);
                infoPlayer.setText(playerArr[0] + ": " + game.getScore(game.getWhatPlayer()));
                roundPoints = 0;
                infoRoundPoints.setText("Ges: " + roundPoints);
                infoRunden.setText("Runde: " + game.getRound());
                infoGeworfen.setText("Geworfen: ");
                infoPlayer.setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverlay.this, ChoosePlayers.class);
                finish();
                startActivity(intent);
            }
        });

        btnZurueck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(resetAllowed == true) {
                    game.failClick(lastScore);
                    infoPlayer.setText(playerArr[game.getWhatPlayer()] + ": " + game.getScore(game.getWhatPlayer()));
                    infoPlayer.setBackgroundColor(getResources().getColor(R.color.transparent));
                    infoGeworfen.setText("Geworfen: ");
                    infoRunden.setText("Runde: " + game.getRound());
                    game.setWinner(99);
                    resetAllowed = false;
                }else{
                    Toast nA = Toast.makeText(getApplicationContext(), "Rücksetzen nicht möglich", Toast.LENGTH_LONG);
                    nA.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    nA.show();
                }
            }
        });
    }

}
