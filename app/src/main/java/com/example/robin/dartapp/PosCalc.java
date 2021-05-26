package com.example.robin.dartapp;

import android.graphics.Point;
import android.view.View;

public class PosCalc {

    public static int FAIL = 0;
    public static int NORMAL = 1;
    public static int DOUBLE = 2;
    public static int TRIPLE = 3;

    private int x, y;
    private int radius;
    private int anK, geK, angle;
    private int index = 0;
    private int[] fieldX = new int[20];
    private int[] fieldY = new int[20];
    private final int[] numbers = {13, 4, 18, 1, 20, 5, 12, 9, 14, 11, 8, 16, 7, 19, 3, 17, 2, 15, 10, 6};
    private int[] circles = new int[6];
   // int circle1 = radius; //aeußerster Ring

    private final int maxX, maxY;
    private final int midX, midY;

    public PosCalc(int maxX, int maxY){ //Custom-Konstruktor
        this.maxX = maxX;
        this.maxY = maxY;
        radius = maxX/2;
        midX = maxX/2;
        midY = maxY/2;
        calcEdge();
        calcCircles();
    }

    public int delta(int angle, int anK){ //funktion zur berechnung der gegenkathete
        int geK;
        double anKD = (double) anK, angleD = (double) angle, help;
        angleD = angle* (Math.PI/180.0);
        help = Math.tan(angleD);
        geK =(int) (help * anKD);
        return geK;
    }

    public int checkPos(int touchedX, int touchedY){ //gibts aus Welches Feld der Dartscheibe gedrueckt wurde
        int j;
        int number=-1;
        int alpha, beta, test;
        for(int i=0; i<20; i++){
            if(i+1 >= 20){
                j=0;
            }else{
                j=i+1;
            }
            if(maxX < 1000){
                alpha = (fieldX[i]*1000) + fieldY[i];
                beta = (fieldX[j]*1000) + fieldY[j];
                test = (touchedX*1000) + touchedY;
            }else{
                alpha = (fieldX[i]*10000) + fieldY[i];
                beta = (fieldX[j]*10000) + fieldY[j];
                test = (touchedX*10000) + touchedY;
            }

            if((i<7) && (i>=2)){
                if((test <= alpha) && (test >= beta) && (touchedY==0)){
                    number = numbers[i];
                    return number;
                }
            }
            if((i<2) || (i>=17)) {
                if((test <= alpha) && (test >= beta)){
                    number = numbers[i];
                    return number;
                }
            }
            if((i>=7) && (i<12)) {
                if((test >= alpha) && (test <= beta)){
                    number = numbers[i];
                    return number;
                }
            }else{
                if((test >= alpha) && (test <= beta) && (touchedY == maxY)){
                    number = numbers[i];
                    return number;
                }
            }

        }
        return number;
    }

    public Point touchCalc(int touchX, int touchY){ //berechnet die Koordinaten am Rand des Images des gedrueckten Punkts
        int area = touchAreaCheck(touchX, touchY);
        double dX, dY;
        int value;
        int phi;
        int newdX, newdY;
        int touchedX, touchedY;
        switch(area){
            case 1:
                dX = touchX - maxX/2;
                dY = maxY/2 - touchY;
                phi = (int) (Math.atan(dY/dX)* (180.0/Math.PI));
                if(phi <= 45){
                    newdY = delta(phi, maxX/2);
                    touchedX = maxX;
                    touchedY = (maxY/2) - newdY;
                }else{
                    newdX = delta((90-phi), (maxY/2));
                    touchedX = (maxX/2) + newdX;
                    touchedY = 0;
                }
                break;
            case 2:
                dX = maxX/2 - touchX;
                dY = maxY/2 - touchY;
                phi = (int) (Math.atan(dX/dY)* (180.0/Math.PI));
                if(phi <= 45){
                    newdX = delta(phi, maxY/2);
                    touchedX = (maxX/2) - newdX;
                    touchedY = 0;
                }else{
                    newdY = delta((90-phi), (maxX/2));
                    touchedX = 0;
                    touchedY = (maxY/2) - newdY;
                }
                break;
            case 3:
                dX = maxX/2 - touchX;
                dY = touchY - maxY/2;
                phi = (int) (Math.atan(dY/dX)* (180.0/Math.PI));
                if(phi <= 45){
                    newdY = delta(phi, maxX/2);
                    touchedX = 0;
                    touchedY = (maxY/2) + newdY;
                }else{
                    newdX = delta((90-phi), (maxY/2));
                    touchedX = (maxX/2) - newdX;
                    touchedY = maxY;
                }
                break;
            case 4:
                dX = touchX - maxX/2;
                dY = touchY - maxY/2;
                phi = (int) (Math.atan(dX/dY)* (180.0/Math.PI));
                if(phi <= 45){
                    newdX = delta(phi, maxY/2);
                    touchedX = (maxX/2)+ newdX;
                    touchedY = maxY;
                }else{
                    newdY = delta((90-phi), (maxX/2));
                    touchedX = maxX;
                    touchedY = (maxY/2) + newdY;
                }
                break;
            default:
                touchedX = -1;
                touchedY = -1;
                break;
        }
        return new Point(touchedX, touchedY);
        //value = checkPos(touchedX, touchedY);
        //int distance = checkDistance(touchX, touchY);


    }

