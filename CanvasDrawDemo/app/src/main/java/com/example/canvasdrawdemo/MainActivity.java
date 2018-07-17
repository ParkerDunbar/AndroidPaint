package com.example.canvasdrawdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.session.PlaybackState;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.AttributedCharacterIterator;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private CanvasView customCanvas;
    SharedPreferences sharedPreferences;
    String encoded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        customCanvas.setBackgroundColor(Color.WHITE);
        sharedPreferences = getSharedPreferences("canvasPref", Context.MODE_PRIVATE);

        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


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
        //Shapes
        Spinner shapeSpinner = (Spinner) findViewById(R.id.shape_spinner);
        shapeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> shapeAdapter = ArrayAdapter.createFromResource(this, R.array.shape_array, android.R.layout.simple_spinner_item);
        shapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(shapeAdapter);

        Button background = (Button) findViewById(R.id.set_background);
        background.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customCanvas.setBackgroundColor();
            }
        });

        Button eraser = (Button) findViewById(R.id.erase);
        eraser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                customCanvas.setEraser();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        switch (arg0.getId()) {
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
            case R.id.shape_spinner:
                String shape = (String) arg0.getItemAtPosition(position);
                customCanvas.setShape(shape);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void clearCanvas(View v){
        if(v.getId() == R.id.clearButton){
            customCanvas.clearCanvas();
        }
    }

    public void saveCanvas(View v) {
        if (v.getId() == R.id.saveButton) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            customCanvas.mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            encoded = Base64.encodeToString(b, Base64.DEFAULT);
            editor.putString("Canvas", encoded.toString());
            editor.commit();
            Toast.makeText(getApplicationContext(), "Image has been saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadCanvas(View v) {
        if (v.getId() == R.id.loadButton) {
            encoded = sharedPreferences.getString("Canvas", "");
            byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
            Bitmap bm = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            customCanvas.mCanvas.drawBitmap(bm, 0,0,customCanvas.mPaint);
            Toast.makeText(getApplicationContext(), "Image has been loaded", Toast.LENGTH_SHORT).show();
        }
    }
}
