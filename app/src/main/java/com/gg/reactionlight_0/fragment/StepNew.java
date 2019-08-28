package com.gg.reactionlight_0.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.gg.reactionlight_0.MainActivity;
import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.bus.BackPressed;
import com.gg.reactionlight_0.bus.StepCreateSuccess;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.HashMap;

public class StepNew extends DialogFragment {
    private static final String TAG = "StepNew";
    private EditText mDelay;
    private EditText mTimelimit;
    private EditText mTo;
/*
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.step_new, container, false);
        setHasOptionsMenu(true);
        mDelay = (EditText) view.findViewById(R.id.Delay);
        mTimelimit = (EditText) view.findViewById(R.id.Timelimit);
        mTo = (EditText) view.findViewById(R.id.PodId);
        return view;
    }
*/
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return createSimpleDialog();
    }
    /**
     * Crea un diálogo de alerta sencillo
     * @return Nuevo diálogo
     */
    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.step_new, null);

        builder.setView(v);
        mDelay = (EditText) v.findViewById(R.id.Delay);
        mTimelimit = (EditText) v.findViewById(R.id.Timelimit);
        mTo = (EditText) v.findViewById(R.id.PodId);
        Button signin = (Button) v.findViewById(R.id.entrar_boton);
        signin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveStep();
                        dismiss();
                    }
                }

        );

        return builder.create();
    }

    private void saveStep() {
        Double delay = new Double(mDelay.getText().toString());
        Double timelimit = new Double(mTimelimit.getText().toString());
        String to = mTo.getText().toString();
        if (to.trim().isEmpty() || delay.toString().trim().isEmpty() || timelimit.toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "Please insert a delay and timelimit", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap data = new HashMap();
        data.put("delay", delay);
        data.put("Tmax", timelimit);
        data.put("to", to);
        onStepCreateSuccess(data);
        ((MainActivity)getActivity()).setViewPager(3);
    }

    public void onStepCreateSuccess(HashMap data) {
        EventBus.getDefault().post(new StepCreateSuccess(data));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BackPressed event) {
        Log.d(TAG, "onMessageEvent: " + TAG);
        if (((MainActivity) getActivity()).getCurrentFragmentTag().equals(TAG)) {
            ((MainActivity) getActivity()).setViewPager(0);
        }
    }

}
