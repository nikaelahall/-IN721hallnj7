package bit.hallnj7.sqldatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    SQLiteDatabase demoDb;
    Spinner countrySpinner;
    ArrayList<String> countryArrayList;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeDatabase();
        fillSpinnerCountryName();

        searchButton = (Button)findViewById(R.id.btnSearch);
        searchButton.setOnClickListener(new ButtonHandler());

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











    public void makeDatabase() //this works
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






    class ButtonHandler implements View.OnClickListener //this does not work
    {
        @Override
        public void onClick(View v)
        {
            String selectedCountry = "'";
            selectedCountry += countrySpinner.getSelectedItem().toString();

            String searchCountry = "SELECT cityName from tblCity WHERE countryName LIKE "; //maybe +=
            selectedCountry += "'";
            searchCountry += selectedCountry;
            Cursor countryRecordSet = demoDb.rawQuery(searchCountry, null);

           int countryRecordCount = countryRecordSet.getCount();
            String[] displaySearchedArray = new String[countryRecordCount];

            int searchedCountryIndex = countryRecordSet.getColumnIndex("countryName");
            int searchedCityIndex = countryRecordSet.getColumnIndex("cityName");

            countryRecordSet.moveToFirst();

            for (int c=1; c<countryRecordCount; c++)
            {
                String countryName = countryRecordSet.getString(searchedCountryIndex);
                String cityName = countryRecordSet.getString(searchedCityIndex);
                displaySearchedArray[c] = countryName + ", " + cityName;

                countryRecordSet.moveToNext();
            }

            ListView searchListView = (ListView)findViewById(R.id.listView);
            ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, displaySearchedArray);
            searchListView.setAdapter(searchAdapter);

            countryRecordSet.close();
        }
    }







    public void fillSpinnerCountryName() //this works
    {
        countrySpinner = (Spinner)findViewById(R.id.spinner);
        int layout = android.R.layout.simple_spinner_item;

        String selectCountry = "SELECT countryName from tblCity";
        Cursor countrySet = demoDb.rawQuery(selectCountry, null);

        int countryCount = countrySet.getCount();
        String[] displayCountryArray = new String[countryCount];

        int countryNameIndex = countrySet.getColumnIndex("countryName");

        countrySet.moveToFirst();

        for (int i=0; i<countryCount; i++)
        {
            String countryName = countrySet.getString(countryNameIndex);
            displayCountryArray[i] = countryName;

            countrySet.moveToNext();
        }

        countryArrayList = new ArrayList<String>();
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, layout, displayCountryArray);
        countrySpinner.setAdapter(countryAdapter);
    }
}
