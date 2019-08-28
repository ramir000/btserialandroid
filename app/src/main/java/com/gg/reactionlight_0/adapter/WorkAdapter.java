package com.gg.reactionlight_0.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gg.reactionlight_0.R;
import com.gg.reactionlight_0.bus.WorkFragmentSelected;
import com.gg.reactionlight_0.dataBase.entity.Step;
import com.gg.reactionlight_0.dataBase.entity.WorkWithSteps;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.NoteHolder> {
    private List<WorkWithSteps> works = new ArrayList<>();
    private static final String TAG = "WorkAdapter";

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_item, parent, false);
        return new NoteHolder(itemView);
    }

    public WorkWithSteps remove(int pos) {
        WorkWithSteps WWS = works.get(pos);
        works.remove(pos);
        notifyDataSetChanged();
        return WWS;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        WorkWithSteps currentWork = works.get(position);
        String stepsOrder = "";
        holder.textViewTitle.setText(currentWork.work.getName());
        holder.textViewDescription.setText(currentWork.work.getDesc());
        if(currentWork.work.isAutomatic()) {
            holder.textViewMode.setText("Auto...");
            for(Step s: currentWork.steps)
                stepsOrder = stepsOrder +"Pod N°: "+s.getTo()+"/ ";
            holder.textViewStepOrder.setText(stepsOrder);
        }
        else
            holder.textViewMode.setText("Manu...");
    }

    @Override
    public int getItemCount() {
        return works.size();
    }

    public void setWorks(List<WorkWithSteps> works) {
        this.works = works;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewMode;
        private TextView textViewStepOrder;

        public NoteHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    EventBus.getDefault().post(new WorkFragmentSelected());
                }
            });
            textViewStepOrder = itemView.findViewById(R.id.steps_order);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewMode = itemView.findViewById(R.id.text_view_mode);
        }
    }
}
