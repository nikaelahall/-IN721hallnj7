package bit.hallnj7.locations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    TextView tvLatitude;
    TextView tvLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTeleport = (Button)findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new ButtonClickHandler());

        tvLongitude = (TextView)findViewById(R.id.tvLongitude);
        tvLatitude = (TextView)findViewById(R.id.tvLatitude);
    }

    public class ButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            calculateRandomLongitude();
            calculateRandomLatitude();
        }
    }

    public void calculateRandomLongitude()
    {
        int max = 90;           int min = -90;
        Random rand = new Random();

        int range = max - min + 1;
        int randomLatitude = rand.nextInt(range) + min;

        tvLatitude.setText(String.valueOf(randomLatitude));
    }

    public void calculateRandomLatitude()
    {
        int max = 180;          int min = -180;
        Random rand = new Random();

        int range = max - min + 1;
        int randomLongitude = rand.nextInt(range) + min;

        tvLongitude.setText(String.valueOf(randomLongitude));
    }


}