    public int checkDistance(int touchX, int touchY){ //berechnet die Distanz vom Mittelpunkt des Images bis zum gedrueckten Punkt
        return (int) Math.sqrt(Math.pow((midX - touchX), 2) + Math.pow((midY - touchY), 2));
    }

    private int touchAreaCheck(int touchX, int touchY){ //returns number from 1 to 4, berechnet in welchem Quadranten sich der touch befindet
        int area = 10;
        if(touchX >= maxX/2){
            if(touchY >= maxY/2){
                area = 4;
            }else{
                area = 1;
            }
        }else{
            if(touchY >= maxY/2){
                area = 3;
            }else{
                area = 2;
            }
        }
        return area;
    }

    private void calcEdge(){        //X- und Y-Koordinaten der Linen am Rand des Images berechnen
        for(int i=0; i<4; i++){
            switch(i){
                case 0:
                    angle = 9;
                    for(int j=0; j<5; j++){
                        if(j <= 2){
                            y = delta(angle, maxX/2);
                            fieldX[index] = maxX;
                            fieldY[index] = (maxY/2)-y;
                            angle += 18;
                            index++;
                        }else{
                            x = delta(angle, maxY/2);
                            fieldX[index] = (maxX/2) + x;
                            fieldY[index] = 0;
                            angle -= 18;
                            index++;
                        }
                        if(j == 2){
                            angle = 90-angle;
                        }
                    }
                    break;
                case 1:
                    angle = 9;
                    for(int j=0; j<5; j++){
                        if(j <= 2){
                            x = delta(angle, maxY/2);
                            fieldX[index] = (maxX/2) - x;
                            fieldY[index] = 0;
                            angle += 18;
                            index++;
                        }else{
                            y = delta(angle, maxX/2);
                            fieldX[index] = 0;
                            fieldY[index] = (maxY/2) - y;
                            angle -= 18;
                            index++;
                        }
                        if(j == 2){
                            angle = 90-angle;
                        }
                    }
                    break;
                case 2:
                    angle = 9;
                    for(int j=0; j<5; j++){
                        if(j <= 2){
                            y = delta(angle, maxX/2);
                            fieldX[index] = 0;
                            fieldY[index] = (maxY/2)+y;
                            angle += 18;
                            index++;
                        }else{
                            x = delta(angle, maxY/2);
                            fieldX[index] = (maxX/2) - x;
                            fieldY[index] = maxY;
                            angle -= 18;
                            index++;
                        }
                        if(j == 2){
                            angle = 90-angle;
                        }
                    }
                    break;
                case 3:
                    angle = 9;
                    for(int j=0; j<5; j++){
                        if(j <= 2){
                            x = delta(angle, maxY/2);
                            fieldX[index] = (maxX/2) + x;
                            fieldY[index] = maxY;
                            angle += 18;
                            index++;
                        }else{
                            y = delta(angle, maxX/2);
                            fieldX[index] = maxX;
                            fieldY[index] = (maxY/2) + y;
                            angle -= 18;
                            index++;
                        }
                        if(j == 2){
                            angle = 90-angle;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        fieldX[7] = 0;
        fieldY[7] = 0;
        fieldX[17]= maxX;
        fieldY[17]= maxY;
    }

    private int calcCircles(){ //distanz der ringe zum Mittelpunkt berechnen
       //int circle1 = radius; //aeußerster Ring
        double radiusLocal = (double)radius;
        circles[0] = (int)radiusLocal;
        circles[1] = (int)(radiusLocal*(9.0/10.0));
        circles[2] = (int)(radiusLocal*(77.0/120.0));
        circles[3] = (int)(radiusLocal*(13.0/24.0));
        circles[4] = (int)(radiusLocal*(5.0/24.0));
        circles[5] = (int)(radiusLocal*(3.0/40.0));
        return 0;
    }

    public ThrowResult calcAll(int xCoord, int yCoord){
        int result = 0;
        int state = 0;
        Point touched = touchCalc(xCoord, yCoord);
        result = checkPos(touched.x, touched.y);
        int distance = checkDistance(xCoord, yCoord);
        if(distance > circles[0]){
            state = FAIL;
            result = 0;
        }else if(distance > circles[1]){
            state = DOUBLE;
        }else if(distance > circles[2]){
            state = NORMAL;
        }else if(distance > circles[3]){
            state = TRIPLE;
        }else if(distance > circles[4]){
            state = NORMAL;
        }else if(distance > circles[5]){
            state = NORMAL;
            result = 25;
        }else{
            state = DOUBLE;
            result = 25;
        }
        return new ThrowResult(result, state);
    }
}
