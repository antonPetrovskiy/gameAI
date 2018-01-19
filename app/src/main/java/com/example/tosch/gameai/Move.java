package com.example.tosch.gameai;

import android.app.Activity;

import java.util.LinkedList;

/**
 * Created by tosch on 20.09.2017.
 */

public class Move {
    Activity activity;

    public Move(MainActivity a){
        activity = a;
    }





    public void moveBack(){
        int x = Integer.parseInt(""+MainActivity.movement.getLast().charAt(0));
        int y = Integer.parseInt(""+MainActivity.movement.getLast().charAt(1));
        MainActivity.movement.removeLast();
        int x1 = Integer.parseInt(""+MainActivity.movement.getLast().charAt(0));
        int y1 = Integer.parseInt(""+MainActivity.movement.getLast().charAt(1));
        move(x,y,x1,y1);
        MainActivity.movement.removeLast();
    }

    public void move(int x, int y, int x1, int y1){
            MainActivity.terminal.append("Moved to " + x1 + y1 + "\n");
            if(checkPlace(x1, y1)){
                MainActivity.restartGame();
                return;
            }
            MainActivity.map[x][y] = 'o';
            MainActivity.map[x1][y1] = 'p';
            MainActivity.getButton(x,y).setImageResource(0);
            MainActivity.getButton(x1,y1).setImageResource(R.drawable.player);
            if(checkHole(x1,y1))
                MainActivity.terminal.append("Hole is near" + "\n");
            if(checkMonster(x1,y1))
                MainActivity.terminal.append("Monster is near" + "\n");

            MainActivity.movement.addLast(String.valueOf(x1)+String.valueOf(y1));
    }


    public boolean checkMonster(int i, int j){
        if(i == 0 && j == 0){
            if(MainActivity.map[i][j+1] == 'm' || MainActivity.map[i+1][j] == 'm')
                return true;
            return false;
        }
        if(i == 0 && j == 3) {
            if(MainActivity.map[i][j-1] == 'm' || MainActivity.map[i+1][j] == 'm')
                return true;
            return false;
        }
        if(i == 3 && j == 0){
            if(MainActivity.map[i][j+1] == 'm' || MainActivity.map[i-1][j] == 'm')
                return true;
            return false;
        }
        if(i == 3 && j == 3) {
            if(MainActivity.map[i][j-1] == 'm' || MainActivity.map[i-1][j] == 'm')
                return true;
            return false;
        }
        if(i == 0 && j != 0 && j != 3) {
            if(MainActivity.map[i][j-1] == 'm' || MainActivity.map[i][j+1] == 'm' || MainActivity.map[i+1][j] == 'm')
                return true;
            return false;
        }
        if(j == 0 && i != 0 && i != 3) {
            if(MainActivity.map[i-1][j] == 'm' || MainActivity.map[i+1][j] == 'm' || MainActivity.map[i][j+1] == 'm')
                return true;
            return false;
        }
        if(i == 3 && j != 0 && j != 3) {
            if(MainActivity.map[i][j-1] == 'm' || MainActivity.map[i][j+1] == 'm' || MainActivity.map[i-1][j] == 'm')
                return true;
            return false;
        }
        if(j == 3 && i != 0 && i != 3) {
            if(MainActivity.map[i-1][j] == 'm' || MainActivity.map[i+1][j] == 'm' || MainActivity.map[i][j-1] == 'm')
                return true;
            return false;
        }


        if(MainActivity.map[i][j-1] == 'm' || MainActivity.map[i+1][j] == 'm' || MainActivity.map[i][j+1] == 'm' || MainActivity.map[i-1][j] == 'm')
            return true;

        return false;
    }

    public boolean checkHole(int i, int j){
        if(i == 0 && j == 0){
            if(MainActivity.map[i][j+1] == 'h' || MainActivity.map[i+1][j] == 'h')
                return true;
            return false;
        }
        if(i == 0 && j == 3) {
            if(MainActivity.map[i][j-1] == 'h' || MainActivity.map[i+1][j] == 'h')
                return true;
            return false;
        }
        if(i == 3 && j == 0){
            if(MainActivity.map[i][j+1] == 'h' || MainActivity.map[i-1][j] == 'h')
                return true;
            return false;
        }
        if(i == 3 && j == 3) {
            if(MainActivity.map[i][j-1] == 'h' || MainActivity.map[i-1][j] == 'h')
                return true;
            return false;
        }
        if(i == 0 && j != 0 && j != 3) {
            if(MainActivity.map[i][j-1] == 'h' || MainActivity.map[i][j+1] == 'h' || MainActivity.map[i+1][j] == 'h')
                return true;
            return false;
        }
        if(j == 0 && i != 0 && i != 3) {
            if(MainActivity.map[i-1][j] == 'h' || MainActivity.map[i+1][j] == 'h' || MainActivity.map[i][j+1] == 'h')
                return true;
            return false;
        }
        if(i == 3 && j != 0 && j != 3) {
            if(MainActivity.map[i][j-1] == 'h' || MainActivity.map[i][j+1] == 'h' || MainActivity.map[i-1][j] == 'h')
                return true;
            return false;
        }
        if(j == 3 && i != 0 && i != 3) {
            if(MainActivity.map[i-1][j] == 'h' || MainActivity.map[i+1][j] == 'h' || MainActivity.map[i][j-1] == 'h')
                return true;
            return false;
        }


        if(MainActivity.map[i][j-1] == 'h' || MainActivity.map[i+1][j] == 'h' || MainActivity.map[i][j+1] == 'h' || MainActivity.map[i-1][j] == 'h')
            return true;

        return false;
    }

    public boolean checkPlace(int i, int j){
        switch(MainActivity.map[i][j]) {
            case 'g':
                MainActivity.terminal.append("You win" + "\n");
                return true;
            case 'h':
                MainActivity.terminal.append("You loose" + "\n");
                return true;
            case 'm':
                MainActivity.terminal.append("You loose" + "\n");
                return true;
        }
        return false;
    }
    

}
