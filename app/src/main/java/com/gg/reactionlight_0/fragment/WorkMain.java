package com.gg.reactionlight_0.fragment;


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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gg.reactionlight_0.MainActivity;
import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.adapter.WorkAdapter;
import com.gg.reactionlight_0.bus.BackPressed;
import com.gg.reactionlight_0.bus.WorkFragmentSelected;
import com.gg.reactionlight_0.bus.WorkSaveSuccess;
import com.gg.reactionlight_0.dataBase.entity.Step;
import com.gg.reactionlight_0.dataBase.entity.Work;
import com.gg.reactionlight_0.dataBase.entity.WorkWithSteps;
import com.gg.reactionlight_0.dataBase.viewmodel.StepViewModel;
import com.gg.reactionlight_0.dataBase.viewmodel.WorkViewModel;
import com.gg.reactionlight_0.dataBase.viewmodel.WorkWithStepsViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class WorkMain extends Fragment {

    private static final String TAG = "WorkMain";
    private WorkWithStepsViewModel mWorkWithStepsViewModel;
    private WorkViewModel mWorkViewModel;
    private List<Work> mWorks = new ArrayList<>();
    private StepViewModel mStepViewModel;
    private int mLast;

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
        //Get Actual view layout
        View view = inflater.inflate(R.layout.work_main_menu, container, false);
        //Setup Button: "Add Work"
        FloatingActionButton buttonAddWork = (FloatingActionButton) view.findViewById(R.id.button_add_work);
        buttonAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) (getActivity())).setViewPager(3);
            }
        });

        //Setup Recycler view in work_main_menu
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        //Setup WorkAdapter, container for "work" display.
        final WorkAdapter adapter = new WorkAdapter();
        recyclerView.setAdapter(adapter);
        mWorkViewModel = ViewModelProviders.of(this).get(WorkViewModel.class);
        mStepViewModel = ViewModelProviders.of(this).get(StepViewModel.class);
        mWorkWithStepsViewModel = ViewModelProviders.of(this).get(WorkWithStepsViewModel.class);
        mWorkWithStepsViewModel.getAllWorkWithSteps().observe(this, new Observer<List<WorkWithSteps>>() {
            @Override
            public void onChanged(@Nullable List<WorkWithSteps> Works) {
                if (Works == null)
                    Works = new LinkedList<>();
                adapter.setWorks(Works);
                if(Works.size() > 0) {
                    mLast = Works.get(0).work.getId() + 1;
                    Log.d(TAG, "onChanged: " + mLast);
                }
            }
        });

        //Add Swipe for delete Work
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                WorkWithSteps WWS = adapter.remove(viewHolder.getAdapterPosition());
                mWorkViewModel.delete(WWS.work);
                for (Step s : WWS.steps)
                    mStepViewModel.delete(s);
            }
        }).attachToRecyclerView(recyclerView);

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).showBar(true);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WorkSaveSuccess event) {
        HashMap data = event.getData();
        String title = (String) data.get("name");
        String description = (String) data.get("desc");
        List<HashMap> mMySteps = (List<HashMap>) data.get("steps");
        List<Step> StepsList = new LinkedList<>();
        if((Boolean)data.get("automatic") || (Boolean)data.get("memoria"))
            for (HashMap stepH : mMySteps) {
                stepH.put("owner", mLast);
                Step step = new Step((int) stepH.get("owner"), (Double) stepH.get("delay"), (Double) stepH.get("Tmax"),
                        (int) stepH.get("order"), (String) stepH.get("to"), (String) stepH.get("data"));
                StepsList.add(step);
            }
        Work work = new Work(title, description,(Boolean)data.get("automatic"),(Boolean)data.get("random"),(Boolean)data.get("memoria"));
        mWorkViewModel.insert(work,StepsList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BackPressed event) {
        if (((MainActivity) getActivity()).getCurrentFragmentTag().equals(TAG)) {
            ((MainActivity) getActivity()).setViewPager(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WorkFragmentSelected event) {
        new WorkSelected().show(getFragmentManager(), "WorkSelected");
    }
}
