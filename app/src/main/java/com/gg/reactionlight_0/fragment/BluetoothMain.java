package com.gg.reactionlight_0.fragment;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import com.gg.reactionlight_0.controller.bt.bluetooth.manager.BluetoothManager;
import com.gg.reactionlight_0.controller.bt.fragment.BluetoothFragment;
import org.greenrobot.eventbus.Subscribe;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class BluetoothMain extends BluetoothFragment {

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private static final String TAG = "BlueTooth_Service";

    private ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();

    public BluetoothMain() {}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setMessageMode(BluetoothManager.MessageMode.Bytes);
    }

    public void serverType() {
        Log.d(TAG, "===> Start Server ! Your mac address : " + mBluetoothManager.getYourBtMacAddress());
        setTimeDiscoverable(BluetoothManager.BLUETOOTH_TIME_DICOVERY_120_SEC);
        selectSppServerMode();
    }

    public void stopServer() {
        disconnectClient();
    }

    public void startDiscovery() {
        super.mBluetoothManager.scanAllBluetoothDevice();
    }

    @Override
    public String setUUIDappIdentifier() {
        return "00001101-0000-1000-8000";
    }

    @Override
    public int myNbrClientMax() {
        return 7;
    }

    @Override
    public void onBluetoothDeviceFound(BluetoothDevice device) {
    }

    @Override
    @Subscribe
    public void onClientConnectionSuccess() {
    }

    @Override
    @Subscribe
    public void onClientConnectionFail() {
    }

    @Override
    @Subscribe
    public void onServeurConnectionSuccess(String adrr) {
    }

    @Override
    @Subscribe
    public void onServeurConnectionFail() {
    }

    @Override
    @Subscribe
    public void onBluetoothStartDiscovery() {
        Log.d(TAG, "===> Start discovering ! Your mac address : " + mBluetoothManager.getYourBtMacAddress());
    }

    @Override
    @Subscribe
    public void onBluetoothMsgStringReceived(String s) {
        Log.d(TAG, "===> onBluetoothMsgStringReceived(String s) : " + s);
    }

    @Override
    @Subscribe
    public void onBluetoothMsgObjectReceived(Object o) {
    }

    @Override
    @Subscribe
    public void onBluetoothMsgBytesReceived(byte[] bytes) {
        String str = new String(bytes, StandardCharsets.UTF_8);
        Log.d(TAG, "===> onBluetoothMsgStringReceived: " + str);
    }

    @Override
    @Subscribe
    public void onBluetoothNotAviable() {

    }

    @Override
    @Subscribe
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                // TODO stuff if u need
            }
        }
    }


}
