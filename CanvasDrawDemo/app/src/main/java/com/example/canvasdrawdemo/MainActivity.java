package com.example.canvasdrawdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        customCanvas.setBackgroundColor(Color.WHITE);

        //Color
        Spinner colorSpinner = (Spinner) findViewById(R.id.color_spinner);
        colorSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(colorAdapter);
        //Size
        Spinner sizeSpinner = (Spinner) findViewById(R.id.size_spinner);
        sizeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this, R.array.size_array, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        Button eraser = (Button) findViewById(R.id.erase);
        eraser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customCanvas.setEraser();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        switch(arg0.getId()){
            case R.id.color_spinner:
                String color = (String) arg0.getItemAtPosition(position);
                Log.d("TEST", color);
                customCanvas.setColor(color);
                break;
            case R.id.size_spinner:
                String size = (String) arg0.getItemAtPosition(position);
                int newBrushSize = Integer.parseInt(size);
                customCanvas.setSize(newBrushSize);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
