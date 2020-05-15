package com.example.android.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Zivotni_Ciklus";

    private TextView rezultatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate");

        rezultatTextView = findViewById(R.id.text_view_result);

        if(savedInstanceState != null) {
            String rezultat = savedInstanceState.getString("REZULTAT");
            rezultatTextView.setText(rezultat);
            int boja = savedInstanceState.getInt("BOJA");
            rezultatTextView.setTextColor(boja);
        }

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.font_size,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String fontSizeString = parent.getItemAtPosition(position).toString();
                int fontSize = Integer.parseInt(fontSizeString);
                rezultatTextView.setTextSize(fontSize);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button subButton = findViewById(R.id.sub_button);
        subButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText broj1EditText = findViewById(R.id.edit_text_1);
                EditText broj2EditText = findViewById(R.id.edit_text_2);

                int broj1 = Integer.parseInt(broj1EditText.getText().toString());
                int broj2 = Integer.parseInt(broj2EditText.getText().toString());

                int rezultat = broj1 - broj2;
                String rezultatString = Integer.toString(rezultat);

                rezultatTextView.setText(rezultatString);

                //Toast.makeText(MainActivity.this, rezultatString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saberiDvaBroja(View view) {
        EditText broj1EditText = findViewById(R.id.edit_text_1);
        EditText broj2EditText = findViewById(R.id.edit_text_2);

        int broj1 = Integer.parseInt(broj1EditText.getText().toString());
        int broj2 = Integer.parseInt(broj2EditText.getText().toString());

        int rezultat = broj1 + broj2;
        String rezultatString = Integer.toString(rezultat);

        rezultatTextView.setText(rezultatString);

        //Toast.makeText(this, rezultatString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("REZULTAT", rezultatTextView.getText().toString());
        outState.putInt("BOJA", rezultatTextView.getCurrentTextColor());
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radio_zelena:
                if(checked) {
                    int zelenaBoja = ContextCompat.getColor(this,R.color.colorPrimary);
                    rezultatTextView.setTextColor(zelenaBoja);
                }
                break;
            case R.id.radio_crvena:
                if(checked) {
                    int crvenaBoja = ContextCompat.getColor(this,R.color.colorAccent);
                    rezultatTextView.setTextColor(crvenaBoja);
                }
                break;
        }
    }
}
