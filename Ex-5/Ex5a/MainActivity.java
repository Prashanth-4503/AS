package com.example.rec;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));  // Create and set an instance of MyView
    }

    // Custom View class
    private class MyView extends View {

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);  // Always call super.onDraw() first

            // Get the center of the canvas
            int centerX = canvas.getWidth() / 2;
            int centerY = canvas.getHeight() / 2;

            // Define the size of the rectangle
            int rectWidth = 900;
            int rectHeight =700;

            // Calculate the left, top, right, and bottom values to center the rectangle
            int left = centerX - rectWidth / 2;
            int top = centerY - rectHeight / 2;
            int right = centerX + rectWidth / 2;
            int bottom = centerY + rectHeight / 2;

            // Create a paint object to set the drawing properties
            Paint myPaint = new Paint();
            myPaint.setColor(Color.GREEN);  // Set paint color
            myPaint.setStyle(Paint.Style.STROKE);  // Set stroke style (outline)
            myPaint.setStrokeWidth(10);  // Set the stroke width

            // Draw the rectangle at the calculated position
            canvas.drawRect(left, top, right, bottom, myPaint);
        }
    }
}
