package com.gamecodeschool.tappydefender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by joelr on 03-Nov-16.
 */

public class TDView extends SurfaceView implements Runnable {

    volatile boolean playing;
    Thread gameThread = null;

    // Game objects
    private PlayerShip player;

    // For drawing
    private Paint paint;
    private SurfaceHolder ourHolder;

    // Constructor
    TDView(Context context, int x, int y) {
        super(context);

        // Initialize our drawing object
        ourHolder = getHolder();
        paint = new Paint();

        // Initialize the player ship
        player = new PlayerShip(context, x, y);

    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {

        // Update the player
        player.update();
    }

    private void draw() {

        if (ourHolder.getSurface().isValid()) {

            // First we lock the area of memory we will be drawing to
            Canvas canvas = ourHolder.lockCanvas();

            // Rub out the last frame
            canvas.drawColor(Color.argb(255,0,0,0));

            // Draw the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            // Unlock and draw the scene
            ourHolder.unlockCanvasAndPost(canvas);
        }

    }

    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {

        }

    }

    // Clean up our thread if the game is interrupted or the player quits
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {

        }
    }

    // Make a new threat and start it
    // Execution moves to our R
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    // SurfaceView allows handling the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }
        return true;
    }
}
