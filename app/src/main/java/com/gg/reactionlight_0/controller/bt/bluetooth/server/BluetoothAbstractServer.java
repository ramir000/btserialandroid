package com.gg.reactionlight_0.controller.bt.bluetooth.server;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;

import com.gg.reactionlight_0.controller.bt.bluetooth.BluetoothRunnable;
import com.gg.reactionlight_0.controller.bt.bluetooth.manager.BluetoothManager;
import com.gg.reactionlight_0.bus.ServeurConnectionFail;
import com.gg.reactionlight_0.bus.ServeurConnectionSuccess;

import java.util.UUID;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Rami on 16/06/2017.
 */
public abstract class BluetoothAbstractServer extends BluetoothRunnable {

    private static final String TAG = BluetoothAbstractServer.class.getSimpleName();

    private UUID mUUID;

    public BluetoothAbstractServer(BluetoothAdapter bluetoothAdapter, String uuiDappIdentifier, String adressMacClient, Activity activity, BluetoothManager.MessageMode messageMode) {
        super(bluetoothAdapter, uuiDappIdentifier, activity, messageMode);
        mClientAddress = adressMacClient;
    }

    @Override
    public void onConnectionSucess() {
        EventBus.getDefault().post(new ServeurConnectionSuccess(mClientAddress));
    }

    @Override
    public void onConnectionFail() {
        EventBus.getDefault().post(new ServeurConnectionFail(mClientAddress));
    }

}
