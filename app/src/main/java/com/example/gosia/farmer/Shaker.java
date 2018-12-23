package com.example.gosia.farmer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.SENSOR_SERVICE;
import static java.lang.StrictMath.abs;
import static java.lang.System.currentTimeMillis;

public class Shaker implements SensorEventListener {
    SensorManager sensorManager= null;
    Sensor accelerometer = null;
    Context context;
    ImageView imageGreen;
    ImageView imageRed;

    int lastGreenResult = 0;
    Random random;
    public static Boolean shaking = true;

    long time1 = 0;
    long time2 = 0;
    long lastUpdate = 0;
    float xCurrent, yCurrent, zCurrent;
    float xPrevious, yPrevious, zPrevious;
    int speed;

    Boolean finished;
    Shaker(Context context, SensorManager sensorManager, ImageView image1, ImageView image2){
        shaking = true;
        Log.e("shaker", "entered");
        this.context = context;
        this.random = new Random();
        this.sensorManager = sensorManager;
        this.imageGreen = image1;
        this.imageRed = image2;
        this.finished = false;
        time1 = currentTimeMillis();
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI); //zobaczyć, czym to się różni to ostatnie

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (shaking == true) {
            time2 = currentTimeMillis();
            xCurrent = sensorEvent.values[0];
            yCurrent = sensorEvent.values[1];
            zCurrent = sensorEvent.values[2];
//            //        Log.e("Sensor", "x:\t" + sensorEvent.values[0]);
//            //        Log.e("Sensor", "y:\t" + sensorEvent.values[1]);
//            //        Log.e("Sensor", "z:\t" + sensorEvent.values[2]);
            float change = (xCurrent + yCurrent + zCurrent - xPrevious - yPrevious - zPrevious);
//            Log.e("Sensor", "change" + change);
            xPrevious = xCurrent;
            yPrevious = yCurrent;
            zPrevious = zCurrent;
            if(change > 0 && abs(change) < 0.8){
                speed = 0;
            }
            else if(abs(change)>=0.8 && abs(change) <2){
                speed = 1;
                if(time2 - lastUpdate > 500){//co 500 milisekund
                    changeImage();
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }
            }
            else if(abs(change)>=2 && abs(change) <3){
                speed = 2;
                if(time2-lastUpdate > 325){
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    changeImage();
                    Log.e("Sensor", "speed" + speed);
                }

            }
            else if(abs(change)>=3 && abs(change) <4){
                speed = 3;
                if(time2-lastUpdate > 200){
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    changeImage();
                    Log.e("Sensor", "speed" + speed);
                }
            }
            else if(abs(change)>=4 && abs(change) <5){
                speed = 4;
                if(time2-lastUpdate > 100){
                    changeImage();
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }
            }

            else if(abs(change)>=5){
                speed = 5;
                if(time2-lastUpdate > 50){
                    changeImage();
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }
            }

            if(abs(change) < 1.5){
                if(time2-time1>500) {
                    Toast.makeText(context, "stop Shaking", Toast.LENGTH_SHORT).show();
                    shaking = false;
                    Game.lastGreenResult = lastGreenResult;
                    this.finished = true;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void changeImage(){
        int value = random.nextInt(12);
        if (value <6 ){
            imageGreen.setImageResource(R.drawable.rabbit);
            lastGreenResult = 1;
        }
        if (value >5 && value < 9){
            imageGreen.setImageResource(R.drawable.sheep);
            lastGreenResult = 2;

        }
        if (value == 9){
            imageGreen.setImageResource(R.drawable.cow);
            lastGreenResult = 4;

        }
        if (value == 10){
            imageGreen.setImageResource(R.drawable.pig);
            lastGreenResult = 3;
        }
        if (value == 11){
            imageGreen.setImageResource(R.drawable.wolf);
            lastGreenResult = 5;

        }
        int val2 = random.nextInt(12);
        if (val2 <6 ) imageRed.setImageResource(R.drawable.rabbit);
        if (val2 >5 && val2 < 8) imageRed.setImageResource(R.drawable.sheep);
        if (val2 >8 && val2 <10) imageRed.setImageResource(R.drawable.pig);
        if (val2 == 10) imageRed.setImageResource(R.drawable.horse);
        if (val2 == 11) imageRed.setImageResource(R.drawable.fox);


    }
}
