package com.example.aleksandar.igrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button[] buttons;
    private int t,tip;
    private int minbut, maxbut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        buttons = new Button[9];

        buttons[0] = findViewById(R.id.gameButton1);
        buttons[1] = findViewById(R.id.gameButton2);
        buttons[2] = findViewById(R.id.gameButton3);
        buttons[3] = findViewById(R.id.gameButton4);
        buttons[4] = findViewById(R.id.gameButton5);
        buttons[5] = findViewById(R.id.gameButton6);
        buttons[6] = findViewById(R.id.gameButton7);
        buttons[7] = findViewById(R.id.gameButton8);
        buttons[8] = findViewById(R.id.gameButton9);

        Intent intent = getIntent();
        String tezina = intent.getStringExtra("tezina");
        tip = intent.getIntExtra("tip",0);
        Random random = new Random();
        switch (tezina) {
            case "Lako":
                t = 10;
                break;
            case "Srednje":
                t = 100;
                break;
            case "Tesko":
                t = 1000;
                break;
        }
        int br = 0;
        int min = 1000;
        int max = 0;
        for(int i = 0; i < 9; i++){
            br = random.nextInt(t);
            buttons[i].setText(Integer.toString(br));
            if(br < min) {minbut = buttons[i].getId(); min = br;}
            if(br > max) {maxbut = buttons[i].getId(); max = br;}
        }

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[0].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[0].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[1].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[1].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[2].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[2].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[3].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[3].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[4].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[4].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[5].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[5].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[6].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[6].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[7].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[7].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });

        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rez = "Netacno ste odgovorili!";
                if(tip == 0){
                    if(buttons[8].getId() == minbut) rez = "Tacno ste odgovorili!";
                }
                if(tip == 1){
                    if(buttons[8].getId() == maxbut) rez = "Tacno ste odgovorili!";
                }
                Intent rezultat = new Intent();
                rezultat.putExtra("rezultat", rez);
                setResult(RESULT_OK,rezultat);
                finish();
            }
        });
    }
}
