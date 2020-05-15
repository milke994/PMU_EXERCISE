package com.example.android.podsetnik;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText mNaziv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mNaziv = findViewById(R.id.add_edittext);
    }

    public void onButtonClick(View view) {
        DialogFragment odabirDatuma = new OdabirDatuma();
        odabirDatuma.show(getSupportFragmentManager(), "odabirDatuma");
    }

    public void odabranDatum(int god, int mesec, int dan) {
        String godString = Integer.toString(god);
        String mesString = Integer.toString(mesec);
        String danString = Integer.toString(dan);
        String datum = danString + ". " + mesString + ". " + godString + ".";

        Intent rezultat = new Intent();
        rezultat.putExtra("naziv", mNaziv.getText().toString());
        rezultat.putExtra("datum", datum);
        setResult(RESULT_OK, rezultat);
        finish();
    }
}
