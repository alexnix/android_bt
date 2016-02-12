package com.example.m17336.bt_to_pc;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.cocosw.bottomsheet.BottomSheet;
import com.example.m17336.bt_to_pc.bluetooth_specifics.CommunicationThread;

import java.util.Map;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ControlsActivity extends AppCompatActivity {

    int prevColor = 0xff0000ff;
    int redMask   = 0x00ff0000;
    int greenMask = 0x0000ff00;
    int blueMask  = 0x000000ff;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        final Activity that = this;
        final ToggleButton onoff = (ToggleButton) findViewById(R.id.toggleButton);
        final SeekBar intensity = (SeekBar) findViewById(R.id.seekBar);

        MyApplication app = (MyApplication) getApplicationContext();
        final CommunicationThread com = app.getCom();

        Button colorPicker = (Button) findViewById(R.id.colorPickerBtn);
        final AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, prevColor, false, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // color is the color selected by the user.
                int r = (color & redMask) >> 16;
                int g = (color & greenMask) >> 8;
                int b = (color & blueMask);
                prevColor = color;
                com.write("HUE " + r + " " + g + " " + b);

                //  onoff.setBackgroundColor(Color.rgb(r, g, b));

            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }
        });
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onoff.isChecked())
                    com.write("ON");
                else
                    com.write("OFF");
            }
        });

        onoff.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new BottomSheet.Builder(that).title(onoff.isChecked() ? "Turn OFF in" : "Turn ON in").sheet(R.menu.bottom_sheet).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String action = onoff.isChecked() ? "OFF " : "ON ";
                        switch (which) {
                            case R.id.m5:
                                com.write(action + "5");
                                break;
                            case R.id.m10:
                                com.write(action + "10");
                                break;
                            case R.id.m15:
                                com.write(action + "15");
                                break;
                            case R.id.m30:
                                com.write(action + "30");
                                break;
                            case R.id.h1:
                                com.write(action + "60");
                                break;
                        }
                    }
                }).show();
                return true;
            }
        });

        intensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                com.write("I "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
