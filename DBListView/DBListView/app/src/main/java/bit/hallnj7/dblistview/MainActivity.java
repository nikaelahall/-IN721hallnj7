package bit.hallnj7.dblistview;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> cityArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView cityListView = (ListView)findViewById(R.id.listView);

        cityArrayList = new ArrayList<String>();
        ReadDataFromFile();

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityArrayList);
        cityListView.setAdapter(citiesAdapter);

    }

    public void ReadDataFromFile()
    {
        String assetFileName = "city_names.txt";

        AssetManager am = getAssets();

        try
        {
            InputStream is = am.open(assetFileName);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);

            String newCity;
            while ((newCity = br.readLine()) != null)
            {
                cityArrayList.add(newCity);
            }

            br.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
