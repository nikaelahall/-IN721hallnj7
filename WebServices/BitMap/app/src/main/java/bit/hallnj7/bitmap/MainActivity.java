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
import org.json.JSONStringer;

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
    String urlString;
    ImageView image;
    Bitmap artistBMP;
    String JSONString;
    String artistImage;
    Bitmap displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        similarArtistNames = new ArrayList<>();
        artistImage = "";

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
            //AsyncShowImage APIImage = new AsyncShowImage();
            //APIImage.execute();
        }
    }

    public void createList(String fetchedString)
    {
        String imgURL;

        try {
            JSONObject artistData = new JSONObject(fetchedString);
            JSONObject artists = artistData.getJSONObject("artists");
            JSONArray artistArray = artists.getJSONArray("artist");

            JSONObject artist = artistArray.getJSONObject(0);
            String image = artist.getString("image");
            similarArtistNames.add(image);
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
                urlString = "http://ws.audioscrobbler.com/2.0/?method=chart.getTopArtists&api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=1&format=json";

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
    }


    public class AsyncShowImage extends AsyncTask<Void, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Void... params)
        {
            Bitmap image = null;

            try {

                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream imageInputStream = connection.getInputStream();
                Bitmap artistBMP = BitmapFactory.decodeStream(imageInputStream);

            } catch (IOException e) {
                // Log exception
                return null;
            }

            return artistBMP;
        }
    }


    protected void onPostExecute(String fetchedString) {
        displayImage = artistBMP;
        showImage();
    }

    public void showImage() {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageBitmap(artistBMP);
    }

    public void GetArtistImage(String fetchedString)
    {
        try {
            JSONObject topArtists = new JSONObject(fetchedString);
            JSONObject artists =  topArtists.getJSONObject("artists");
            JSONArray artistArray = artists.getJSONArray("artist");
            JSONObject currentArtist = artistArray.getJSONObject(0); //FirstArtist
            JSONArray imageArray = currentArtist.getJSONArray("image");
            JSONObject image = imageArray.getJSONObject(2); //LargeImage

            artistImage = image.getString("#text");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}