package com.example.jiayuan.firstapp;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sManager;
    private Sensor mSensorAccelerometer;
    private TextView tv_step,tx;
    private Button start,start1,start2,start3;
    public static int step = 0;   //步数
    private double oriValue = 0;  //原始值
    private double lstValue = 0;  //上次的值
    private double curValue = 0;  //当前值
    private boolean motiveState = true;   //是否处于运动状态
    private boolean processState = false;   //标记当前是否已经在计步
    public static int time=1000,c=0,d=0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handler=new Handler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorAccelerometer=sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener(this,mSensorAccelerometer,SensorManager.SENSOR_DELAY_UI);
        tv_step=(TextView)findViewById(R.id.TextView3);
        tx=(TextView)findViewById(R.id.TextView9);
        tv_step.setText(""+step);
        start=(Button)findViewById(R.id.Button1);
        start1=(Button)findViewById(R.id.Button2);
        start2=(Button)findViewById(R.id.Button5);
        start3=(Button)findViewById(R.id.Button6);

       // start.setOnClickListener(this);
    }
    public void TimeClick(View view){
        handler.postDelayed(runnable,time);

    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            try{
                handler.postDelayed(this,time);
                if(d>58) {
                    d=0;
                    tx.setText("" + (++c) + "分" + ""+d+"秒");
                }
                else {
                    tx.setText("" + c + "分" + ""+(++d)+"秒");
                }
            }
            catch(Exception e){

                e.printStackTrace();
            }
        }
    };
    public void ResetClick(View view){
        step=0;
      tv_step.setText(""+0);
    }
    public void StopClick(View view){

        handler.removeCallbacks(runnable);
    }
    public void onSensorChanged(SensorEvent event) {
        double range = 2;   //设定一个精度范围
        float[] value = event.values;
        curValue = magnitude(value[0], value[1], value[2]);   //计算当前的模
        //向上加速的状态
        if (motiveState == true) {
            if (curValue >= lstValue) lstValue = curValue;
            else {
                //检测到一次峰值
                if (Math.abs(curValue - lstValue) > range) {
                    oriValue = curValue;
                    motiveState = false;
                }
            }
        }
        //向下加速的状态
        if (motiveState == false) {
            if (curValue <= lstValue) lstValue = curValue;
            else {
                if (Math.abs(curValue - lstValue) > range) {
                    //检测到一次峰值
                    oriValue = curValue;
                    if (processState == true) {
                        step++;  //步数 + 1
                        if (processState == true) {
                            tv_step.setText(""+step);    //读数更新
                        }
                    }
                    motiveState = true;
                }
            }
        }
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
      public void StartClick(View view) {

        if (processState == true) {
            start.setText("開始");
            processState = false;
      } else {
            start.setText("停止");
            processState = true;
        }
    }
    public void Click(View view){
        Intent intent=new Intent(getApplicationContext(),jiayuan.class);
        startActivity(intent);
    }
    public double magnitude(float x, float y, float z) {
        double magnitude = 0;
        magnitude = Math.sqrt(x * x + y * y + z * z);
        return magnitude;
    }

    //@Override
    //protected void onDestroy() {
       // super.onDestroy();
       // sManager.unregisterListener(this);
    //}


    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void onDestroy() {
        super.onDestroy();
        sManager.unregisterListener(this);
    }
}
