package bit.hallnj7.webservices;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    URL URLObject;
    HttpURLConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowRaw = (Button) findViewById(R.id.btnRaw);
        btnShowRaw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AsyncAPIShowRawJSON APIThread = new AsyncAPIShowRawJSON();
                APIThread.execute();
            }});
    } //end onCreate



    class AsyncAPIShowRawJSON extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            //Declare here so it's not local to the try block
            String JSONString = null;
            try
            {
                String urlString = "http://ws.audioscrobbler.com/2.0/?method=artist.getTopTracks&artist=Sia&api_key=58384a2141a4b9737eacb9d0989b8a8c&limit=10&format=json";


                //Convert URL string to URLObject
                URL URLObject = new URL(urlString);
                //Create HTTPURLConnection object via openConnection command of URLObject
                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();
                //Send the URL
                connection.connect();
                //If it doesn't return 200, you don't have data...
                int responseCode = connection.getResponseCode();
                //Get an InputStream from the HttpURLConnection object and set up a BufferedReader
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //Read the input. May only be one line...
                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((responseString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(responseString);
                }
                //Get the string from the stringBuilder. JSONString ready for parsing
                JSONString = stringBuilder.toString();
            } //end try

            catch(Exception e) {e.printStackTrace();}
            return JSONString;
        } //end doInBackground



        protected void onPostExecute(String fetchedString)
        {
            //Dump fetched string to textView
            TextView tv = (TextView) findViewById(R.id.textView);
            tv.setText(fetchedString);
        }
    }
}
