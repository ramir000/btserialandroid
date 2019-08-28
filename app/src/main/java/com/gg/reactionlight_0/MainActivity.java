package com.gg.reactionlight_0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.gg.reactionlight_0.adapter.PagerAdapter;
import com.gg.reactionlight_0.bus.BackPressed;
import com.gg.reactionlight_0.fragment.WorkMenuCreate;
import com.gg.reactionlight_0.fragment.MainMenu;
import com.gg.reactionlight_0.fragment.WorkMain;
import com.gg.reactionlight_0.fragment.BluetoothMain;
import com.gg.reactionlight_0.fragment.WorkSelected;
import com.gg.reactionlight_0.fragment.config.NonSwipeableViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private PagerAdapter mFragmentAdapter;
    private NonSwipeableViewPager mViewPager;
    private List<Object> mStored = new ArrayList<>();
    private Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Reaction Light");
        getSupportActionBar().hide();
        Log.d(TAG, "onCreate: Started.");
        mFragmentAdapter = new PagerAdapter(getSupportFragmentManager());
        //Setup the pager
            //setup Pager to be no Swipeable.
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.containter);
        mFragmentAdapter = setupViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new BackPressed());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                EventBus.getDefault().post(new BackPressed());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showBar(Boolean on){
        if(on)
            getSupportActionBar().show();
        else
            getSupportActionBar().hide();
    }

    private PagerAdapter setupViewPager(NonSwipeableViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainMenu(), "AppMainMenu");
        adapter.addFragment(new BluetoothMain(), "BlueToothMain");
        adapter.addFragment(new WorkMain(), "WorkMain");
        adapter.addFragment(new WorkMenuCreate(), "WorkMenuCreate");
        mViewPager.setCurrentItem(0);
        viewPager.setAdapter(adapter);
        return adapter;
    }

    public void setViewPager(int fragmentNumber) {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public String getCurrentFragmentTag() {
        return mFragmentAdapter.getTag(mViewPager.getCurrentItem());
    }
}
