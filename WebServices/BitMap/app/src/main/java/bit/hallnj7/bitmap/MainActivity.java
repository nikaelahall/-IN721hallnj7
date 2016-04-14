package bit.hallnj7.bitmap;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String JSONInput;
    ArrayList<String> artistNames;
    URL URLObject;
    HttpURLConnection connection;
    ListView artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artistNames = new ArrayList<>();

        Button btnShowData = (Button) findViewById(R.id.button);
        btnShowData.setOnClickListener(new ButtonClickHandler());
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
                artistNames.add(image);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public class AsyncAPIShowRawJSON extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            String JSONString = null;
            try
            {
                String urlString = "http://ws.audioscrobbler.com/2.0/?method=chart.getTopArtists&api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=10&format=json";

                URL URLObject = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                connection.connect();
                int responseCode = connection.getResponseCode();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((responseString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONString = stringBuilder.toString();
            }

            catch(Exception e) {e.printStackTrace();}
            return JSONString;
        }



        protected void onPostExecute(String fetchedString)
        {
            createList(fetchedString);

            ListView imgText = (ListView)findViewById(R.id.listView);

            //artistList = (ListView) findViewById(R.id.listView);
            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, artistNames);
            //ArtistDataAdapter arrayAdapter = new ArtistDataAdapter(MainActivity.this, R.layout.artist_text_views, artistNames);
            //artistList.setAdapter(arrayAdapter);
            imgText.setAdapter(arrayAdapter);
        }
    }
}
