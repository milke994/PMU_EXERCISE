package com.example.android.capitals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView editText;
    private TextView textView;

    private String[] countries;
    private String[] capitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countries = getResources().getStringArray(R.array.countries);
        capitals = getResources().getStringArray(R.array.capitals);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, countries);
        editText.setAdapter(adapter);
    }

    public void onButtonClicked(View view) {
        for(int i = 0; i < countries.length; i++) {
            if(editText.getText().toString().equals(countries[i])){
                textView.setText(capitals[i]);
            }
        }
    }
}
