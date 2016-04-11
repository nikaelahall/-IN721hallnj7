package bit.hallnj7.ioffile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String assetFileName = "dunedin_events.json";

        //Get an assetManager and create an input stream from the JSON file
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

    }
}
