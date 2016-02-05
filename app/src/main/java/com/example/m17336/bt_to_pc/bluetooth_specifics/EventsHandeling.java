package com.example.m17336.bt_to_pc.bluetooth_specifics;

import android.os.Message;
import android.support.design.widget.Snackbar;

public class EventsHandeling extends android.os.Handler {

    public static final int SOCKET_CONNECTED = 1;
    public static final int DATA_RECEIVED = 2;
    public static final int ERROR = 3;

    private Snackbar snackbar;

    public EventsHandeling(Snackbar snackbar){
        this.snackbar = snackbar;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SOCKET_CONNECTED: {
                break;
            }

            case DATA_RECEIVED: {
                break;
            }

            case ERROR: {
                snackbar.setText("Eroare de conectare").show();
            }
        }
    }
}
