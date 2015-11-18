package com.example.kirill.arcanoid_v1;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Kirill on 16.11.2015.
 */
public class Ball {
    public int x, y;
    private int speedX, speedY;
    public  int r;
    Paint p;
    public Ball(GameView gameView){
        p.setColor(Color.RED);
        p.setStrokeWidth(10);
        this.x = 100;
        this.y = 100;
        speedX=12;
        speedY=12;
    }
    public void update(){
        x += speedX;
        y+= speedY;
    }
    public void onDraw(){
        update();
        //canvas.drawCircle;

    }
}
