package bit.hallnj7.locations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GoogleMap mMap;
    LatLng DunedinLatLng;
    LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTeleport = (Button) findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new ButtonClickHandler());
    }



    public class MapCallBackClass implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            mMap = googleMap;

            mMap.addMarker(new MarkerOptions().position(DunedinLatLng));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }


    public class ButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            location = calculateRandomLocation();
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new MapCallBackClass());
        }
    }

    public LatLng calculateRandomLocation()
    {
        Random rand = new Random();

        double randomLatitude = rand.nextDouble() * 90;
        double randomLongitude = rand.nextDouble() * 180;

        int chooseSignLatitude = rand.nextInt(2);
        int chooseSignLongitude = rand.nextInt(2);

        if (chooseSignLatitude == 1) {
            randomLatitude *= -1;
        } else if (chooseSignLongitude == 1) {
            randomLongitude *= -1;
        }

        DunedinLatLng = new LatLng(randomLatitude, randomLongitude);
        return DunedinLatLng;
    }
}
