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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameOverlayCricket extends AppCompatActivity {

    public static int lastScore = 0;
    static boolean resetAllowed = false;
    String exes = "";
    GameCricket game;

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
        final int[] numbers = new int[7];
        numbers[6] = 25;

        for(int i = 1; i <= countPlayers; i++){
            playerArr[i-1] = getIntent().getStringExtra("PLAYER"+i);
        }

        ArrayList<Integer> arrList = new ArrayList<>();
        int num =0;
        int dCound=0;
        Random rand = new Random();
        if(extra1 != null){
            for(int i=0; i<6; i++){
                num = rand.nextInt(20-1)+1;
                for(int j=0; j<arrList.size();j++){
                    if(arrList.get(j) == num)
                        dCound++;
                }
                if(dCound==0) {
                    arrList.add(num);
                }else{
                    i--;
                }
                dCound=0;
            }
            Collections.sort(arrList);
            Collections.reverse(arrList);
            for(int i=0; i<arrList.size();i++){
                numbers[i] = arrList.get(i);
            }
        }else{
            numbers[0]=20;
            numbers[1]=19;
            numbers[2]=18;
            numbers[3]=17;
            numbers[4]=16;
            numbers[5]=15;
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

        final int extra;
        if(extra2 != null){
            extra = GameCricket.CUT;
        }else{
            extra = GameCricket.NORMAL;
        }

        game = new GameCricket(playerArr, numbers, extra);    //Objekt des Spiels

//------------------------------------------------------------------------------------------------------------------------
//Grafische Oberflaeche einrichten

        RelativeLayout mLayout = new RelativeLayout(this);
        mLayout.setBackgroundColor(getResources().getColor(R.color.black));
        ImageView dartScheibe = new ImageView(this);

        final TextView infoPlayer1 = new TextView(this);
        final TextView infoPlayer2 = new TextView(this);
        final TextView infoPlayer3 = new TextView(this);
        final TextView infoPlayer4 = new TextView(this);
        final TextView infoPlayer5 = new TextView(this);
        final TextView infoPlayer6 = new TextView(this);
        final TextView infoPlayer7 = new TextView(this);
        final TextView infoPlayer8 = new TextView(this);
        final TextView infoNumber1 = new TextView(this);
        final TextView infoNumber2 = new TextView(this);
        final TextView infoNumber3 = new TextView(this);
        final TextView infoNumber4 = new TextView(this);
        final TextView infoNumber5 = new TextView(this);
        final TextView infoNumber6 = new TextView(this);
        final TextView infoNumber7 = new TextView(this);
        final TextView infoHit1 = new TextView(this);
        final TextView infoHit2 = new TextView(this);
        final TextView infoHit3 = new TextView(this);
        final TextView infoHit4 = new TextView(this);
        final TextView infoHit5 = new TextView(this);
        final TextView infoHit6 = new TextView(this);
        final TextView infoHit7 = new TextView(this);
        final Button btnMenu = new Button(this);
        final Button btnNeuesSpiel = new Button(this);
        final Button btnZurueck = new Button(this);


        dartScheibe.setImageResource(R.drawable.dartscheibe800x800neu);
        //  dartScheibe.setBackgroundColor(getResources().getColor(R.color.colorAccent));


        infoPlayer1.setId(ViewCompat.generateViewId());
        infoPlayer1.setTextSize(x/50);
        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.lightBlack));
        infoPlayer1.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer1.setGravity(Gravity.LEFT);
        infoPlayer1.setGravity(Gravity.CENTER_VERTICAL);


        infoPlayer2.setId(ViewCompat.generateViewId());
        infoPlayer2.setTextSize(x/50);
        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer2.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer2.setGravity(Gravity.LEFT);
        infoPlayer2.setGravity(Gravity.CENTER_VERTICAL);


        infoPlayer3.setId(ViewCompat.generateViewId());
        infoPlayer3.setTextSize(x/50);
        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer3.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer3.setGravity(Gravity.LEFT);
        infoPlayer3.setGravity(Gravity.CENTER_VERTICAL);


        infoPlayer4.setId(ViewCompat.generateViewId());
        infoPlayer4.setTextSize(x/50);
        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer4.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer4.setGravity(Gravity.LEFT);
        infoPlayer4.setGravity(Gravity.CENTER_VERTICAL);


        infoPlayer5.setId(ViewCompat.generateViewId());
        infoPlayer5.setTextSize(x/50);
        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer5.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer5.setGravity(Gravity.LEFT);
        infoPlayer5.setGravity(Gravity.CENTER_VERTICAL);


        infoPlayer6.setId(ViewCompat.generateViewId());
        infoPlayer6.setTextSize(x/50);
        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer6.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer6.setGravity(Gravity.LEFT);
        infoPlayer6.setGravity(Gravity.CENTER_VERTICAL);


        infoPlayer7.setId(ViewCompat.generateViewId());
        infoPlayer7.setTextSize(x/50);
        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer7.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer7.setGravity(Gravity.LEFT);
        infoPlayer7.setGravity(Gravity.CENTER_VERTICAL);


        infoPlayer8.setId(ViewCompat.generateViewId());
        infoPlayer8.setTextSize(x/50);
        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoPlayer8.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoPlayer8.setGravity(Gravity.LEFT);
        infoPlayer8.setGravity(Gravity.CENTER_VERTICAL);

        switch(countPlayers){
            case 8:
                infoPlayer8.setText(playerArr[7] + ": " + game.getScore(7));
            case 7:
                infoPlayer7.setText(playerArr[6] + ": " + game.getScore(6));
            case 6:
                infoPlayer6.setText(playerArr[5] + ": " + game.getScore(5));
            case 5:
                infoPlayer5.setText(playerArr[4] + ": " + game.getScore(4));
            case 4:
                infoPlayer4.setText(playerArr[3] + ": " + game.getScore(3));
            case 3:
                infoPlayer3.setText(playerArr[2] + ": " + game.getScore(2));
            case 2:
                infoPlayer2.setText(playerArr[1] + ": " + game.getScore(1));
            case 1:
                infoPlayer1.setText(playerArr[0] + ": " + game.getScore(0));
            default:

        }

        infoNumber1.setText(numbers[0] + "\n");
        infoNumber1.setId(ViewCompat.generateViewId());
        infoNumber1.setTextSize(x/40);
        infoNumber1.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoNumber1.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoNumber1.setGravity(Gravity.CENTER);
        infoNumber1.setGravity(Gravity.CENTER_VERTICAL);

        infoNumber2.setText(numbers[1] + "\n");
        infoNumber2.setId(ViewCompat.generateViewId());
        infoNumber2.setTextSize(x/40);
        infoNumber2.setBackgroundColor(getResources().getColor(R.color.lightBlack));
        infoNumber2.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoNumber2.setGravity(Gravity.CENTER);
        infoNumber2.setGravity(Gravity.CENTER_VERTICAL);

        infoNumber3.setText(numbers[2] + "\n");
        infoNumber3.setId(ViewCompat.generateViewId());
        infoNumber3.setTextSize(x/40);
        infoNumber3.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoNumber3.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoNumber3.setGravity(Gravity.CENTER);
        infoNumber3.setGravity(Gravity.CENTER_VERTICAL);

        infoNumber4.setText(numbers[3] + "\n");
        infoNumber4.setId(ViewCompat.generateViewId());
        infoNumber4.setTextSize(x/40);
        infoNumber4.setBackgroundColor(getResources().getColor(R.color.lightBlack));
        infoNumber4.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoNumber4.setGravity(Gravity.CENTER);
        infoNumber4.setGravity(Gravity.CENTER_VERTICAL);

        infoNumber5.setText(numbers[4] + "\n");
        infoNumber5.setId(ViewCompat.generateViewId());
        infoNumber5.setTextSize(x/40);
        infoNumber5.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoNumber5.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoNumber5.setGravity(Gravity.CENTER);
        infoNumber5.setGravity(Gravity.CENTER_VERTICAL);

        infoNumber6.setText(numbers[5] + "\n");
        infoNumber6.setId(ViewCompat.generateViewId());
        infoNumber6.setTextSize(x/40);
        infoNumber6.setBackgroundColor(getResources().getColor(R.color.lightBlack));
        infoNumber6.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoNumber6.setGravity(Gravity.CENTER);
        infoNumber6.setGravity(Gravity.CENTER_VERTICAL);

        infoNumber7.setText("B" + "\n");
        infoNumber7.setId(ViewCompat.generateViewId());
        infoNumber7.setTextSize(x/40);
        infoNumber7.setBackgroundColor(getResources().getColor(R.color.transparent));
        infoNumber7.setTextColor(getResources().getColor(R.color.myDesignFont));
        infoNumber7.setGravity(Gravity.CENTER);
        infoNumber7.setGravity(Gravity.CENTER_VERTICAL);



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
        RelativeLayout.LayoutParams lpPlayer2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpPlayer8 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNummer1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNummer2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNummer3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNummer4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNummer5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNummer6 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNummer7 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpDart = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpMenu = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpNeuesSpiel = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpZurueck = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


        mLayout.setLayoutParams(lp);

        lpPlayer1.addRule(RelativeLayout.ALIGN_LEFT);
        lpPlayer1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        infoPlayer1.setLayoutParams(lpPlayer1);
        mLayout.addView(infoPlayer1);

        lpPlayer2.addRule(RelativeLayout.ALIGN_LEFT);
        lpPlayer2.addRule(RelativeLayout.BELOW, infoPlayer1.getId());
        infoPlayer2.setLayoutParams(lpPlayer2);
        mLayout.addView(infoPlayer2);

        lpPlayer3.addRule(RelativeLayout.ALIGN_LEFT);
        lpPlayer3.addRule(RelativeLayout.BELOW, infoPlayer2.getId());
        infoPlayer3.setLayoutParams(lpPlayer3);
        mLayout.addView(infoPlayer3);

        lpPlayer4.addRule(RelativeLayout.ALIGN_LEFT);
        lpPlayer4.addRule(RelativeLayout.BELOW, infoPlayer3.getId());
        infoPlayer4.setLayoutParams(lpPlayer4);
        mLayout.addView(infoPlayer4);

        lpPlayer5.addRule(RelativeLayout.RIGHT_OF, infoPlayer1.getId());
        lpPlayer5.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        infoPlayer5.setLayoutParams(lpPlayer5);
        mLayout.addView(infoPlayer5);

        lpPlayer6.addRule(RelativeLayout.RIGHT_OF, infoPlayer2.getId());
        lpPlayer6.addRule(RelativeLayout.BELOW, infoPlayer5.getId());
        infoPlayer6.setLayoutParams(lpPlayer6);
        mLayout.addView(infoPlayer6);

        lpPlayer7.addRule(RelativeLayout.RIGHT_OF, infoPlayer3.getId());
        lpPlayer7.addRule(RelativeLayout.BELOW, infoPlayer6.getId());
        infoPlayer7.setLayoutParams(lpPlayer7);
        mLayout.addView(infoPlayer7);

        lpPlayer8.addRule(RelativeLayout.RIGHT_OF, infoPlayer4.getId());
        lpPlayer8.addRule(RelativeLayout.BELOW, infoPlayer7.getId());
        infoPlayer8.setLayoutParams(lpPlayer8);
        mLayout.addView(infoPlayer8);

        lpNummer1.addRule(RelativeLayout.ALIGN_LEFT);
        lpNummer1.addRule(RelativeLayout.BELOW, infoPlayer4.getId());
        infoNumber1.setLayoutParams(lpNummer1);
        mLayout.addView(infoNumber1);

        lpNummer2.addRule(RelativeLayout.BELOW, infoPlayer4.getId());
        lpNummer2.addRule(RelativeLayout.RIGHT_OF, infoNumber1.getId());
        infoNumber2.setLayoutParams(lpNummer2);
        mLayout.addView(infoNumber2);

        lpNummer3.addRule(RelativeLayout.BELOW, infoPlayer4.getId());
        lpNummer3.addRule(RelativeLayout.RIGHT_OF, infoNumber2.getId());
        infoNumber3.setLayoutParams(lpNummer3);
        mLayout.addView(infoNumber3);

        lpNummer4.addRule(RelativeLayout.BELOW, infoPlayer4.getId());
        lpNummer4.addRule(RelativeLayout.RIGHT_OF, infoNumber3.getId());
        infoNumber4.setLayoutParams(lpNummer4);
        mLayout.addView(infoNumber4);

        lpNummer5.addRule(RelativeLayout.BELOW, infoPlayer4.getId());
        lpNummer5.addRule(RelativeLayout.RIGHT_OF, infoNumber4.getId());
        infoNumber5.setLayoutParams(lpNummer5);
        mLayout.addView(infoNumber5);

        lpNummer6.addRule(RelativeLayout.BELOW, infoPlayer4.getId());
        lpNummer6.addRule(RelativeLayout.RIGHT_OF, infoNumber5.getId());
        infoNumber6.setLayoutParams(lpNummer6);
        mLayout.addView(infoNumber6);

        lpNummer7.addRule(RelativeLayout.BELOW, infoPlayer4.getId());
        lpNummer7.addRule(RelativeLayout.RIGHT_OF, infoNumber6.getId());
        infoNumber7.setLayoutParams(lpNummer7);
        mLayout.addView(infoNumber7);

        lpMenu.addRule(RelativeLayout.ALIGN_LEFT);
        lpMenu.addRule(RelativeLayout.BELOW, infoNumber1.getId());
        btnMenu.setLayoutParams(lpMenu);
        mLayout.addView(btnMenu);

        lpNeuesSpiel.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lpNeuesSpiel.addRule(RelativeLayout.BELOW, infoNumber7.getId());
        btnNeuesSpiel.setLayoutParams(lpNeuesSpiel);
        mLayout.addView(btnNeuesSpiel);

        lpZurueck.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpZurueck.addRule(RelativeLayout.BELOW, infoNumber4.getId());
        btnZurueck.setLayoutParams(lpZurueck);
        mLayout.addView(btnZurueck);

        lpDart.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lpDart.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dartScheibe.setLayoutParams(lpDart);
        mLayout.addView(dartScheibe);

        setContentView(mLayout);

        int infoPlayerX = x/2;
        int infoPlayerY = y/16;
        int infoNummerX = x/7;
        int infoNummerY = y/8;

        dartScheibe.getLayoutParams().width = x;
        dartScheibe.getLayoutParams().height = x;
        infoPlayer1.getLayoutParams().width = infoPlayerX;
        infoPlayer1.getLayoutParams().height = infoPlayerY;
        infoPlayer2.getLayoutParams().width = infoPlayerX;
        infoPlayer2.getLayoutParams().height = infoPlayerY;
        infoPlayer3.getLayoutParams().width = infoPlayerX;
        infoPlayer3.getLayoutParams().height = infoPlayerY;
        infoPlayer4.getLayoutParams().width = infoPlayerX;
        infoPlayer4.getLayoutParams().height = infoPlayerY;
        infoPlayer5.getLayoutParams().width = infoPlayerX;
        infoPlayer5.getLayoutParams().height = infoPlayerY;
        infoPlayer6.getLayoutParams().width = infoPlayerX;
        infoPlayer6.getLayoutParams().height = infoPlayerY;
        infoPlayer7.getLayoutParams().width = infoPlayerX;
        infoPlayer7.getLayoutParams().height = infoPlayerY;
        infoPlayer8.getLayoutParams().width = infoPlayerX;
        infoPlayer8.getLayoutParams().height = infoPlayerY;

        infoNumber1.getLayoutParams().width = infoNummerX;
        infoNumber1.getLayoutParams().height = infoNummerY;
        infoNumber2.getLayoutParams().width = infoNummerX;
        infoNumber2.getLayoutParams().height = infoNummerY;
        infoNumber3.getLayoutParams().width = infoNummerX;
        infoNumber3.getLayoutParams().height = infoNummerY;
        infoNumber4.getLayoutParams().width = infoNummerX;
        infoNumber4.getLayoutParams().height = infoNummerY;
        infoNumber5.getLayoutParams().width = infoNummerX;
        infoNumber5.getLayoutParams().height = infoNummerY;
        infoNumber6.getLayoutParams().width = infoNummerX;
        infoNumber6.getLayoutParams().height = infoNummerY;
        infoNumber7.getLayoutParams().width = infoNummerX;
        infoNumber7.getLayoutParams().height = infoNummerY;

