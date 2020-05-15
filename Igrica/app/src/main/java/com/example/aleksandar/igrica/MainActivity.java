package com.example.aleksandar.igrica;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int requestcode = 1;
    private RadioButton min;
    private RadioButton max;
    private Button button;
    private Spinner spinner;
    private String tezina;
    private int tip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (savedInstanceState != null){
//            tezina = savedInstanceState.getString("tezina");
//            min.setChecked(savedInstanceState.getBoolean("checkmin"));
//            max.setChecked(savedInstanceState.getBoolean("checkmax"));
//        }

        min = findViewById(R.id.radio1);
        max = findViewById(R.id.radio2);
        button = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tezine,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tezina = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tezina = parent.getItemAtPosition(1).toString();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(min.isChecked()) tip = 0;
                if(max.isChecked()) tip = 1;
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("tezina", tezina);
                intent.putExtra("tip", tip);
                startActivityForResult(intent,requestcode);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        onSaveInstanceState(bundle);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == requestcode){
            if(resultCode == RESULT_OK){
                String rezultat = data.getStringExtra("rezultat");
                Toast.makeText(this,rezultat,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("checkmin",min.isChecked());
        outState.putBoolean("checkmax",max.isChecked());
        outState.putString("tezina", tezina);
    }
}
