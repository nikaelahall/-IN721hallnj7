package bit.hallnj7.sensorproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    SensorManager sensorManager;
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;
    float x;
    float y;
    float z;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        imageView = (ImageView)findViewById(R.id.imageView);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new movementSensor(), accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);

        imageView.scrollTo(Math.round(x), Math.round(y));
        imageView.setImageResource(R.drawable.ball);
    }

    public class movementSensor implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            textView.setText(String.valueOf("X axis: " + x));
            textView2.setText(String.valueOf("Y axis: " + y));
            textView3.setText(String.valueOf("Z axis: " + z));

            imageView.scrollBy(Math.round(x), Math.round(y));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
