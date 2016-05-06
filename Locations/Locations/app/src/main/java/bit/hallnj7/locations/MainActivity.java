package bit.hallnj7.locations;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvLatitude;
    TextView tvLongitude;
    int randomLongitude;
    int randomLatitude;
    TextView tvPlace;

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
            AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
            APIThread.execute();
        }
    }

    public void calculateRandomLocation() {
        int LongMax = 90;
        int LongMin = -90;
        int LatMax = 180;
        int LatMin = -180;
        Random rand = new Random();

        int LongRange = LongMax - LongMin + 1;
        randomLatitude = rand.nextInt(LongRange) + LongMin;

        int LatRange = LatMax - LatMin + 1;
        randomLongitude = rand.nextInt(LatRange) + LatMin;

        tvLatitude.setText(String.valueOf(randomLatitude));
        tvLongitude.setText(String.valueOf(randomLongitude));
    }


    public class AsyncAPIShowRawJSON extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String JSONString = null;

            try {
                //String urlString = "http://www.geoplugin.net/extras/location.gp?lat="
                //  + randomLatitude + "&long=" + randomLongitude + "&format=json";
                String urlString = "http://www.geoplugin.net/extras/location.gp?lat=-45&long=170&format=json";

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
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
            return JSONString;
        }

        protected void onPostExecute(String fetchedString)
        {
            Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
            TextView tvPlace = (TextView) findViewById(R.id.tvJsonInput);
            tvPlace.setText(fetchedString);
        }
    }
}
