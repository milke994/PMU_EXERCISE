package com.example.aleksandar.capitals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView auto_comp;
    private Button btn;
    private String[] countries;
    private String[] capitals;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auto_comp = findViewById(R.id.auto_complete1);
        textView = findViewById(R.id.text_view);

        capitals = getResources().getStringArray(R.array.capitals);
        countries = getResources().getStringArray(R.array.countries);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,countries);
        auto_comp.setAdapter(adapter);

        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"nesto", Toast.LENGTH_SHORT).show();
                for(int i = 0; i < countries.length; i++){
                    if (auto_comp.getText().toString().equals(countries[i]))
                        textView.setText(capitals[i]);
                }
            }
        });
    }
}
