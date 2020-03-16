package com.example.robin.dartapp;

public class GameThreeHundret {
    public final static int NORMAL = 1;
    public final static int DOUBLE_OUT = 2;
    public final static int DOUBLE_IN = 3;
    public final static int DOUBLE_IN_OUT = 4;

    private int round;
    private int arrow;
    private int whatPlayer;
    private int mode;
    private int tempScore;
    private int winner;
    private int countPlayers;
    private int totalScore;

   // private Player player1;
   // private Player player2;
    private Player[] player;

    public GameThreeHundret(String[] players, int inOut, int totalScore){
        player = new Player[players.length];
        this.mode = inOut;
        for(int i = 0; i < players.length; i++){
            player[i] = new Player(players[i], inOut, totalScore);
        }
        countPlayers = player.length;
        whatPlayer = 0;
        arrow = 1;
        round = 1;
        winner = 99;
        this.totalScore = totalScore;
    }

    public GameThreeHundret(String namePlayer1, String namePlayer2, int inOut, int totalScore){

        player = new Player[2];
        player[0] = new Player(namePlayer1, inOut, totalScore);
        player[1] = new Player(namePlayer2, inOut, totalScore);
      //  player1 = new Player(namePlayer1, inOut, totalScore);
       // player2 = new Player(namePlayer2, inOut, totalScore);
        this.mode = inOut;
        whatPlayer = 0;
        arrow = 1;
        round  = 1;
        winner = 99;
        countPlayers = 2;
        this.totalScore = totalScore;
    }

    public boolean hit(int score, int state){

        boolean allowed = true;


        if(arrow == 1) {
            tempScore = player[whatPlayer].getScore();
        }
        if(player[whatPlayer].getDoubleIn() == true && state == PosCalc.DOUBLE){
            /*if((player[whatPlayer].getScore()-score) < 0){
                arrow = 4;
                allowed = false;
                player[whatPlayer].setScore(tempScore);
            }else{
                player[whatPlayer].throwArrow(score);
                arrow++;
                player[whatPlayer].setDoubleIn(false);
            }*/
            player[whatPlayer].throwArrow(score);
            arrow++;
            player[whatPlayer].setDoubleIn(false);
        }else if(player[whatPlayer].getDoubleIn() == true && state != PosCalc.DOUBLE){
            arrow++;
        }else if(player[whatPlayer].getDoubleIn() == false){
            if((player[whatPlayer].getScore()-score) < 0) {
                arrow = 4;
                allowed = false;
                player[whatPlayer].setScore(tempScore);
            }else {
                if(player[whatPlayer].getDoubleOut() == true){
                    if(player[whatPlayer].getScore()-score == 0){
                        if(state == PosCalc.DOUBLE){
                            player[whatPlayer].throwArrow(score);
                            arrow++;
                        }else{
                            arrow = 4;
                            allowed = false;
                            player[whatPlayer].setScore(tempScore);
                        }
                    }else{
                        player[whatPlayer].throwArrow(score);
                        arrow++;
                    }
                }else {
                    player[whatPlayer].throwArrow(score);
                    arrow++;
                }
            }
        }
        if (arrow > 3) {
            if (whatPlayer >= countPlayers-1) {
                whatPlayer = 0;
                round++;
            } else {
                whatPlayer++;
            }
            arrow = 1;
        }

        for(int i=0; i < player.length; i++){
            if(player[i].getScore() == 0){
                winner = i;
            }
        }

        return allowed;
    }

    public void failClick(int score){

        if(arrow == 1){
            if(whatPlayer == 0){
                whatPlayer = countPlayers-1;
                round--;
            }else{
                whatPlayer--;
            }
            if(player[whatPlayer].getScore() + score == totalScore){
                arrow = 3;
                player[whatPlayer].setDoubleIn(true);
                player[whatPlayer].setScore(player[whatPlayer].getScore() + score);
            }else if (player[whatPlayer].getScore() == totalScore){
                arrow = 3;
                //player[whatPlayer].setScore(player[whatPlayer].getScore() + score);
            }else{
                player[whatPlayer].setScore(player[whatPlayer].getScore() + score);
                arrow = 3;
            }
        }else{
            arrow--;
            player[whatPlayer].setScore(player[whatPlayer].getScore() + score);
            if(player[whatPlayer].getScore() == totalScore)
                player[whatPlayer].setDoubleIn(true);
        }
    }

    public int getRound(){
        return round;
    }

    public int getWhatPlayer(){
        return whatPlayer;
    }

    public int getScore(int player){
        return this.player[player].getScore();
    }
   /* public int getScoreP1(){
        return player1.getScore();
    }
    public int getScoreP2(){
        return player2.getScore();
    }
*/
    public int getWinner(){
        return winner;
    }

    public void setWinner(int winner){
        this.winner = winner;
    }

    public int getArrow(){
        return arrow;
    }
}
