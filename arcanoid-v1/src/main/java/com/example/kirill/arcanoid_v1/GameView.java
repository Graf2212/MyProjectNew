package com.example.kirill.arcanoid_v1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kirill on 16.11.2015.
 */
public class GameView extends SurfaceView {
    private GameThread mThread;

    public int shotX, shotY;

    private boolean running = false;


    public class  GameThread extends Thread{
        private GameView view;

        public GameThread(GameView view){
            this.view = view;
        }

        public void setRunning (boolean run){
            running = run;
        }

        public void run(){
            while (running){
                Canvas canvas = null;
                try{
                    canvas = view.getHolder().lockCanvas();
                    synchronized (view.getHolder()){
                        onDraw(canvas);
                    }
                } catch (Exception e){}
                finally{
                    if (canvas !=null){
                        view.getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    public GameView(Context context){
        super(context);
        mThread = new GameThread(this);

        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mThread.setRunning(true);
                mThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                mThread.setRunning(false);
                while (retry){
                    try{
                        mThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }

            }
        });
    }

    protected void onDraw (Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }



}
