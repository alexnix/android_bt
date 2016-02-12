package com.example.m17336.bt_to_pc.bluetooth_specifics;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import android.os.Handler;

/**
 * Created by m17336 on 2/5/2016.
 */
public class CommunicationThread extends Thread {
    private BluetoothSocket socket;
    private Handler handler;
    private InputStream is;
    private OutputStream os;

    public CommunicationThread(BluetoothSocket socket, Handler handler) {
        this.socket = socket;
        this.handler = handler;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            handler.obtainMessage(EventsHandeling.ERROR_OBTAIN_STREAMS).sendToTarget();
        }
    }

    public void run(){
        byte[] buffer = new byte[1024];
        int bytes;

        while( true ){
            try {
                bytes = is.read(buffer);
                String data = new String(buffer, 0, bytes);
                handler.obtainMessage(EventsHandeling.DATA_RECEIVED, data).sendToTarget();
            } catch (IOException e){
                handler.obtainMessage(EventsHandeling.ERROR_COMMUNICATION).sendToTarget();
            }
        }
    }

    public void write(String message){
        String m = message + '\n';
        byte[] msgBuffer = m.getBytes();
        try {
            os.write(msgBuffer);
        } catch (IOException e) {}
    }
}
