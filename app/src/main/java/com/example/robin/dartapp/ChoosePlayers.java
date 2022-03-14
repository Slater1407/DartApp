package com.example.robin.dartapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoosePlayers extends AppCompatActivity {

    private Button btn_nextActivity;
    private Button btn_newPlayer;
    private Button btn_lessPlayer;
    private Button btn_back;
    private EditText et_Player;
    private int countPlayer;
    private final int maxPlayers = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int x = displayMetrics.widthPixels;
        final int y = displayMetrics.heightPixels;

        countPlayer = 0;

//----------------------------------------------------------------------------------------------------------------------------------------

        btn_nextActivity = new Button(this);
        btn_newPlayer = new Button(this);
        btn_lessPlayer = new Button(this);
        btn_back = new Button(this);
        et_Player = new EditText(this);
        RelativeLayout mLayout = new RelativeLayout(this);
        mLayout.setBackgroundColor(getResources().getColor(R.color.black));


        //et_Player.setText("");
        et_Player.setTextSize(x/50);
        et_Player.setWidth(x);
       // et_Player.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
        et_Player.setBackground(getResources().getDrawable(R.drawable.rounded_textfield));
        et_Player.setTextColor(getResources().getColor(R.color.myDesignFont));
        et_Player.setGravity(Gravity.CENTER);
        et_Player.setId(ViewCompat.generateViewId());

        btn_nextActivity.setText("SPIELEN!");
        btn_nextActivity.setGravity(Gravity.CENTER);
        btn_nextActivity.setTextSize(x/40);
        btn_nextActivity.setWidth(x);
        btn_nextActivity.setEnabled(false);
       // btn_nextActivity.setTextColor(getResources().getColor(R.color.btnFontClickable));
       // btn_nextActivity.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
        btn_nextActivity.setId(ViewCompat.generateViewId());

        btn_newPlayer.setText("+");
        btn_newPlayer.setTextSize(x/30);
        btn_newPlayer.setGravity(Gravity.CENTER);
        btn_newPlayer.setEnabled(false);
        btn_newPlayer.setBackground(getResources().getDrawable(R.drawable.round_button_green));
        btn_newPlayer.setId(ViewCompat.generateViewId());

        btn_lessPlayer.setText("-");
        btn_lessPlayer.setTextSize(x/30);
        btn_lessPlayer.setGravity(Gravity.CENTER);
        btn_lessPlayer.setEnabled(false);
        btn_lessPlayer.setBackground(getResources().getDrawable(R.drawable.round_button_red));
        btn_lessPlayer.setId(ViewCompat.generateViewId());

        btn_back.setText("MODUS");
        btn_back.setGravity(Gravity.CENTER);
        btn_back.setTextSize(x/40);
        btn_back.setWidth(x);
        btn_back.setEnabled(true);
        btn_back.setId(ViewCompat.generateViewId());

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp_et_Player = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp_btn_nextActivity = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp_btn_back = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp_btn_newPlayer = new RelativeLayout.LayoutParams(x/5, x/5);
        RelativeLayout.LayoutParams lp_btn_lessPlayer = new RelativeLayout.LayoutParams(x/5, x/5);


        mLayout.setLayoutParams(lp);

        lp_et_Player.addRule(RelativeLayout.CENTER_VERTICAL);
        lp_et_Player.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp_et_Player.setMargins(0, y/50, 0, 0);
        et_Player.setLayoutParams(lp_et_Player);
        mLayout.addView(et_Player);

        lp_btn_nextActivity.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        lp_btn_nextActivity.setMargins(0,0,0,0);
        btn_nextActivity.setLayoutParams(lp_btn_nextActivity);
        mLayout.addView(btn_nextActivity);

        lp_btn_newPlayer.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp_btn_newPlayer.addRule(RelativeLayout.BELOW, btn_nextActivity.getId());
        lp_btn_newPlayer.setMargins(0, y/45, 0,0);
        btn_newPlayer.setLayoutParams(lp_btn_newPlayer);
        mLayout.addView(btn_newPlayer);

        lp_btn_lessPlayer.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp_btn_lessPlayer.addRule(RelativeLayout.BELOW, btn_newPlayer.getId());
        lp_btn_lessPlayer.setMargins(0, y/50, 0,0);
        btn_lessPlayer.setLayoutParams(lp_btn_lessPlayer);
        mLayout.addView(btn_lessPlayer);

        lp_btn_back.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp_btn_back.setMargins(0,0,0,0);
        btn_back.setLayoutParams(lp_btn_back);
        mLayout.addView(btn_back);

        setContentView(mLayout);

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        final int score = getIntent().getIntExtra("SCORE", 0);
        final int extra = getIntent().getIntExtra("EXTRA", 0);

        final ArrayList<String> playerList = new ArrayList<String>();

        et_Player.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    btn_nextActivity.setEnabled(false);
                    btn_newPlayer.setEnabled(false);
                }else{
                    if(countPlayer < maxPlayers)
                        btn_newPlayer.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_newPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countPlayer < maxPlayers) {
                    playerList.add(et_Player.getText().toString());
                    countPlayer++;
                    et_Player.setText("");
                    btn_lessPlayer.setEnabled(true);
                    if(countPlayer >= 8){
                        btn_newPlayer.setEnabled(false);
                    }
                    btn_nextActivity.setEnabled(true);
                    Toast player_added = Toast.makeText(getApplicationContext(), "Spieler : " + playerList.get(playerList.size()-1) + " hinzugefügt.", Toast.LENGTH_SHORT);
                    player_added.setGravity(Gravity.CENTER_VERTICAL, 0, -(y /10));
                    player_added.show();
                }else{
                    Toast player_full = Toast.makeText(getApplicationContext(), "Maximale Spieleranzahl erreicht!", Toast.LENGTH_SHORT);
                    player_full.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    player_full.show();
                }
            }
        });

        btn_lessPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countPlayer>=1){
                    et_Player.setText(playerList.get(countPlayer - 1));
                    playerList.remove(countPlayer-1);
                    countPlayer--;
                    if(countPlayer<1) {
                        btn_lessPlayer.setEnabled(false);
                        btn_nextActivity.setEnabled(false);
                    }
                }
            }
        });

        btn_nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                intent = new Intent(ChoosePlayers.this, GameOverlay.class);
                intent.putExtra("SCORE", score);
                intent.putExtra("COUNTPLAYERS", countPlayer);
                intent.putExtra("EXTRA", extra);
                for(int i = 1; i<=countPlayer; i++){
                    intent.putExtra("PLAYER"+i, playerList.get(i-1));
                }

                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(ChoosePlayers.this, ChooseMode.class);
                finish();
                //startActivity(intent);
            }
        });
    }
}
