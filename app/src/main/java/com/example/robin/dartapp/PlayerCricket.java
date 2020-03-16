package com.example.robin.dartapp;

public class PlayerCricket {
    private int score;
    private String name;
    private int[] numbers;

    public PlayerCricket(String name){

        this.name = name;
        numbers = new int[7];
        for(int i=0; i<7; i++){
            numbers[i] = 0;
        }
        score = 0;
    }

    public boolean addNumber(int number){
        if(numbers[number]<3){
            numbers[number]++;
            return true;
        }else{
            return false;
        }
    }


    public int[] getAllNumbers(){
        return numbers;
    }

    public int getNumber(int i){
        return numbers[i];
    }
    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void addScore(int score){
        this.score += score;
    }
}
