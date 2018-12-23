package com.example.gosia.farmer;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class diceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
    }

    public void losuj(View view){

        TextView greenText = (TextView)findViewById(R.id.greenResult);
        greenText.setText(greenDice());
        TextView redText = (TextView)findViewById(R.id.redResult);
        redText.setText(String.valueOf(redDice()));
        if(greenText.getText() == redText.getText())
        {
            if(greenText.getText() == "królik")
                Game.rabbits++;
            if(greenText.getText() == "owca")
                Game.sheeps++;
            if(greenText.getText() == "świnia")
                Game.pigs++;
        }
    }
    String greenDice(){
        Random random = new Random();
        int liczba = random.nextInt(12);
        if (liczba == 0)
            return "wilk";
        else if (liczba == 1)
            return "krowa";
        else if (liczba == 2)
            return "świnia";
        else if (liczba >2 && liczba<6)
            return "owca";
        else
            return "królik";


    }
    String redDice(){
        Random random = new Random();
        int liczba = random.nextInt(12);
        if (liczba == 0)
            return "lis";
        else if (liczba == 1)
            return "koń";
        else if (liczba > 1 && liczba<4)
            return "świnia";
        else if (liczba >3 && liczba<6)
            return "owca";
        else
            return "królik";
    }
}
