package com.example.canvasdrawdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// Code from https://examples.javacodegeeks.com/android/core/graphics/canvas-graphics/android-canvas-example/

public class MainActivity extends AppCompatActivity {
    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }
}
