package com.example.canvasdrawdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    public void saveCanvas(View v) {
        if(v.getId() == R.id.saveButton) {
            customCanvas.saveCanvas("image1");
            Toast.makeText(getApplicationContext(), "Image has been saved", Toast.LENGTH_LONG).show();
        }
    }

    public void loadCanvas(View v) {
        if(v.getId() == R.id.loadButton) {
            customCanvas.loadCanvas();
            Toast.makeText(getApplicationContext(), "Image has been loaded", Toast.LENGTH_LONG).show();
        }
    }
}
