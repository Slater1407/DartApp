package com.example.robin.dartapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChooseMode extends AppCompatActivity{

    static String mode = null;
    static String extra1 = null;
    static String extra2 = null;
    static boolean btnWeiterAllowed = false;
    static boolean btnExtra1Allowed = false;
    static boolean btnExtra2Allowed = false;
    static boolean btnExtra3Allowed = false;
    static boolean btn301Allowed = true;
    static boolean btn501Allowed = true;
    static boolean btnCricketAllowed = true;
    static boolean btnExtra1Clicked = false;
    static boolean btnExtra2Clicked = false;
    static boolean btnExtra3Clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int x = displayMetrics.widthPixels;
        final int y = displayMetrics.heightPixels;

        RelativeLayout mLayout = new RelativeLayout(this);
        mLayout.setBackgroundColor(getResources().getColor(R.color.black));
        final Button btn301 = new Button(this);
        final Button btn501 = new Button(this);
        final Button btnCricket = new Button(this);
        final Button btnOpt1 = new Button(this);
        final Button btnOpt2 = new Button(this);
        final Button btnOpt3 = new Button(this);
        final Button btnWeiter = new Button(this);

        final TextView intro = new TextView(this);

        intro.setText("Modus wählen:");
        intro.setTextColor(getResources().getColor(R.color.myDesignFont));
        intro.setTextSize(x/25);
        intro.setGravity(Gravity.CENTER_HORIZONTAL);
        intro.setGravity(Gravity.CENTER_VERTICAL);
        intro.setId(ViewCompat.generateViewId());
//-----------------------------------------------------------------------------------------------------------------------------
        btn301.setText("301");
        btn301.setTextColor(getResources().getColor(R.color.btnFontClickable));
        btn301.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
        //btn301.setBackground(getResources().getDrawable(R.drawable.rounded_textfield));
       // btn301.setTextColor(getResources().getColor(R.color.myDesignFont));
        btn301.setId(ViewCompat.generateViewId());
        btn301.setTextSize(x/50);
        btn301.setGravity(Gravity.LEFT);
        btn301.setGravity(Gravity.CENTER_VERTICAL);
        btn301.setGravity(Gravity.CENTER_HORIZONTAL);
//-----------------------------------------------------------------------------------------------------------------------------
        btn501.setText("501");
        btn501.setTextColor(getResources().getColor(R.color.btnFontClickable));
        btn501.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
        btn501.setId(ViewCompat.generateViewId());
        btn501.setTextSize(x/50);
        btn501.setGravity(Gravity.LEFT);
        btn501.setGravity(Gravity.CENTER_VERTICAL);
        btn501.setGravity(Gravity.CENTER_HORIZONTAL);
//------------------------------------------------------------------------------------------------------------------------------
        btnCricket.setText("Cricket");
        btnCricket.setTextColor(getResources().getColor(R.color.btnFontClickable));
        btnCricket.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
        btnCricket.setId(ViewCompat.generateViewId());
        btnCricket.setTextSize(x/50);
        btnCricket.setGravity(Gravity.LEFT);
        btnCricket.setGravity(Gravity.CENTER_VERTICAL);
        btnCricket.setGravity(Gravity.CENTER_HORIZONTAL);
//------------------------------------------------------------------------------------------------------------------------------------
        btnOpt1.setText("");
        btnOpt1.setBackgroundColor(getResources().getColor(R.color.transparent));
        btnOpt1.setTextColor(getResources().getColor(R.color.transparent));
        btnOpt1.setId(ViewCompat.generateViewId());
        btnOpt1.setTextSize(x/50);
        btnOpt1.setGravity(Gravity.LEFT);
        btnOpt1.setGravity(Gravity.CENTER_VERTICAL);
        btnOpt1.setGravity(Gravity.CENTER_HORIZONTAL);
//---------------------------------------------------------------------------------------------------------------------------------------------
        btnOpt2.setText("");
        btnOpt2.setBackgroundColor(getResources().getColor(R.color.transparent));
        btnOpt2.setTextColor(getResources().getColor(R.color.transparent));
        btnOpt2.setId(ViewCompat.generateViewId());
        btnOpt2.setTextSize(x/50);
        btnOpt2.setGravity(Gravity.LEFT);
        btnOpt2.setGravity(Gravity.CENTER_VERTICAL);
        btnOpt2.setGravity(Gravity.CENTER_HORIZONTAL);
//---------------------------------------------------------------------------------------------------------------------------------------------
        btnOpt3.setText("");
        btnOpt3.setBackgroundColor(getResources().getColor(R.color.transparent));
        btnOpt3.setTextColor(getResources().getColor(R.color.transparent));
        btnOpt3.setId(ViewCompat.generateViewId());
        btnOpt3.setTextSize(x/50);
        btnOpt3.setGravity(Gravity.LEFT);
        btnOpt3.setGravity(Gravity.CENTER_VERTICAL);
        btnOpt3.setGravity(Gravity.CENTER_HORIZONTAL);
//----------------------------------------------------------------------------------------------------------------------------------------------------
        btnWeiter.setText("WEITER");
        btnWeiter.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
        btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
        btnWeiter.setId(ViewCompat.generateViewId());
        btnWeiter.setTextSize(x/40);
        btnWeiter.setWidth(x);
        btnWeiter.setGravity(Gravity.LEFT);
        btnWeiter.setGravity(Gravity.CENTER_VERTICAL);
        btnWeiter.setGravity(Gravity.CENTER_HORIZONTAL);
//---------------------------------------------------------------------------------------------------------------------------------------------
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpBtn301 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpBtn501 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpBtnCricket = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpBtnOpt1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpBtnOpt2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpBtnOpt3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lpBtnWeiter = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        mLayout.setLayoutParams(lp);

        lpText.addRule(RelativeLayout.ALIGN_LEFT);
        lpText.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        lpText.setMargins(x/8, y/15, 0, 0);
        intro.setLayoutParams(lpText);
        mLayout.addView(intro);

        lpBtn301.addRule(RelativeLayout.ALIGN_LEFT);
        lpBtn301.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        lpBtn301.setMargins(x/8, y/3, 0, 0);
        btn301.setLayoutParams(lpBtn301);
        mLayout.addView(btn301);

        lpBtn501.addRule(RelativeLayout.ALIGN_LEFT);
        lpBtn501.addRule(RelativeLayout.BELOW, btn301.getId());
        lpBtn501.setMargins(x/8, y/15, 0, 0);
        btn501.setLayoutParams(lpBtn501);
        mLayout.addView(btn501);

        lpBtnCricket.addRule(RelativeLayout.ALIGN_LEFT);
        lpBtnCricket.addRule(RelativeLayout.BELOW, btn501.getId());
        lpBtnCricket.setMargins(x/8, y/15, 0, 0);
        btnCricket.setLayoutParams(lpBtnCricket);
        mLayout.addView(btnCricket);

        lpBtnOpt1.addRule(RelativeLayout.RIGHT_OF, btn301.getId());
        lpBtnOpt1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        lpBtnOpt1.setMargins(x/8, y/3, 0, 0);
        btnOpt1.setLayoutParams(lpBtnOpt1);
        mLayout.addView(btnOpt1);

        lpBtnOpt2.addRule(RelativeLayout.RIGHT_OF, btn301.getId());
        lpBtnOpt2.addRule(RelativeLayout.BELOW, btnOpt1.getId());
        lpBtnOpt2.setMargins(x/8, y/15, 0, 0);
        btnOpt2.setLayoutParams(lpBtnOpt2);
        mLayout.addView(btnOpt2);

        lpBtnOpt3.addRule(RelativeLayout.RIGHT_OF, btn301.getId());
        lpBtnOpt3.addRule(RelativeLayout.BELOW, btnOpt2.getId());
        lpBtnOpt3.setMargins(x/8, y/15, 0, 0);
        btnOpt3.setLayoutParams(lpBtnOpt3);
        mLayout.addView(btnOpt3);

        lpBtnWeiter.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lpBtnWeiter.setMargins(0, 0, 0, 0);
        btnWeiter.setLayoutParams(lpBtnWeiter);
        mLayout.addView(btnWeiter);

        setContentView(mLayout);

        //-------------------------------------------------------------------------------------------------------------------------



        btn301.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn301Allowed){
                    if(btn501Allowed && btnCricketAllowed) {
                        btn301.setBackgroundColor(getResources().getColor(R.color.green));
                        mode = "301";
                        btn501Allowed = false;
                        btn501.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btn501.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                        btnCricketAllowed = false;
                        btnCricket.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btnCricket.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                        btnExtra1Allowed = true;
                        btnOpt1.setText("Normal");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt1.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra2Allowed = true;
                        btnOpt2.setText("DoubleIN");
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt2.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra3Allowed = true;
                        btnOpt3.setText("DoubleOUT");
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt3.setTextColor(getResources().getColor(R.color.btnFontClickable));
                    }else{
                        btn301.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        mode = null;
                        extra1 = null;
                        extra2 = null;
                        btn501Allowed = true;
                        btn501.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btn501.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnCricketAllowed = true;
                        btnCricket.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnCricket.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra1Allowed = false;
                        btnOpt1.setText("Normal");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt1.setTextColor(getResources().getColor(R.color.transparent));
                        btnExtra2Allowed = false;
                        btnOpt2.setText("DoubleIN");
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt2.setTextColor(getResources().getColor(R.color.transparent));
                        btnExtra3Allowed = false;
                        btnOpt3.setText("DoubleOUT");
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt3.setTextColor(getResources().getColor(R.color.transparent));
                    }
                }else{

                }
            }
        });

        btn501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn501Allowed){
                    if(btn301Allowed && btnCricketAllowed) {
                        btn501.setBackgroundColor(getResources().getColor(R.color.green));
                        mode = "501";
                        btn301Allowed = false;
                        btn301.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btn301.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                        btnCricketAllowed = false;
                        btnCricket.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btnCricket.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                        btnExtra1Allowed = true;
                        btnOpt1.setText("Normal");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt1.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra2Allowed = true;
                        btnOpt2.setText("Double IN");
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt2.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra3Allowed = true;
                        btnOpt3.setText("Double OUT");
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt3.setTextColor(getResources().getColor(R.color.btnFontClickable));
                    }else{
                        btn501.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        mode = null;
                        extra1 = null;
                        extra2 = null;
                        btn301Allowed = true;
                        btn301.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btn301.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnCricketAllowed = true;
                        btnCricket.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnCricket.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra1Allowed = false;
                        btnOpt1.setText("Normal");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt1.setTextColor(getResources().getColor(R.color.transparent));
                        btnExtra2Allowed = false;
                        btnOpt2.setText("Double IN");
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt2.setTextColor(getResources().getColor(R.color.transparent));
                        btnExtra3Allowed = false;
                        btnOpt3.setText("Double OUT");
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt3.setTextColor(getResources().getColor(R.color.transparent));
                    }
                }else{

                }
            }
        });

        btnCricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnCricketAllowed){
                    if(btn501Allowed && btn301Allowed) {
                        btnCricket.setBackgroundColor(getResources().getColor(R.color.green));
                        mode = "CRICKET";
                        btn501Allowed = false;
                        btn501.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btn501.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                        btn301Allowed = false;
                        btn301.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btn301.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                        btnExtra1Allowed = true;
                        btnOpt1.setText("Normal");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt1.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra2Allowed = true;
                        btnOpt2.setText("Chance It");
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt2.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra3Allowed = true;
                        btnOpt3.setText("Cut Throat");
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt3.setTextColor(getResources().getColor(R.color.btnFontClickable));
                    }else{
                        btnCricket.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        mode = null;
                        extra1 = null;
                        extra2 = null;
                        btn501Allowed = true;
                        btn501.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btn501.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btn301Allowed = true;
                        btn301.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btn301.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        btnExtra1Allowed = false;
                        btnOpt1.setText("Normal");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt1.setTextColor(getResources().getColor(R.color.transparent));
                        btnExtra2Allowed = false;
                        btnOpt2.setText("Double IN");
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt2.setTextColor(getResources().getColor(R.color.transparent));
                        btnExtra3Allowed = false;
                        btnOpt3.setText("Double OUT");
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.transparent));
                        btnOpt3.setTextColor(getResources().getColor(R.color.transparent));
                    }
                }else{

                }
            }
        });

        btnOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnExtra1Allowed){
                    if(btn301Allowed || btn501Allowed || btnCricketAllowed){
                        if(btnExtra2Allowed && btnExtra3Allowed) {
                            btnOpt1.setBackgroundColor(getResources().getColor(R.color.green));
                            btnExtra1Clicked = true;
                            extra1 = null;
                            extra2 = null;
                            btnExtra2Allowed = false;
                           // btnOpt2.setText("Double IN");
                            btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                            btnOpt2.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                            btnExtra3Allowed = false;
                          //  btnOpt3.setText("Double OUT");
                            btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                            btnOpt3.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                        }else{
                            btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                            btnExtra1Clicked = false;
                            extra1 = null;
                            extra2 = null;
                            btnExtra2Allowed = true;
                          //  btnOpt2.setText("Double IN");
                            btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                            btnOpt2.setTextColor(getResources().getColor(R.color.btnFontClickable));
                            btnExtra3Allowed = true;
                           // btnOpt3.setText("Double OUT");
                            btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                            btnOpt3.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        }
                    }
                }
                if((!btnExtra2Clicked && !btnExtra3Clicked) &&!btnExtra1Clicked){
                    btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                    btnWeiter.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                    btnWeiterAllowed = false;
                }else{
                    btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                    btnWeiter.setTextColor(getResources().getColor(R.color.btnFontClickable));
                    btnWeiterAllowed = true;
                }
            }
        });

        btnOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnExtra2Allowed){
                    if(btn301Allowed || btn501Allowed){
                        extra1 = "DI";
                    }else{
                        extra1 = "CI";
                    }
                    if(btnExtra1Allowed) {
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.green));
                        btnExtra2Clicked = true;
                        btnExtra1Allowed = false;
                        //btnOpt1.setText("Double IN");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btnOpt1.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                    }else if(!btnExtra2Clicked) {
                        btnExtra2Clicked = true;
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.green));
                    }else{
                        btnExtra2Clicked = false;
                        btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        extra1 = null;
                        if(!btnExtra3Clicked) {
                            btnExtra1Allowed = true;
                            // btnOpt1.setText("Double IN");
                            btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                            btnOpt1.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        }
                       /* btnExtra3Allowed = true;
                        // btnOpt3.setText("Double OUT");
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt3.setTextColor(getResources().getColor(R.color.btnFontClickable));*/
                    }
                }
                if((!btnExtra2Clicked && !btnExtra3Clicked) &&!btnExtra1Clicked){
                    btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                    btnWeiter.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                    btnWeiterAllowed = false;
                }else{
                    btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                    btnWeiter.setTextColor(getResources().getColor(R.color.btnFontClickable));
                    btnWeiterAllowed = true;
                }
            }
        });

        btnOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnExtra3Allowed){
                    if(btn301Allowed || btn501Allowed){
                        extra2 = "DO";
                    }else{
                        extra2 = "CT";
                    }
                    if(btnExtra1Allowed) {
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.green));
                        btnExtra3Clicked = true;
                        btnExtra1Allowed = false;
                        //btnOpt1.setText("Double IN");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                        btnOpt1.setTextColor(getResources().getColor(R.color.btnFontUNClickable));

                    }else if(!btnExtra3Clicked) {
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.green));
                        btnExtra3Clicked = true;
                    }else{
                        btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnExtra3Clicked = false;
                        extra2 = null;
                        if(!btnExtra2Clicked){
                            btnExtra1Allowed = true;
                            //btnOpt1.setText("Double IN");
                            btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                            btnOpt1.setTextColor(getResources().getColor(R.color.btnFontClickable));
                        }
                      /*  btnExtra1Allowed = true;
                        // btnOpt1.setText("Double IN");
                        btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        btnOpt1.setTextColor(getResources().getColor(R.color.btnFontClickable));*/
                     //   btnExtra2Allowed = true;
                        // btnOpt3.setText("Double OUT");
                       // btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                        //btnOpt2.setTextColor(getResources().getColor(R.color.btnFontClickable));
                    }
                }
                if((!btnExtra2Clicked && !btnExtra3Clicked) &&!btnExtra1Clicked){
                    btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                    btnWeiter.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                    btnWeiterAllowed = false;
                }else{
                    btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
                    btnWeiter.setTextColor(getResources().getColor(R.color.btnFontClickable));
                    btnWeiterAllowed = true;
                }
            }
        });

        btnWeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnWeiterAllowed){
                    Intent intent = new Intent(ChooseMode.this, ChoosePlayers.class);
                    intent.putExtra("MODE", mode);
                    intent.putExtra("EXTRA1", extra1);
                    intent.putExtra("EXTRA2", extra2);
                    //finish();
                    startActivity(intent);
                }
            }
        });
    }

}
