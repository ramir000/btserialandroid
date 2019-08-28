package com.gg.reactionlight_0.controller;

import com.gg.reactionlight_0.MainActivity;
import com.gg.reactionlight_0.controller.bt.bluetooth.manager.BluetoothManager;

import java.util.List;

public class AutoWorkController extends WorkController {

    private List<List<String>> mSec;

    public AutoWorkController(String mName, String mDescrip, BluetoothManager mBluetoothManager, List<String> mMacClient, List<List<String>> mSec) {
        super(mName, mDescrip, mBluetoothManager, mMacClient);
        this.mSec = mSec;
    }


    @Override
    public void run() {
        for (List<String> s : mSec) {
            try {
                Thread.sleep(new Long(s.get(0)));
                mBluetoothManager.sendStringMessage(s.get(1), s.get(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
