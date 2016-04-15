package bit.hallnj7.similarmusicians;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

    ArrayList<String> similarArtistNames;
    URL URLObject;
    HttpURLConnection connection;
    EditText ETenteredName;
    String urlString;
    String enteredName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        similarArtistNames = new ArrayList<>();

        Button btnShowRaw = (Button) findViewById(R.id.button);
        btnShowRaw.setOnClickListener(new ButtonClickHandler());

        searchNames();
        //ETenteredName = (EditText)findViewById(R.id.editText);
        //enteredName = ETenteredName.getText().toString();
    }


    public class ButtonClickHandler implements  View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if (TextUtils.isEmpty(ETenteredName.getText()))
            {
                Toast.makeText(MainActivity.this, "Nothing entered", Toast.LENGTH_LONG).show();
            }
            else
            {
                similarArtistNames.clear();
                AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
                APIThread.execute();
                searchNames();
            }
        }
    }

    public String searchNames()
    {
        ETenteredName = (EditText)findViewById(R.id.editText);
        return ETenteredName.getText().toString();
    }

    public void createList(String fetchedString)
    {
        try {
            JSONObject artistData = new JSONObject(fetchedString);
            JSONObject artists = artistData.getJSONObject("similarartists");
            JSONArray artistArray = artists.getJSONArray("artist");

            for (int i = 0; i < 10; i++) {
                JSONObject artist = artistArray.getJSONObject(i);
                String name = artist.getString("name");
                similarArtistNames.add(name);
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

            searchNames();

            String api = "&api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=10&format=json";

            try {
                urlString = "http://ws.audioscrobbler.com/2.0/?";
                urlString += "method=artist.getSimilar&artist=" + enteredName + api;

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


        protected void onPostExecute(String fetchedString) {
            createList(fetchedString);

            ListView artistList = (ListView) findViewById(R.id.listView);
            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, similarArtistNames);
            artistList.setAdapter(arrayAdapter);
        }
    }
}

