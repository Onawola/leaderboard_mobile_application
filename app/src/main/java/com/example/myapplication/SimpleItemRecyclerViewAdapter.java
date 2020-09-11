package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.LearningLeader;

import java.util.List;

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<LearningLeader> mValues;

    public SimpleItemRecyclerViewAdapter(List<LearningLeader> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.learning_leaders_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTvAddress.setText(mValues.get(position).getName());
        StringBuilder sb = new StringBuilder();
        sb.append(mValues.get(position).getHours() + " learning hours, " + mValues.get(position).getCountry() + '.');
        String query = sb.toString();
        holder.mTvAddress.setText(query);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvName;
        public final TextView mTvAddress;

        public ViewHolder(View view) {
            super(view);
            mTvName = (TextView) view.findViewById(R.id.tvName);
            mTvAddress = (TextView) view.findViewById(R.id.tvAddress);
        }
    }
}