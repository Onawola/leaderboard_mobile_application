package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.services.LeaderService;
import com.example.myapplication.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.drawable.gads);
        }

        Button create = (Button) findViewById(R.id.btn_submit);
        final EditText leaderFirstName = (EditText) findViewById(R.id.et_first_name);
        final EditText leaderLastName = (EditText) findViewById(R.id.et_last_name);
        final EditText leaderEmail = (EditText) findViewById(R.id.et_email_address);
        final EditText leaderProject = (EditText) findViewById(R.id.et_project);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(SubmitActivity.this);

                View v = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.dialog, null);
                builder.setCancelable(false);
                builder.setView(v);

                final AlertDialog alertDialog = builder.create();

                alertDialog.show();

                Button btn_yes;
                ImageButton btn_no;
                btn_yes = (Button) v.findViewById(R.id.btn_yes);
                btn_no = (ImageButton) v.findViewById(R.id.ib_cancel);
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                        LeaderService leaderService = ServiceBuilder.buildService(LeaderService.class);
                        Call<Void> request = leaderService.createLeader(
                                "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse",
                                leaderFirstName.getText().toString(),
                                leaderLastName.getText().toString(),
                                leaderEmail.getText().toString(),
                                leaderProject.getText().toString()
                        );

                        request.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> request, Response<Void> response) {
                                final AlertDialog.Builder builder1
                                        = new AlertDialog
                                        .Builder(SubmitActivity.this);

                                View v1 = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.dailog2, null);

                                builder1.setView(v1);
                                AlertDialog alertDialog1 = builder1.create();
                                alertDialog1.show();
                            }

                            @Override
                            public void onFailure(Call<Void> request, Throwable t) {
                                final AlertDialog.Builder builder2
                                        = new AlertDialog
                                        .Builder(SubmitActivity.this);

                                View v2 = LayoutInflater.from(SubmitActivity.this).inflate(R.layout.dailog3, null);

                                builder2.setView(v2);

                                AlertDialog alertDialog2 = builder2.create();

                                alertDialog2.show();
                            }
                        });
                    }
                });
                    }
                });


    }
}
