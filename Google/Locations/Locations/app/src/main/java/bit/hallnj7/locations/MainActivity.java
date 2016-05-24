package bit.hallnj7.locations;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvLatitude;
    TextView tvLongitude;
    TextView tvPlace;
    double randomLatitude;
    double randomLongitude;
    String geopluginCity;
    String geopluginCountry;
    ProgressDialog progress;
    GoogleMap mMap;
    LatLng DunedinLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTeleport = (Button) findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new ButtonClickHandler());

        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
    }


    public class ButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            calculateRandomLocation();

            progress = new ProgressDialog(MainActivity.this);
            progress.setMessage("Progress...");
            progress.show();


            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new MapCallBackClass());
        }
    }

    public class MapCallBackClass implements OnMapReadyCallback
    {
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            mMap = googleMap;

            mMap.addMarker(new MarkerOptions().position(DunedinLatLng).title("Dunedin!!"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(DunedinLatLng));
        }
    }


    public void calculateRandomLocation() //generates the random coordinate, gives an even range of odd and even numbers
    {
        Random rand = new Random();

        randomLatitude = rand.nextDouble() * 90;
        randomLongitude = rand.nextDouble() * 180;

        int chooseSignLatitude = rand.nextInt(2);
        int chooseSignLongitude = rand.nextInt(2);

        if (chooseSignLatitude == 1) {
            randomLatitude *= -1;
        } else if (chooseSignLongitude == 1) {
            randomLongitude *= -1;
        }

        AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
        APIThread.execute();

        DunedinLatLng = new LatLng(-45, 170);
    }


    public class AsyncAPIShowRawJSON extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String JSONString = null;

            try {
                String urlString = "http://www.geoplugin.net/extras/location.gp?lat="
                        + randomLatitude + "&long=" + randomLongitude + "&format=json";

                URL URLObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((responseString = bufferedReader.readLine()) != null) {
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONString = stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return JSONString;
        }


        protected void onPostExecute(String fetchedString)
        {
            try
            {
                TextView tvPlace = (TextView) findViewById(R.id.tvJsonInput);
                JSONObject placeData = new JSONObject(fetchedString);
                geopluginCity = placeData.optString("geoplugin_place");
                geopluginCountry = placeData.optString("geoplugin_countryCode");

                if (geopluginCity != null && geopluginCountry != null) {
                    progress.dismiss();
                    tvPlace.setText("Closest city: " + geopluginCity + ", " + geopluginCountry);
                }
            }

            catch (JSONException e)
            {
                e.printStackTrace();
                //DisplayNoCity();  //Displays "No city here" when no city is close to these co-ordinates
                calculateRandomLocation(); //calculates the random co-ordinates
            }
        }
    }
}
