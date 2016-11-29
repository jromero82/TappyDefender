package com.gamecodeschool.tappydefender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by joelr on 03-Nov-16.
 */

public class PlayerShip {

    private final int GRAVITY = -12;

    // Stop ship leaving the screen
    private int maxY;
    private int minY;

    // Limit the bounds of the ship's speed
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private Bitmap bitmap;
    private int x, y;
    private int speed = 0;
    private boolean boosting;

    public PlayerShip(Context context, int screenX, int screenY) {
        boosting = false;
        x = 50;
        y = 50;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
    }

    public void update() {

        // Are we boosting?
        if (boosting) {
            // Speed up
            speed += 2;
        } else {
            // Slow down
            speed -= 5;
        }

        // Constrain top speed
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        // Never stop completely
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        // Move ship up or down
        y -= speed + GRAVITY;

        // Don't let the ship stray off screen
        if (y < minY) {
            y = minY;
        }

        if (y > maxY) {
            y = maxY;
        }
    }

    // Getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }
}
