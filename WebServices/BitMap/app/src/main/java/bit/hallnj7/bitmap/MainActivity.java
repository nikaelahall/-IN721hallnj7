package bit.hallnj7.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import bit.hallnj7.bitmap.R;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> similarArtistNames;
    URL URLObject;
    HttpURLConnection connection;
    String urlString;
    ImageView image;
    Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        similarArtistNames = new ArrayList<>();

        Button btnShowRaw = (Button) findViewById(R.id.button);
        btnShowRaw.setOnClickListener(new ButtonClickHandler());

        image = (ImageView)findViewById(R.id.imageView);
    }


    public class ButtonClickHandler implements  View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
                AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
                APIThread.execute();
        }
    }

    public void createList(String fetchedString)
    {
        try {
            JSONObject artistData = new JSONObject(fetchedString);
            JSONObject artists = artistData.getJSONObject("artists");
            JSONArray artistArray = artists.getJSONArray("artist");

            for (int i = 0; i < 1; i++) {
                JSONObject artist = artistArray.getJSONObject(i);
                String image = artist.getString("image");
                similarArtistNames.add(image);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public class AsyncAPIShowRawJSON extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String JSONString = null;

            try {
                urlString = "http://ws.audioscrobbler.com/2.0/?method=chart.getTopArtists&api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=10&format=json";

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

            try {

                urlString = JSONString;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;

            } catch (IOException e) {
                // Log exception
                return null;
            }


            return JSONString;
        }


        protected void onPostExecute(String fetchedString) {
            createList(fetchedString);

            ListView artistList = (ListView) findViewById(R.id.listView);
            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, similarArtistNames);
            artistList.setAdapter(arrayAdapter);
            ImageView image = (ImageView)findViewById(R.id.imageView);
            image.setImageResource(myBitmap);
        }
    }
}