package com.example.robin.dartapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

    static int score = 0;
    static int extra = 0;
    static boolean b1Clicked = false;
    static boolean b2Clicked = false;
    static boolean b3Clicked = false;

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
//------------------------------------------------------------------------------------------------------------------------------------
        btnOpt1.setText("NORMAL");
        btnOpt1.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
        btnOpt1.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
        btnOpt1.setId(ViewCompat.generateViewId());
        btnOpt1.setTextSize(x/50);
        btnOpt1.setGravity(Gravity.LEFT);
        btnOpt1.setGravity(Gravity.CENTER_VERTICAL);
        btnOpt1.setGravity(Gravity.CENTER_HORIZONTAL);
        btnOpt1.setEnabled(false);
//---------------------------------------------------------------------------------------------------------------------------------------------
        btnOpt2.setText("DOUBLE IN");
        btnOpt2.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
        btnOpt2.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
        btnOpt2.setId(ViewCompat.generateViewId());
        btnOpt2.setTextSize(x/50);
        btnOpt2.setGravity(Gravity.LEFT);
        btnOpt2.setGravity(Gravity.CENTER_VERTICAL);
        btnOpt2.setGravity(Gravity.CENTER_HORIZONTAL);
        btnOpt2.setEnabled(false);
//---------------------------------------------------------------------------------------------------------------------------------------------
        btnOpt3.setText("DOUBLE OUT");
        btnOpt3.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
        btnOpt3.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
        btnOpt3.setId(ViewCompat.generateViewId());
        btnOpt3.setTextSize(x/50);
        btnOpt3.setGravity(Gravity.LEFT);
        btnOpt3.setGravity(Gravity.CENTER_VERTICAL);
        btnOpt3.setGravity(Gravity.CENTER_HORIZONTAL);
        btnOpt3.setEnabled(false);
//----------------------------------------------------------------------------------------------------------------------------------------------------
        btnWeiter.setText("WEITER");
        btnWeiter.setTextColor(getResources().getColor(R.color.btnFontClickable));
        btnWeiter.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
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
                btn301.setBackgroundColor(getResources().getColor(R.color.green));
                btn301.setTextColor(getResources().getColor(R.color.btnFontClickable));
                btn301.setEnabled(false);
                score = 301;
                btn501.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                btn501.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                btn501.setEnabled(true);
                setBtnClickable(btnOpt1);
                setBtnClickable(btnOpt2);
                setBtnClickable(btnOpt3);
            }
        });

        btn501.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn501.setBackgroundColor(getResources().getColor(R.color.green));
                btn501.setTextColor(getResources().getColor(R.color.btnFontClickable));
                btn501.setEnabled(false);
                score = 501;
                btn301.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
                btn301.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
                btn301.setEnabled(true);
                setBtnClickable(btnOpt1);
                setBtnClickable(btnOpt2);
                setBtnClickable(btnOpt3);
            }
        });

        btnOpt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b1Clicked){
                    b1Clicked = false;
                    setBtnClickable(btnOpt1);
                }else {
                    setBtnClickable(btnOpt1);
                    b1Clicked = true;
                    btnOpt1.setBackgroundColor(getResources().getColor(R.color.green));
                }
                setBtnClickable(btnOpt2);
                setBtnClickable(btnOpt3);
                b2Clicked = false;
                b3Clicked = false;
            }
        });

        btnOpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b2Clicked){
                    b2Clicked = false;
                    setBtnClickable(btnOpt2);
                }else {
                    setBtnClickable(btnOpt2);
                    b2Clicked = true;
                    btnOpt2.setBackgroundColor(getResources().getColor(R.color.green));
                    setBtnClickable(btnOpt1);
                    b1Clicked = false;
                }
            }
        });

        btnOpt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b3Clicked){
                    b3Clicked = false;
                    setBtnClickable(btnOpt3);
                }else {
                    setBtnClickable(btnOpt3);
                    b3Clicked = true;
                    btnOpt3.setBackgroundColor(getResources().getColor(R.color.green));
                    setBtnClickable(btnOpt1);
                    b1Clicked = false;
                }
            }
        });

        btnWeiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b1Clicked || b2Clicked || b3Clicked) {
                    if(b1Clicked){
                        extra = GameCasual.NORMAL;
                    }else if(b2Clicked && !b3Clicked){
                        extra = GameCasual.DOUBLE_IN;
                    }else if(!b2Clicked && b3Clicked){
                        extra = GameCasual.DOUBLE_OUT;
                    }else{
                        extra = GameCasual.DOUBLE_IN_OUT;
                    }
                    Intent intent = new Intent(ChooseMode.this, ChoosePlayers.class);
                    intent.putExtra("SCORE", score);
                    intent.putExtra("EXTRA", extra);
                    //finish();
                    startActivity(intent);
                }
            }
        });
    }

    void setBtnClickable(Button btn){
        btn.setClickable(true);
        btn.setEnabled(true);
        btn.setBackgroundColor(getResources().getColor(R.color.btnBackgroundClickable));
        btn.setTextColor(getResources().getColor(R.color.btnFontClickable));
    }
    void setBtnUnClickable(Button btn){
        //btn.setClickable(false);
        btn.setBackgroundColor(getResources().getColor(R.color.btnBackgroundUNClickable));
        btn.setTextColor(getResources().getColor(R.color.btnFontUNClickable));
    }

}
