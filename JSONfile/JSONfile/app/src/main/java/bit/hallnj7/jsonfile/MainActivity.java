package bit.hallnj7.jsonfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String assetFileName = "city_data.json";

        //Get an asset manager and create an input stream from the JSON file
        AssetManager am = getAssets();
        InputStream inputStream = null;
        int fileSizeInBytes = 0;
        byte[] JSONBuffer = new byte[fileSizeInBytes];

        try
        {
            inputStream = am.open(assetFileName);
            fileSizeInBytes = inputStream.available();
            inputStream.read(JSONBuffer);
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Create a string from the byte[]
        String JSONInput = new String(JSONBuffer);

        JSONObject cityData = null;
        JSONArray dataArray = null;
        try
        {
            //Convert file string to JSONObject
            cityData = new JSONObject(JSONInput);
            dataArray = cityData.getJSONArray("data");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        //Debugging
        Toast.makeText(MainActivity.this, dataArray.toString(), Toast.LENGTH_LONG).show();

    }
}
