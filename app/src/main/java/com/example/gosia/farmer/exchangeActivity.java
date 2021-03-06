package com.example.gosia.farmer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;
import static java.lang.System.currentTimeMillis;

public class exchangeActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager= null;
    Sensor accelerometer = null;
    long time1 = 0;
    long time2 = 0;
    long lastUpdate = 0;
    float xCurrent, yCurrent, zCurrent;
    float xPrevious, yPrevious, zPrevious;
    Boolean shaking = true;
    int speed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        sensorTrial();
    }

    public void sensorTrial(){
        time1 = currentTimeMillis();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
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
            //        Log.e("Sensor", "x:\t" + sensorEvent.values[0]);
            //        Log.e("Sensor", "y:\t" + sensorEvent.values[1]);
            //        Log.e("Sensor", "z:\t" + sensorEvent.values[2]);
            float change = (xCurrent + yCurrent + zCurrent - xPrevious - yPrevious - zPrevious);
            //Log.e("Sensor", "change" + change);
            xPrevious = xCurrent;
            yPrevious = yCurrent;
            zPrevious = zCurrent;
            if(change > 0 && abs(change) < 0.8){
              speed = 0;
            }
            else if(abs(change)>=0.8 && abs(change) <2){
                speed = 1;
                if(time2 - lastUpdate > 500){                        //co 50
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }

            }
            else if(abs(change)>=2 && abs(change) <3){
                speed = 2;
                if(time2-lastUpdate > 325){
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }

            }
            else if(abs(change)>=3 && abs(change) <4){
                speed = 3;
                if(time2-lastUpdate > 200){
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }

            }
            else if(abs(change)>=4 && abs(change) <5){
                speed = 4;
                if(time2-lastUpdate > 100){
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }

            }
            else if(abs(change)>=5){
                speed = 5;
                if(time2-lastUpdate > 50){
                    time1 = System.currentTimeMillis();
                    lastUpdate = time2;
                    Log.e("Sensor", "speed" + speed);
                }

            }

            if(abs(change) < 1.5){
                if(time2-time1>500) {
                    Toast.makeText(getApplicationContext(), "stop Shaking", Toast.LENGTH_SHORT).show();
                    shaking = false;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
