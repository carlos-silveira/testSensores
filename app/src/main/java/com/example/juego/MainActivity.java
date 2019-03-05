package com.example.juego;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView txtC;
    SensorManager sensorManager;
    Sensor sensorAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtC=(TextView) findViewById(R.id.txtC);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,sensorAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
    long tAnterior;
    @Override
    public void onSensorChanged(SensorEvent event) {
    Sensor mySensor= event.sensor;
    if(mySensor.getType()== Sensor.TYPE_ACCELEROMETER){
        float x=event.values[0];
        float y=event.values[1];
        float z=event.values[2];
        long cTime=System.currentTimeMillis();
        if ((cTime -tAnterior)>1000){
            txtC.setText("X: "+x+" Y: "+y+" Z: "+z);
            tAnterior=cTime;
        }
    }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //detenemos el sensor cuando el app este en segundo plano
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorAccelerometer,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
