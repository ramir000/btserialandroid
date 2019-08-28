package com.gg.reactionlight_0.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.dataBase.entity.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.NoteHolder> {
    private List<HashMap> steps = new ArrayList<>();
    private static final String TAG = "StepAdapter";

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        HashMap currentStep = steps.get(position);
        String mTo= (String)currentStep.get("to");
        Log.d(TAG, "onBindViewHolder: " + currentStep.get("delay"));
        holder.textViewPosic.setText(""+ position);
        holder.textViewDelay.setText(currentStep.get("delay").toString());
        holder.textViewPod.setText(mTo);
        holder.textViewTmax.setText(currentStep.get("Tmax").toString());
    }

    @Override
    public int getItemCount() {
        return this.steps.size();
    }

    public void setSteps(List<HashMap> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }

    public List<HashMap> remove(int position){
        steps.remove(position);
        return steps;
    }
    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewPosic;
        private TextView textViewDelay;
        private TextView textViewPod;
        private TextView textViewTmax;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewPosic = itemView.findViewById(R.id.Posid);
            textViewDelay = itemView.findViewById(R.id.delay_text_view);
            textViewPod = itemView.findViewById(R.id.PdId);
            textViewTmax = itemView.findViewById(R.id.Tmax);
        }
    }
}
