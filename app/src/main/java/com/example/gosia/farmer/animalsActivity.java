package com.example.gosia.farmer;

import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class animalsActivity extends AppCompatActivity {

    Shaker shaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        changeTextbox();
        changeImages();
    }

    public void changeTextbox(){
        String rabbits = String.valueOf(Game.rabbits);
        String sheeps = String.valueOf(Game.sheeps);
        String pigs = String.valueOf(Game.pigs);
        String cows = String.valueOf(Game.cows);
        String horses = String.valueOf(Game.horses);
        String smallDogs = String.valueOf(Game.smallDog);
        String bigDogs = String.valueOf(Game.bigDog);


        TextView textView = (TextView)findViewById(R.id.rabbits);
        textView.setText(rabbits);

        TextView sheepText = (TextView)findViewById(R.id.ships);
        sheepText.setText(sheeps);

        TextView pigsText = (TextView)findViewById(R.id.pigs);
        pigsText.setText(pigs);

        TextView cowsText = (TextView)findViewById(R.id.cows);
        cowsText.setText(cows);

        TextView horseText = (TextView)findViewById(R.id.horses);
        horseText.setText(horses);

        TextView smallDogText = (TextView)findViewById(R.id.smallDog);
        smallDogText.setText(smallDogs);

        TextView bigDogText = (TextView)findViewById(R.id.bigDog);
        bigDogText.setText(bigDogs);

    }

    public void changeImages(){
        ImageView greenLastResult = (ImageView)findViewById(R.id.greenLastResult);
        ImageView redLastResult = (ImageView)findViewById(R.id.redLastResult);
        greenLastResult.setImageResource(Game.lastGreenResult);
        redLastResult.setImageResource(Game.lastRedResult);

    }


    public void rabbitinc(View view) {
        Game.rabbits++;
        changeTextbox();
    }

    public void rabbitdec(View view) {
        Game.rabbits--;
        changeTextbox();

    }

    public void horsedec(View view) {
        Game.horses--;
        changeTextbox();
    }

    public void horseinc(View view) {
        Game.horses++;
        changeTextbox();
    }

    public void piginc(View view) {
        Game.pigs++;
        changeTextbox();
    }

    public void pigdec(View view) {
        Game.pigs--;
        changeTextbox();
    }

    public void cowinc(View view) {
        Game.cows++;
        changeTextbox();
    }

    public void cowdec(View view) {
        Game.cows--;
        changeTextbox();
    }

    public void sheepinc(View view) {
        Game.sheeps++;
        changeTextbox();
    }

    public void sheepdec(View view) {
        Game.sheeps--;
        changeTextbox();
    }

    public void bigdoginc(View view) {
        Game.bigDog++;
        changeTextbox();
    }

    public void bigdogdec(View view) {
        Game.bigDog--;
        changeTextbox();
    }

    public void smalldogdec(View view) {
        Game.smallDog--;
        changeTextbox();
    }

    public void smalldoginc(View view) {
        Game.smallDog++;
        changeTextbox();
    }
    public void los(View view){
        Log.e("los", "entered");
        ImageView greenImage = (ImageView)findViewById(R.id.greenLastResult);
        ImageView redImage  = (ImageView)findViewById(R.id.redLastResult);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        shaker = new Shaker(getApplicationContext(), sensorManager, greenImage, redImage);


    }



}
