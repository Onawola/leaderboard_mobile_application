package com.example.myapplication.services;

import com.example.myapplication.models.LearningLeader;
import com.example.myapplication.models.SkillIQLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface LeaderService {
    @GET("/api/hours")
    Call<List<LearningLeader>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<SkillIQLeader>> getSkillIQLeaders();

    @FormUrlEncoded
    @POST
    Call<Void> createLeader(
            @Url String altUrl,
            @Field("entry.1824927963") String emailAddress,
            @Field("entry.1877115667") String name,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String linkToProject

    );
}
