package com.example.android.podsetnik;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MojAdapter mAdapter;
    private ArrayList<Podsetnik> mPodsetnici;
    private RecyclerView mRecyclerView;

    private final int DODAVANJE_PODSETNIKA = 245;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPodsetnici = new ArrayList<>();

        mAdapter = new MojAdapter(this, mPodsetnici);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        postaviPodatke();

    }

    public void onFabClick(View view) {
        Intent eksplicitniIntent = new Intent(this, AddActivity.class);
        startActivityForResult(eksplicitniIntent, DODAVANJE_PODSETNIKA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DODAVANJE_PODSETNIKA) {
            if(resultCode == RESULT_OK) {
                String naziv = data.getStringExtra("naziv");
                String datum = data.getStringExtra("datum");
                mPodsetnici.add(new Podsetnik(naziv, datum));
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void postaviPodatke() {
        String[] nazivi = getResources().getStringArray(R.array.nazivi);
        String[] datumi = getResources().getStringArray(R.array.datumi);

        for(int i = 0; i < nazivi.length; i++) {
            mPodsetnici.add(new Podsetnik(nazivi[i], datumi[i]));
        }

        mAdapter.notifyDataSetChanged();
    }
}
