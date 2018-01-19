package com.example.tosch.gameai;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    static ImageButton p30;
    static ImageButton p31;
    static ImageButton p32;
    static ImageButton p33;
    static ImageButton p20;
    static ImageButton p21;
    static ImageButton p22;
    static ImageButton p23;
    static ImageButton p10;
    static ImageButton p11;
    static ImageButton p12;
    static ImageButton p13;
    static ImageButton p00;
    static ImageButton p01;
    static ImageButton p02;
    static ImageButton p03;

    ImageButton left;
    ImageButton up;
    ImageButton right;
    ImageButton down;
    ImageButton center;

    static TextView terminal;



    static Move m;

    int X = 3;
    int Y = 0;

    static LinkedList <String> movement = new LinkedList <String>();
    static char[][] map = new char [4][4];
    static int[][] playerMap = new int [4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        m = new Move(this);
        initButtonAction();
        movement.addLast("30");



        initPlayerMap();
        generateMap();
        //valueOfPosition();
        //showMap();
        //showPlayerMap();
        //generateMap();
        //showMap();

    }



    private static int gg = 3;
    private static int bg = 0;

    static public void cntReset() {
        gg = 3;
        bg = 0;
    }

    public void move(){
        int i = gg;
        int j = bg;

            if(m.checkHole(i,0) || m.checkMonster(i,0)){
                return;
            }

            if(!m.checkHole(i,j) && !m.checkMonster(i,j)){
                m.move(i,j,i,j+1);
            }else{
                bg = 4;
            }

        if (bg >= 4) {
            bg = 0;

            while(movement.getLast().charAt(1)!='0'){
                m.moveBack();
            }
            //movement.removeLast();
            if (gg <= 0) {
                gg = 3;
            } else {
                gg--;
                m.move(gg, bg, gg, bg);
            }
        } else {
            bg++;
        }
    }

    public void valueOfPosition(){
            for(int i = 3; i  >= 0 ; i --){
                for(int j = 0; j < 4; j ++){
                    X = i;
                    Y = j;
                    if(!m.checkHole(X,Y) && !m.checkMonster(X,Y)){
                        playerMap[X][Y] = 1;
                    }else if(m.checkHole(X,Y) && !m.checkMonster(X,Y)){
                        playerMap[X][Y] = 2;
                    }else if(!m.checkHole(X,Y) && m.checkMonster(X,Y)){
                        playerMap[X][Y] = 3;
                    }else if(m.checkHole(X,Y) && m.checkMonster(X,Y)){
                        playerMap[X][Y] = 4;
                    }


                }
            }
    }

    public void initView(){
        p30 = (ImageButton) findViewById(R.id.p30);
        p31 = (ImageButton) findViewById(R.id.p31);
        p32 = (ImageButton) findViewById(R.id.p32);
        p33 = (ImageButton) findViewById(R.id.p33);
        p20 = (ImageButton) findViewById(R.id.p20);
        p21 = (ImageButton) findViewById(R.id.p21);
        p22 = (ImageButton) findViewById(R.id.p22);
        p23 = (ImageButton) findViewById(R.id.p23);
        p10 = (ImageButton) findViewById(R.id.p10);
        p11 = (ImageButton) findViewById(R.id.p11);
        p12 = (ImageButton) findViewById(R.id.p12);
        p13 = (ImageButton) findViewById(R.id.p13);
        p00 = (ImageButton) findViewById(R.id.p00);
        p01 = (ImageButton) findViewById(R.id.p01);
        p02 = (ImageButton) findViewById(R.id.p02);
        p03 = (ImageButton) findViewById(R.id.p03);

        left = (ImageButton) findViewById(R.id.left);
        right = (ImageButton) findViewById(R.id.right);
        up = (ImageButton) findViewById(R.id.up);
        down = (ImageButton) findViewById(R.id.down);
        center = (ImageButton) findViewById(R.id.center);

        terminal = (TextView) findViewById(R.id.terminalOutput);
        terminal.setMovementMethod(new ScrollingMovementMethod());


    }

    public void initButtonAction(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.move(Integer.parseInt(""+movement.getLast().charAt(0)),Integer.parseInt(""+movement.getLast().charAt(1)),Integer.parseInt(""+movement.getLast().charAt(0)),Integer.parseInt(""+movement.getLast().charAt(1))-1);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.move(Integer.parseInt(""+movement.getLast().charAt(0)),Integer.parseInt(""+movement.getLast().charAt(1)),Integer.parseInt(""+movement.getLast().charAt(0)),Integer.parseInt(""+movement.getLast().charAt(1))+1);
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.move(Integer.parseInt(""+movement.getLast().charAt(0)),Integer.parseInt(""+movement.getLast().charAt(1)),Integer.parseInt(""+movement.getLast().charAt(0))-1,Integer.parseInt(""+movement.getLast().charAt(1)));
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.move(Integer.parseInt(""+movement.getLast().charAt(0)),Integer.parseInt(""+movement.getLast().charAt(1)),Integer.parseInt(""+movement.getLast().charAt(0))+1,Integer.parseInt(""+movement.getLast().charAt(1)));
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move();
            }
        });

        p00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }


    public static void restartGame(){
        terminal.setText("");
        cntReset();
        remove();
        initPlayerMap();
        generateMap();
        movement.clear();
        movement.addLast("30");
    }

    public static void initPlayerMap(){
        for(int i = 0; i < 4 ; i ++){
            for(int j = 0; j < 4; j ++){
                playerMap[i][j] = 0;
                map[i][j] = 'o';
            }
        }
        map[3][0] = 'p';
    }

    public static void generateMap(){
        Random rand = new Random();
        int x;
        int y;

        getButton(3,0).setImageResource(setEnemy('p'));
        map[3][0] = 'p';


        do {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
        }while(map[x][y] != 'o');
        getButton(x,y).setImageResource(setEnemy('h'));
        map[x][y] = 'h';

        do {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
        }while(map[x][y] != 'o');
        getButton(x,y).setImageResource(setEnemy('m'));
        map[x][y] = 'm';

        do {
            x = rand.nextInt(4);
            y = rand.nextInt(4);
        }while(map[x][y] != 'o');
        getButton(x,y).setImageResource(setEnemy('g'));
        map[x][y] = 'g';


        if(m.checkHole(3,0) || m.checkMonster(3,0)){
            terminal.append("Cant move. No safety moves" + "\n");
        }
    }

    public void showMap(){
        for(int i = 0; i < 4 ; i ++){
            for(int j = 0; j < 4; j ++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void showPlayerMap(){
        for(int i = 0; i < 4 ; i ++){
            for(int j = 0; j < 4; j ++){
                System.out.print(playerMap[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public static int setEnemy(char c){
        switch(c){
            case 'm':
                return R.drawable.monster;
            case 'h':
                return R.drawable.hole;
            case 'g':
                return R.drawable.gold;
            case 'p':
                return R.drawable.player;
        }
        return 0;
    }

    public static ImageView getButton(int x, int y){
        if(x==0 && y ==0)
            return p30;
        if(x==0 && y ==1)
            return p31;
        if(x==0 && y ==2)
            return p32;
        if(x==0 && y ==3)
            return p33;
        if(x==1 && y ==0)
            return p20;
        if(x==1 && y ==1)
            return p21;
        if(x==1 && y ==2)
            return p22;
        if(x==1 && y ==3)
            return p23;
        if(x==2 && y ==0)
            return p10;
        if(x==2 && y ==1)
            return p11;
        if(x==2 && y ==2)
            return p12;
        if(x==2 && y ==3)
            return p13;
        if(x==3 && y ==0)
            return p00;
        if(x==3 && y ==1)
            return p01;
        if(x==3 && y ==2)
            return p02;
        if(x==3 && y ==3)
            return p03;
        return null;
    }

    public static void remove(){
        p30.setImageResource(0);
        p31.setImageResource(0);
        p32.setImageResource(0);
        p33.setImageResource(0);
        p20.setImageResource(0);
        p21.setImageResource(0);
        p22.setImageResource(0);
        p23.setImageResource(0);
        p10.setImageResource(0);
        p11.setImageResource(0);
        p12.setImageResource(0);
        p13.setImageResource(0);
        p01.setImageResource(0);
        p02.setImageResource(0);
        p03.setImageResource(0);
    }

    public void checkState(){

    }
}
