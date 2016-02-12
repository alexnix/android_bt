package com.example.m17336.bt_to_pc.bluetooth_specifics;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.util.UUID;


public class ConnectThread extends Thread {
    private BluetoothSocket socket;
    private Handler handler;

    public ConnectThread(BluetoothDevice device, Handler handler){
        this.handler = handler;

        try {
            socket = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        } catch (IOException e) {
            handler.obtainMessage(EventsHandeling.ERROR_RFCOMM).sendToTarget();
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            socket.connect();
            manageConnectedSocket();
        } catch (IOException e) {
            handler.obtainMessage(EventsHandeling.ERROR_CONNECT).sendToTarget();
            e.printStackTrace();
        }
    }

    private void manageConnectedSocket() {
        CommunicationThread com = new CommunicationThread(socket, handler);
        handler.obtainMessage(EventsHandeling.SOCKET_CONNECTED, com).sendToTarget();
    }
}