//------------------------------------------------------------------------------------------------------------------------------------------


        dartScheibe.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent mEvent)
            {
                Integer xCoord = (int)mEvent.getX();
                Integer yCoord = (int)mEvent.getY();

                posCalc.calcAll(xCoord, yCoord); //Position berechnen
                Integer result = posCalc.getResult(); //geworfene Zahl
                lastScore = result;
                Integer state = posCalc.getState(); //double triple oder normal

                if(game.getWinner()==99) {
                    game.hit(result, state);

                    if (game.getWinner() != 99) {
                        //infoPlayer.setBackgroundColor(getResources().getColor(R.color.green));
                        Toast win = Toast.makeText(getApplicationContext(), "Der Gewinner ist: " + playerArr[game.getWinner()], Toast.LENGTH_SHORT);
                        win.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        win.show();
                        // infoPlayer.setText(playerArr[game.getWinner()]);
                    }
                }

                switch(countPlayers){
                    case 8:
                        infoPlayer8.setText(playerArr[7] + ": " + game.getScore(7));
                    case 7:
                        infoPlayer7.setText(playerArr[6] + ": " + game.getScore(6));
                    case 6:
                        infoPlayer6.setText(playerArr[5] + ": " + game.getScore(5));
                    case 5:
                        infoPlayer5.setText(playerArr[4] + ": " + game.getScore(4));
                    case 4:
                        infoPlayer4.setText(playerArr[3] + ": " + game.getScore(3));
                    case 3:
                        infoPlayer3.setText(playerArr[2] + ": " + game.getScore(2));
                    case 2:
                        infoPlayer2.setText(playerArr[1] + ": " + game.getScore(1));
                    case 1:
                        infoPlayer1.setText(playerArr[0] + ": " + game.getScore(0));
                    default:

                }

                if(game.getAllNumbers(game.getWhatPlayer())[0] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[0] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[0] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[0] == 0)
                    exes = "";
                infoNumber1.setText(numbers[0] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 0)
                    exes = "";
                infoNumber2.setText(numbers[1] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 0)
                    exes = "";
                infoNumber3.setText(numbers[2] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 0)
                    exes = "";
                infoNumber4.setText(numbers[3] +"\n" + exes);

                if(game.getAllNumbers(game.getWhatPlayer())[4] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[4] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[4] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[4] == 0)
                    exes = "";
                infoNumber5.setText(numbers[4] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 0)
                    exes = "";
                infoNumber6.setText(numbers[5] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 0)
                    exes = "";
                infoNumber7.setText("B"+"\n" + exes);

                switch (game.getWhatPlayer()){
                    case 7:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        break;

                    case 6:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        break;

                    case 5:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        break;

                    case 4:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        break;

                    case 3:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        break;

                    case 2:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        break;

                    case 1:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        break;

                    case 0:
                        infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        infoPlayer1.setBackgroundColor(getResources().getColor(R.color.lightBlack));
                        break;

                    default:
                }


                return false;
            }

        });

        btnNeuesSpiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game = new GameCricket(playerArr, numbers, extra);
                switch(countPlayers){
                    case 8:
                        infoPlayer8.setText(playerArr[7] + ": " + game.getScore(7));
                    case 7:
                        infoPlayer7.setText(playerArr[6] + ": " + game.getScore(6));
                    case 6:
                        infoPlayer6.setText(playerArr[5] + ": " + game.getScore(5));
                    case 5:
                        infoPlayer5.setText(playerArr[4] + ": " + game.getScore(4));
                    case 4:
                        infoPlayer4.setText(playerArr[3] + ": " + game.getScore(3));
                    case 3:
                        infoPlayer3.setText(playerArr[2] + ": " + game.getScore(2));
                    case 2:
                        infoPlayer2.setText(playerArr[1] + ": " + game.getScore(1));
                    case 1:
                        infoPlayer1.setText(playerArr[0] + ": " + game.getScore(0));
                    default:
                }

                if(game.getAllNumbers(game.getWhatPlayer())[0] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[0] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[0] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[0] == 0)
                    exes = "";
                infoNumber1.setText(numbers[0] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[1] == 0)
                    exes = "";
                infoNumber2.setText(numbers[1] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[2] == 0)
                    exes = "";
                infoNumber3.setText(numbers[2] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[3] == 0)
                    exes = "";
                infoNumber4.setText(numbers[3] +"\n" + exes);

                if(game.getAllNumbers(game.getWhatPlayer())[4] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[4] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[4] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[4] == 0)
                    exes = "";
                infoNumber5.setText(numbers[4] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[5] == 0)
                    exes = "";
                infoNumber6.setText(numbers[5] +"\n" + exes);
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 3)
                    exes = "XXX";
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 2)
                    exes = "XX";
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 1)
                    exes = "X";
                if(game.getAllNumbers(game.getWhatPlayer())[6] == 0)
                    exes = "";
                infoNumber7.setText("B" +"\n" + exes);

                infoPlayer8.setBackgroundColor(getResources().getColor(R.color.transparent));
                infoPlayer7.setBackgroundColor(getResources().getColor(R.color.transparent));
                infoPlayer6.setBackgroundColor(getResources().getColor(R.color.transparent));
                infoPlayer5.setBackgroundColor(getResources().getColor(R.color.transparent));
                infoPlayer4.setBackgroundColor(getResources().getColor(R.color.transparent));
                infoPlayer3.setBackgroundColor(getResources().getColor(R.color.transparent));
                infoPlayer2.setBackgroundColor(getResources().getColor(R.color.transparent));
                infoPlayer1.setBackgroundColor(getResources().getColor(R.color.lightBlack));
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverlayCricket.this, ChoosePlayers.class);
                intent.putExtra("EXTRA1", extra1);
                intent.putExtra("EXTRA2", extra2);
                intent.putExtra("MODE", mode);

                finish();
                startActivity(intent);
            }
        });

       /* btnZurueck.setOnClickListener(new View.OnClickListener(){

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
        });*/
    }

}
