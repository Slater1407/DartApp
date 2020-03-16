package com.example.robin.dartapp;

public class GameCricket {

    public final static int NORMAL = 1;
    public final static int CUT = 2;

    private int arrow;
    private int whatPlayer;
    private int mode;
    private int tempScore;
    private int winner;
    private int countPlayers;
    private int[] numbers;

    private PlayerCricket[] player;

    public GameCricket(String[] players, int[] numbers, int mode){
        player = new PlayerCricket[players.length];
        this.mode = mode;
        for(int i = 0; i < players.length; i++){
            player[i] = new PlayerCricket(players[i]);
        }
        countPlayers = player.length;
        whatPlayer = 0;
        arrow = 1;
        winner = 99;
        tempScore = 0;
        this.numbers = numbers;
    }

    public void hit(int score, int state){
        int number;
        if(state!=0) {
            number = score / state;
        }else{
            number = score;
        }
        for(int i=0; i<7; i++){
            if(number == numbers[i]){ //ist geworfene nummer dabei?
                for(int k = 0; k<state; k++) { //so oft ausführen wie die nummer geworfen wurde
                    if (!player[whatPlayer].addNumber(i)) { //nummer schon zu?
                        if (mode == GameCricket.CUT) { //CUT THROAGHT MODUS?
                            for(int count=0; count<countPlayers; count++) {

                                if (player[count].getNumber(i) < 3) {
                                    player[count].addScore(number);
                                }

                            }
                        } else {
                            int cPlayer=0;
                            for(int j=0; j<countPlayers; j++){
                                if(player[j].getNumber(i) == 3)
                                    cPlayer++;
                            }

                            if(cPlayer!=countPlayers){
                                player[whatPlayer].addScore(number);
                            }
                        }
                    }
                }
            }
        }
        int winCounter=0;
        for(int i=0; i<7; i++){//prüfen ob alle zahlen zu sind
            if(player[whatPlayer].getNumber(i) == 3){
                winCounter++;
            }
        }
        if(mode == GameCricket.NORMAL) {
            for (int i = 0; i < countPlayers; i++) {//prüfen ob man die meisten punkte hat
                if (i != whatPlayer) {
                    if (player[whatPlayer].getScore() < player[i].getScore()) {
                        winCounter--;
                    }
                }
            }
        }else{
            for (int i = 0; i < countPlayers; i++) {//prüfen ob man die wenigsten punkte hat
                if (i != whatPlayer) {
                    if (player[whatPlayer].getScore() > player[i].getScore()) {
                        winCounter--;
                    }
                }
            }
        }

        if(winCounter==7){
            winner = whatPlayer;
            return;
        }
        if(arrow==3){
            arrow = 1;
            if(whatPlayer==countPlayers-1){
                whatPlayer=0;
            }else{
                whatPlayer++;
            }
        }else{
            arrow++;
        }
    }

    public int[] getAllNumbers(int player){
        return this.player[player].getAllNumbers();
    }

    public int getScore(int player){
        return this.player[player].getScore();
    }
    public int getWinner(){
        return winner;
    }
    public int getWhatPlayer(){
        return whatPlayer;
    }
}
