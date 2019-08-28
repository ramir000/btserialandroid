package com.gg.reactionlight_0.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gg.reactionlight_0.MainActivity;
import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.adapter.StepAdapter;
import com.gg.reactionlight_0.bus.StepCreateSuccess;
import com.gg.reactionlight_0.bus.StepPagerView;
import com.gg.reactionlight_0.bus.StepsChange;
import com.gg.reactionlight_0.dataBase.viewmodel.StepViewModel;
import com.gg.reactionlight_0.dataBase.viewmodel.WorkViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StepMenuCreate extends Fragment {
    private static final String TAG = "StepMenuCreate";
    private FloatingActionButton buttonAddStep;
    private StepViewModel mStepViewModel;
    private WorkViewModel mWorkViewModel;
    private int mOwner;
    private final StepAdapter adapter = new StepAdapter();
    private List<HashMap> mMySteps = new LinkedList<>();
    private MediatorLiveData mMediatorLiveData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_step_menu, container, false);
        buttonAddStep = (FloatingActionButton) view.findViewById(R.id.button_add_Step);
        buttonAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new StepNew().show(getFragmentManager(), "StepNew");
                //((MainActivity)getActivity()).setViewPager(4);
            }
        });

        mStepViewModel = ViewModelProviders.of(this).get(StepViewModel.class);
        //Setup Recycler view in work_main_menu
        RecyclerView recyclerView = view.findViewById(R.id.StepRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        //Setup StepAdapter, container for "work" display.
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mMySteps = adapter.remove(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);



        mWorkViewModel = ViewModelProviders.of(this).get(WorkViewModel.class);
        mWorkViewModel = ViewModelProviders.of(this).get(WorkViewModel.class);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    public void onStepsChange(List<HashMap> Steps){
        int pos = 1;
        for(HashMap step: Steps){
            step.put("order",pos);
            //TODO: Datos a guardar por cada paso van aca o cuando se guarda el owner
        }

        EventBus.getDefault().post(new StepsChange(Steps));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StepCreateSuccess event) {
        HashMap data = event.getData();
        mMySteps.add(data);
        onStepsChange(mMySteps);
        adapter.setSteps(mMySteps);
    }
}
