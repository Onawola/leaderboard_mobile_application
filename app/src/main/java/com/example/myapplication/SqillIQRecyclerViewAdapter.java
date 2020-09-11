package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.LearningLeader;
import com.example.myapplication.models.SkillIQLeader;

import java.util.List;

public class SqillIQRecyclerViewAdapter
        extends RecyclerView.Adapter<SqillIQRecyclerViewAdapter.ViewHolder> {

    private final List<SkillIQLeader> mValues;

    public SqillIQRecyclerViewAdapter(List<SkillIQLeader> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skill_iq_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mSkillName.setText(mValues.get(position).getName());
        StringBuilder sb = new StringBuilder();
        sb.append(mValues.get(position).getScore() + " skill IQ Score, " + mValues.get(position).getCountry() + '.');
        String query = sb.toString();
        holder.mTvSkillAddress.setText(query);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mSkillName;
        public final TextView mTvSkillAddress;

        public ViewHolder(View view) {
            super(view);
            mSkillName = (TextView) view.findViewById(R.id.tvSkillName);
            mTvSkillAddress = (TextView) view.findViewById(R.id.tvSkillAddress);
        }
    }
}