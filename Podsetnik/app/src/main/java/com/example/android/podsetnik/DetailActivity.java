package com.example.android.podsetnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView mNaziv;
    private TextView mDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent mojIntent = getIntent();

        mNaziv = findViewById(R.id.detail_naziv_textview);
        mNaziv.setText(mojIntent.getStringExtra("naziv"));

        mDatum = findViewById(R.id.detail_datum_textview);
        mDatum.setText(mojIntent.getStringExtra("datum"));
    }
}
