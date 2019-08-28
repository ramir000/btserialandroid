/*
package com.gg.reactionlight_0.controller.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;

public class BluetoothFactory {


    public static BluetoothAdapter getBluetoothAdapter() {
        BluetoothManager bm;
        Context.getSystemService(Context.BLUETOOTH_SERVICE);

        bm = (BluetoothManager) Context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter ba = bm.getAdapter();
        if (Build.VERSION.SDK_INT >= 23) {
            return ba;
        } else {
            return BluetoothAdapter.getDefaultAdapter();
        }
    }

}
*/