package com.example.m17336.bt_to_pc;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.m17336.bt_to_pc.bluetooth_specifics.CommunicationThread;

import java.util.Map;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ControlsActivity extends AppCompatActivity {

    CommunicationThread  com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);
        MyApplication app = (MyApplication) getApplicationContext();
        com = app.getCom();

        Button up = (Button) findViewById(R.id.up),
                down = (Button) findViewById(R.id.down),
                left = (Button) findViewById(R.id.left),
                right = (Button) findViewById(R.id.right),
                rotate = (Button) findViewById(R.id.rotate);

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "UP", Toast.LENGTH_SHORT).show();
                if( com!=null ) com.write("1");
                return false;
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "DOWN", Toast.LENGTH_SHORT).show();
                if( com!=null ) com.write("2");
                return false;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "LEFT", Toast.LENGTH_SHORT).show();
                if( com!=null ) com.write("3");
                return false;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "RIGHT", Toast.LENGTH_SHORT).show();
                if( com!=null ) com.write("4");
                return false;
            }
        });

        rotate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "ROTATE", Toast.LENGTH_SHORT).show();
                if( com!=null ) com.write("5");
                return false;
            }
        });
    }

    public void up (View v) {
        Toast.makeText(getApplicationContext(), "UP", Toast.LENGTH_SHORT).show();
        if( com!=null ) com.write("1");
    }

    public void down (View v) {
        Toast.makeText(getApplicationContext(), "DOWN", Toast.LENGTH_SHORT).show();
        if( com!=null ) com.write("2");
    }

    public void left (View v) {
        Toast.makeText(getApplicationContext(), "LEFT", Toast.LENGTH_SHORT).show();
        if( com!=null ) com.write("3");
    }

    public void right (View v) {
        Toast.makeText(getApplicationContext(), "RIGHT", Toast.LENGTH_SHORT).show();
        if( com!=null ) com.write("4");
    }

    public void rotate (View v) {
        Toast.makeText(getApplicationContext(), "ROTATE", Toast.LENGTH_SHORT).show();
        if( com!=null ) com.write("5");
    }
}
