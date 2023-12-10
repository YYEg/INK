package com.example.bt_def.bluetooth

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.UUID

class ConnectThread(private val device: BluetoothDevice) : Thread() {
    private val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    private var mSocket: BluetoothSocket? = null
    init {
        try {
            mSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
            Log.d("MyLog", "init")
        } catch (e: IOException){

        } catch (se: SecurityException){
            Log.e("MyLog", "Socket creation failed: ${se.message}")
        }
    }

    override  fun run() {
        try {
            Log.d("MyLog", "Connecting...")
            mSocket?.connect()
            Log.d("MyLog", "Connected")
        } catch (e: IOException){
            Log.e("MyLog", "Connection failed: ${e.message}")
        } catch (se: SecurityException){

        }
    }

    fun closeConnection(){
        try{
            mSocket?.close()
        } catch (e: IOException){

        } catch (se: SecurityException){

        }
    }
}