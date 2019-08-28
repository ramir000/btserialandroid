package com.gg.reactionlight_0.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.gg.reactionlight_0.MainActivity;
import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.bus.BackPressed;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainMenu extends Fragment {

    private static final String TAG = "AppMainMenu";
    private CardView btnNavCon;
    private CardView btnNavPly;
    private CardView btnNavAct;
    private CardView btnNavStd;
    private GridLayout mainGrid;

    public static String getTAG() {
        return TAG;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.app_main_menu, container, false);
        ((MainActivity)getActivity()).showBar(false);
        btnNavCon = (CardView) view.findViewById(R.id.btnNavCon);
        btnNavPly = (CardView) view.findViewById(R.id.btnNavPly);
        btnNavAct = (CardView) view.findViewById(R.id.btnNavAct);
        btnNavStd = (CardView) view.findViewById(R.id.btnNavStd);

        btnNavCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });

        btnNavPly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });

        btnNavAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setViewPager(2);
            }
        });

        btnNavStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });

        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BackPressed event) {
        if (((MainActivity) getActivity()).getCurrentFragmentTag().equals(TAG))
            ((MainActivity) getActivity()).finish();
    }

}
