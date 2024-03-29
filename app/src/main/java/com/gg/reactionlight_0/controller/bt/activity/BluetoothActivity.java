package com.gg.reactionlight_0.controller.bt.activity;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;

import com.gg.reactionlight_0.controller.bt.bluetooth.manager.BluetoothManager;
import com.gg.reactionlight_0.bus.BluetoothCommunicatorBytes;
import com.gg.reactionlight_0.bus.BluetoothCommunicatorObject;
import com.gg.reactionlight_0.bus.BluetoothCommunicatorString;
import com.gg.reactionlight_0.bus.BondedDevice;
import com.gg.reactionlight_0.bus.ClientConnectionFail;
import com.gg.reactionlight_0.bus.ClientConnectionSuccess;
import com.gg.reactionlight_0.bus.ServeurConnectionFail;
import com.gg.reactionlight_0.bus.ServeurConnectionSuccess;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Rami MARTIN on 13/04/2014.
 */
public abstract class BluetoothActivity extends Activity {

    protected BluetoothManager mBluetoothManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBluetoothManager = new BluetoothManager(this);
        checkBluetoothAviability();
        mBluetoothManager.setUUIDappIdentifier(setUUIDappIdentifier());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        mBluetoothManager.setNbrClientMax(myNbrClientMax());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        closeAllConnexion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothManager.REQUEST_DISCOVERABLE_CODE) {
            if (resultCode == BluetoothManager.BLUETOOTH_REQUEST_REFUSED) {
            } else if (resultCode == BluetoothManager.BLUETOOTH_REQUEST_ACCEPTED) {
                onBluetoothStartDiscovery();
            } else {
            }
        }
    }

    public void closeAllConnexion() {
        mBluetoothManager.closeAllConnexion();
    }

    public void checkBluetoothAviability() {
        if (!mBluetoothManager.checkBluetoothAviability()) {
            onBluetoothNotAviable();
        }
    }

    public void setTimeDiscoverable(int timeInSec) {
        mBluetoothManager.setTimeDiscoverable(timeInSec);
    }

    public void startDiscovery() {
        mBluetoothManager.startDiscovery();
    }

    public void scanAllBluetoothDevice() {
        mBluetoothManager.scanAllBluetoothDevice();
    }

    public void disconnectClient() {
        mBluetoothManager.disconnectClient(true);
    }

    public void disconnectServer() {
        mBluetoothManager.disconnectServer(true);
    }

    public void createServeur(String address) {
        mBluetoothManager.createServeur(address, false);
    }

    public void createSppServeur(String address) {
        mBluetoothManager.createServeur(address, true);
    }

    public void selectServerMode() {
        mBluetoothManager.selectServerMode();
    }

    public void selectClientMode() {
        mBluetoothManager.selectClientMode();
    }

    public BluetoothManager.TypeBluetooth getTypeBluetooth() {
        return mBluetoothManager.mType;
    }

    public BluetoothManager.TypeBluetooth getBluetoothMode() {
        return mBluetoothManager.mType;
    }

    public void createClient(String addressMac) {
        mBluetoothManager.createClient(addressMac);
    }

    public void setMessageMode(BluetoothManager.MessageMode messageMode) {
        mBluetoothManager.setMessageMode(messageMode);
    }

    public void sendMessageStringToAll(String message) {
        mBluetoothManager.sendStringMessageForAll(message);
    }

    public void sendMessageString(String adressMacTarget, String message) {
        mBluetoothManager.sendStringMessage(adressMacTarget, message);
    }

    public void sendMessageObjectToAll(Object message) {
        mBluetoothManager.sendObjectForAll(message);
    }

    public void sendMessageObject(String adressMacTarget, Object message) {
        mBluetoothManager.sendObject(adressMacTarget, message);
    }

    public void sendMessageBytesForAll(byte[] message) {
        mBluetoothManager.sendBytesForAll(message);
    }

    public void sendMessageBytes(String adressMacTarget, byte[] message) {
        mBluetoothManager.sendBytes(adressMacTarget, message);
    }

    public boolean isConnected() {
        return mBluetoothManager.isConnected;
    }

    public abstract String setUUIDappIdentifier();

    public abstract int myNbrClientMax();

    public abstract void onBluetoothDeviceFound(BluetoothDevice device);

    public abstract void onClientConnectionSuccess();

    public abstract void onClientConnectionFail();

    public abstract void onServeurConnectionSuccess();

    public abstract void onServeurConnectionFail();

    public abstract void onBluetoothStartDiscovery();

    public abstract void onBluetoothMsgStringReceived(String message);

    public abstract void onBluetoothMsgObjectReceived(Object message);

    public abstract void onBluetoothMsgBytesReceived(byte[] message);

    public abstract void onBluetoothNotAviable();

    public void onEventMainThread(BluetoothDevice device) {
        onBluetoothDeviceFound(device);
        createServeur(device.getAddress());
    }

    public void onEventMainThread(ClientConnectionSuccess event) {
        mBluetoothManager.isConnected = true;
        onClientConnectionSuccess();
    }

    public void onEventMainThread(ClientConnectionFail event) {
        mBluetoothManager.isConnected = false;
        onClientConnectionFail();
    }

    public void onEventMainThread(ServeurConnectionSuccess event) {
        mBluetoothManager.isConnected = true;
        mBluetoothManager.onServerConnectionSuccess(event.mClientAdressConnected);
        onServeurConnectionSuccess();
    }

    public void onEventMainThread(ServeurConnectionFail event) {
        mBluetoothManager.onServerConnectionFailed(event.mClientAdressConnectionFail);
        onServeurConnectionFail();
    }

    public void onEventMainThread(BluetoothCommunicatorString event) {
        onBluetoothMsgStringReceived(event.mMessageReceive);
    }

    public void onEventMainThread(BluetoothCommunicatorObject event) {
        onBluetoothMsgObjectReceived(event.mObject);
    }

    public void onEventMainThread(BluetoothCommunicatorBytes event) {
        onBluetoothMsgBytesReceived(event.mBytesReceive);
    }

    public void onEventMainThread(BondedDevice event) {
        //mActManager.sendMessage("BondedDevice");
    }

}
