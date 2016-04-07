package bit.hallnj7.sqldatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase demoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeDatabase();

        String selectQuery = "SELECT * FROM tblCity";
        Cursor recordSet = demoDb.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] displayStringArray = new String[recordCount];

        int cityNameIndex = recordSet.getColumnIndex("cityName");
        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();

        for (int r=0; r<recordCount; r++)
        {
            String cityName = recordSet.getString(cityNameIndex);
            String countryName = recordSet.getString(countryNameIndex);
            displayStringArray[r] = cityName + ", " + countryName;

            recordSet.moveToNext();
        }

        ListView cityListView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, displayStringArray);
        cityListView.setAdapter(citiesAdapter);
    }











    public void makeDatabase()
    {
        demoDb = openOrCreateDatabase("DemoDB", MODE_PRIVATE, null);

        String dropQuery = "DROP TABLE tblCity";
        demoDb.execSQL(dropQuery);

        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity(" +
                "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cityName TEXT NOT NULL, " +
                "countryName TEXT NOT NULL);";
        demoDb.execSQL(createQuery);

        demoDb.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Amsterdam', 'The Netherlands')");
        demoDb.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Berlin', 'Germany')");
        demoDb.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Cairo', 'Egypt')");
        demoDb.execSQL("INSERT INTO tblCity (cityName, countryName) VALUES('Dunedin', 'New Zealand')");
    }

    public void search()
    {

    }
}
