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

            // Define the radius of the circle
            int radius = 500;  // You can adjust the radius as needed

            // Create a paint object to set the drawing properties
            Paint myPaint = new Paint();
            myPaint.setColor(Color.RED);  // Set paint color (Red for example)
            myPaint.setStyle(Paint.Style.STROKE);  // Set stroke style (outline)
            myPaint.setStrokeWidth(10);  // Set the stroke width

            // Draw the circle at the center of the canvas
            canvas.drawCircle(centerX, centerY, radius, myPaint);
        }
    }
}
