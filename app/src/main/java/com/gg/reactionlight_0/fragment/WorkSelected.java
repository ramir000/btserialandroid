package com.gg.reactionlight_0.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.bus.WorkFragmentSelected;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



public class WorkSelected extends DialogFragment {
    private static final String TAG = "WorkSelected";

    public static String getTAG() {
        return TAG;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.menu_edit_ejecutar, container, false);

        return view;
    }
    

}
