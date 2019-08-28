package com.gg.reactionlight_0.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.gg.reactionlight_0.MainActivity;
import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.adapter.PagerAdapter;
import com.gg.reactionlight_0.bus.BackPressed;
import com.gg.reactionlight_0.bus.StepCreateSuccess;
import com.gg.reactionlight_0.bus.StepPagerView;
import com.gg.reactionlight_0.bus.StepSaveSuccess;
import com.gg.reactionlight_0.bus.StepsChange;
import com.gg.reactionlight_0.bus.WorkSaveSuccess;
import com.gg.reactionlight_0.dataBase.entity.Step;
import com.gg.reactionlight_0.dataBase.viewmodel.StepViewModel;
import com.gg.reactionlight_0.fragment.config.NonSwipeableViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

public class WorkMenuCreate extends Fragment {
    private static final String TAG = "WorkMenuCreate";
    private EditText editTextName;
    private EditText editTextDescription;
    private Switch mAutomatic;
    private Switch mRandom;
    private Switch mMemoria;
    private TextView mPasos;
    private StepViewModel mStepViewModel;

    private PagerAdapter mPagerStepAdapter;
    private NonSwipeableViewPager mViewPager;
    private List<HashMap> mMySteps;

    public static String getTAG() {
        return TAG;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_work_menu, container, false);
        setHasOptionsMenu(true);
        editTextName = view.findViewById(R.id.WorkName);
        editTextDescription = view.findViewById(R.id.WorkDesc);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        mPagerStepAdapter = new PagerAdapter(getFragmentManager());
        //Setup the pager
         //setup Pager to be no Swipeable.
        mViewPager = (NonSwipeableViewPager) view.findViewById(R.id.StepContainer);
        mPagerStepAdapter = setupViewPager(mViewPager);
        //
        mStepViewModel = ViewModelProviders.of(this).get(StepViewModel.class);
        //
        mAutomatic = (Switch) view.findViewById(R.id.Automatic);
        mRandom = (Switch) view.findViewById(R.id.random);
        mMemoria = (Switch) view.findViewById(R.id.memory);
        mPasos = (TextView) view.findViewById(R.id.Pasos);

        mAutomatic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if(!mRandom.isChecked()) {
                        mViewPager.setVisibility(View.VISIBLE);
                        mPasos.setVisibility(View.VISIBLE);
                    }
                    mMemoria.setVisibility(View.VISIBLE);
                    mRandom.setVisibility(View.VISIBLE);
                } else {
                    mViewPager.setVisibility(View.GONE);
                    mPasos.setVisibility(View.INVISIBLE);
                    mMemoria.setVisibility(View.INVISIBLE);
                    mRandom.setVisibility(View.INVISIBLE);
                }
            }
        });
        //
        mRandom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mViewPager.setVisibility(View.INVISIBLE);
                    mPasos.setVisibility(View.INVISIBLE);
                } else {
                    mViewPager.setVisibility(View.VISIBLE);
                    mPasos.setVisibility(View.VISIBLE);
                }
            }
        });
        //
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_work, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_work:
                saveWork();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveWork() {
        String name = editTextName.getText().toString();
        String desc = editTextDescription.getText().toString();
        if (name.trim().isEmpty() || desc.trim().isEmpty()) {
            Toast.makeText(getActivity(), "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap data = new HashMap();
        data.put("name", name);
        data.put("desc", desc);
        data.put("steps", mMySteps);
        data.put(("automatic"),mAutomatic.isChecked());
        data.put(("random"),mRandom.isChecked());
        data.put(("memoria"),mMemoria.isChecked());
        onWorkSaveSucess(data);
        ((MainActivity) getActivity()).setViewPager(2);
    }



    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private PagerAdapter setupViewPager(NonSwipeableViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        adapter.addFragment(new StepMenuCreate(),"StepMenuCreate");
        adapter.addFragment(new StepNew(),"StepNew");
        viewPager.setAdapter(adapter);
        return adapter;
    }



    public void onWorkSaveSucess(HashMap data) {
        EventBus.getDefault().post(new WorkSaveSuccess(data));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BackPressed event) {
        if (((MainActivity) getActivity()).getCurrentFragmentTag().equals(TAG))
            ((MainActivity) getActivity()).setViewPager(2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StepPagerView event) {
        setViewPager(event.getPosition());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StepSaveSuccess event) {
        HashMap data = event.getData();
        int Owner = (int) data.get("owner");
        Double delay = (Double) data.get("delay");
        Double timelimit = (Double) data.get("timelimit");
        int order = (int) data.get("order");
        String to = (String) data.get("to");
        Step step = new Step(Owner,delay,timelimit,order,to,"xxx");
        mStepViewModel.insert(step);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(StepsChange event) {
        mMySteps = event.getData();
    }
}
