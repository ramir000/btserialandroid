package com.gg.reactionlight_0.controller;

import com.gg.reactionlight_0.controller.bt.bluetooth.manager.BluetoothManager;

import java.util.List;

public abstract class WorkController implements Runnable {
    protected String mName;
    protected String mDescrip;
    protected BluetoothManager mBluetoothManager;
    protected List<String> mMacClient;

    public WorkController(String mName, String mDescrip, BluetoothManager mBluetoothManager, List<String> mMacClient) {
        this.mName = mName;
        this.mDescrip = mDescrip;
        this.mBluetoothManager = mBluetoothManager;
        this.mMacClient = mMacClient;
    }

    @Override
    public abstract void run();
}
