package com.example.tosch.gameai;

import android.app.Activity;

/**
 * Created by tosch on 20.09.2017.
 */

public class Move {
    Activity activity;

    public Move(MainActivity a){
        activity = a;
    }



    public void moveRight(){
        for(int i = 0; i < 4 ; i ++){
            for(int j = 0; j < 4; j ++){
                if(MainActivity.map[i][j] == 'p' && j != 3){
                    MainActivity.terminal.append("Moved to " + i + (j+1) + "\n");
                    if(checkPlace(i, j+1)){
                        MainActivity.remove();
                        MainActivity.initPlayerMap();
                        MainActivity.generateMap();
                        return;
                    }
                    MainActivity.map[i][j] = 'o';
                    MainActivity.map[i][j+1] = 'p';
                    MainActivity.getButton(i,j).setImageResource(0);
                    MainActivity.getButton(i,j+1).setImageResource(R.drawable.player);
                    if(checkHole(i,j+1))
                        MainActivity.terminal.append("Hole is near" + "\n");
                    if(checkMonster(i,j+1))
                        MainActivity.terminal.append("Monster is near" + "\n");
                    return;
                }
            }
        }
    }

    public void moveLeft(){
        for(int i = 0; i < 4 ; i ++){
            for(int j = 0; j < 4; j ++){
                if(MainActivity.map[i][j] == 'p' && j != 0){
                    MainActivity.terminal.append("Moved to " + i + (j-1) + "\n");
                    if(checkPlace(i, j-1)){
                        MainActivity.remove();
                        MainActivity.initPlayerMap();
                        MainActivity.generateMap();
                        return;
                    }
                    MainActivity.map[i][j] = 'o';
                    MainActivity.map[i][j-1] = 'p';
                    MainActivity.getButton(i,j).setImageResource(0);
                    MainActivity.getButton(i,j-1).setImageResource(R.drawable.player);
                    if(checkHole(i,j-1))
                        MainActivity.terminal.append("Hole is near" + "\n");
                    if(checkMonster(i,j-1))
                        MainActivity.terminal.append("Monster is near" + "\n");
                    return;
                }
            }
        }
    }

    public void moveUp(){
        for(int i = 0; i < 4 ; i ++){
            for(int j = 0; j < 4; j ++){
                if(MainActivity.map[i][j] == 'p' && i != 0){
                    MainActivity.terminal.append("Moved to " + (i-1) + j + "\n");
                    if(checkPlace(i-1, j)){
                        MainActivity.remove();
                        MainActivity.initPlayerMap();
                        MainActivity.generateMap();
                        return;
                    }
                    MainActivity.map[i][j] = 'o';
                    MainActivity.map[i-1][j] = 'p';
                    MainActivity.getButton(i,j).setImageResource(0);
                    MainActivity.getButton(i-1,j).setImageResource(R.drawable.player);
                    if(checkHole(i-1,j))
                        MainActivity.terminal.append("Hole is near" + "\n");
                    if(checkMonster(i-1,j))
                        MainActivity.terminal.append("Monster is near" + "\n");
                    return;
                }
            }
        }
    }

    public void moveDown(){
        for(int i = 0; i < 4 ; i ++){
            for(int j = 0; j < 4; j ++){
                if(MainActivity.map[i][j] == 'p' && i != 3){
                    MainActivity.terminal.append("Moved to " + (i+1) + j + "\n");
                    if(checkPlace(i+1, j)){
                        MainActivity.remove();
                        MainActivity.initPlayerMap();
                        MainActivity.generateMap();
                        return;
                    }
                    MainActivity.map[i][j] = 'o';
                    MainActivity.map[i+1][j] = 'p';
                    MainActivity.getButton(i,j).setImageResource(0);
                    MainActivity.getButton(i+1,j).setImageResource(R.drawable.player);
                    if(checkHole(i+1,j))
                        MainActivity.terminal.append("Hole is near" + "\n");
                    if(checkMonster(i+1,j))
                        MainActivity.terminal.append("Monster is near" + "\n");
                    return;
                }
            }
        }
    }

    public void moveBack(){

    }

    public void go(){
        int x = Integer.parseInt(""+MainActivity.movement.getLast().charAt(0));
        int y = Integer.parseInt(""+MainActivity.movement.getLast().charAt(1));


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
