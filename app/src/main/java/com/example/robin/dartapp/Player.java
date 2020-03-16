package com.example.robin.dartapp;

public class Player {

    private int score;
    private boolean doubleIn;
    private boolean doubleOut;
    private String name;

    public Player(String name, int mode, int totalScore){
        if(mode == GameThreeHundret.DOUBLE_IN){
            doubleIn = true;
        }else{
            doubleIn = false;
        }
        if(mode == GameThreeHundret.DOUBLE_OUT){
            doubleOut = true;
        }else{
            doubleOut = false;
        }
        if(mode == GameThreeHundret.DOUBLE_IN_OUT){
            doubleOut = true;
            doubleIn = true;
        }
        this.name = name;
        this.score = totalScore;
    }

    public void throwArrow(int points){
        score = score - points;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public boolean getDoubleIn(){
        return doubleIn;
    }

    public boolean getDoubleOut(){
        return doubleOut;
    }

    public void setDoubleIn(boolean doubleIn){
        this.doubleIn = doubleIn;
    }

    public void setDoubleOut(boolean doubleOut){
        this.doubleOut = doubleOut;
    }
}
