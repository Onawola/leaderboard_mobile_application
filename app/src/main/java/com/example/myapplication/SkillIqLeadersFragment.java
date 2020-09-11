package com.example.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.SkillIQLeader;
import com.example.myapplication.services.LeaderService;
import com.example.myapplication.services.ServiceBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqLeadersFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.skill_iq_leaders_list, container, false);

        final Context context = getContext();

        final RecyclerView skillRecyclerView = (RecyclerView) root.findViewById(R.id.rv_skill_iq_leaders);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        skillRecyclerView.setLayoutManager(linearLayoutManager);
        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);
        Call<List<SkillIQLeader>> skillRequest = leaderService.getSkillIQLeaders();

        skillRequest.enqueue(new Callback<List<SkillIQLeader>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeader>> request, Response<List<SkillIQLeader>> response) {
                if(response.isSuccessful()){
                    skillRecyclerView.setAdapter(new SqillIQRecyclerViewAdapter(response.body()));
                } else if(response.code() == 401) {
                    Toast.makeText(context, "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<SkillIQLeader>> request, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(context, "A connection error occured", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }
}
