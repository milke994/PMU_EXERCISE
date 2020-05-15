package com.example.ma130584.threadprimer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    TextView tv1,tv2, prog;


    //AsyncTask moze da se koristi za procente odradjenog posla za to sluzi Integer
    public class Inkrementer extends AsyncTask<Void, Integer, Void>{

        private TextView textView, progress;


        public Inkrementer(TextView textView, TextView progress) {
            this.textView = textView;
            this.progress = progress;
        }

        @Override
        //povratna vrednost na osnovu potpisa kao i parametri
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                try {
//                    obrada zahteva dosta vremena
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i + 1);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            int count = Integer.parseInt(textView.getText().toString());
            textView.setText(Integer.toString(++count));
        }

        @Override
        //procenat koliko je posla zavrseno
        protected void onProgressUpdate(Integer... values) {
            progress.setText(values[0] * 10 + "%");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        tv1 = findViewById(R.id.textview1);
        tv2 = findViewById(R.id.textview2);
        prog = findViewById(R.id.progress);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Inkrementer(tv1, prog).execute();
                //mora nova nit da se ne bi blokirala GUI nit!
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            //obrada zahteva dosta vremena
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

                //Ne mozemo iz druge niti postavljati stvari na komponente GUI niti
//                        tv1.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                int count = Integer.parseInt(tv1.getText().toString());
//                                tv1.setText(Integer.toString(++count));
//                            }
//                        });
//                    }
//                }).start();
           }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count2 = Integer.parseInt(tv2.getText().toString());
                count2++;
                tv2.setText(Integer.toString(count2));
            }
        });
    }
}
