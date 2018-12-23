package com.example.gosia.farmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Game gra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.gra = new Game("helloGame");
        Intent intent = new Intent(this, animalsActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this, "onRestart()" , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        //Toast.makeText(this, "onPause()", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //Toast.makeText(this, "onStop()", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //Toast.makeText(this, "onDestroy()", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
    public void throwDice(View view) throws InterruptedException {
        //Intent intent = new Intent(".diceActivity");
        //startActivity(intent);
        //gra.zmienna = "new";
        //Toast.makeText(getApplicationContext(), gra.zmienna, Toast.LENGTH_SHORT).show();
        losuj();
    }
    public void exchangeButton(View view) {
        Toast.makeText(getApplicationContext(), "you pressed exchange button", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(".exchangeActivity");
        startActivity(intent);
    }
    public void animalButton (View view) {
        Toast.makeText(getApplicationContext(), "you pressed animal button", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, animalsActivity.class);
        startActivity(intent);
    }

    public void losuj(){
        redDice();
        greenDice();

    }
    void greenDice(){
        ImageView greenImage = (ImageView)findViewById(R.id.greenImageResult);
        Random random = new Random();
        int liczba = random.nextInt(12);
        if (liczba == 0) {
            greenImage.setImageResource(R.drawable.wolf);
            Game.lastGreenResult = R.drawable.wolf;
            if(Game.bigDog>0)
                Game.bigDog--;
            else {
                Game.rabbits = 0;
                Game.sheeps = 0;
                Game.pigs = 0;
                Game.cows = 0;
            }
        }
        else if (liczba == 1) {
            greenImage.setImageResource(R.drawable.cow);
            Game.lastGreenResult = R.drawable.cow;
        }
        else if (liczba == 2) {
            greenImage.setImageResource(R.drawable.pig);
            Game.lastGreenResult = R.drawable.pig;
        }
        else if (liczba >2 && liczba<6) {
            greenImage.setImageResource(R.drawable.sheep);
            Game.lastGreenResult = R.drawable.sheep;
        }

        else {
            greenImage.setImageResource(R.drawable.rabbit);
            Game.lastGreenResult = R.drawable.rabbit;
        }

    }
    void redDice(){
        ImageView redImage = (ImageView)findViewById(R.id.redImageResult);
        Random random = new Random();

        int liczba = random.nextInt(12);
        if (liczba == 0) {
            redImage.setImageResource(R.drawable.fox);
            Game.lastRedResult = R.drawable.fox;
            if(Game.smallDog > 0)
                Game.smallDog--;
            else
                Game.rabbits = 0;
        }
        else if (liczba == 1) {
            redImage.setImageResource(R.drawable.horse);
            Game.lastRedResult = R.drawable.horse;
        }
        else if (liczba > 1 && liczba<4) {
            redImage.setImageResource(R.drawable.pig);
            Game.lastRedResult = R.drawable.pig;
        }
        else if (liczba >3 && liczba<6) {
            redImage.setImageResource(R.drawable.sheep);
            Game.lastRedResult = R.drawable.sheep;
        }
        else {
            redImage.setImageResource(R.drawable.rabbit);
            Game.lastRedResult = R.drawable.rabbit;
        }

        }
}
