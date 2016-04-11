package bit.hallnj7.xmlfiles;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> cityNameArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resourceMachine = getResources();
        XmlResourceParser xmlParser = resourceMachine.getXml(R.xml.city_names);

        ListView cityListView = (ListView)findViewById(R.id.listView);
        cityNameArray = new ArrayList<String>();

        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityNameArray);
        cityListView.setAdapter(citiesAdapter);

        //start parsing
        int eventType = 0;
        try
        {
            eventType = xmlParser.getEventType();
        }

        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }

        //loop until getEventType returns END_DOCUMENT
        while(eventType != XmlResourceParser.END_DOCUMENT)
        {
            //if you've found a start tag...
            if (eventType == XmlResourceParser.START_TAG)
            {
                //check if its th element you are looking for
                if (xmlParser.getName().equals("cityName"))
                {
                    //if so, grab the next token, which will be the contents of the tag
                    try
                    {
                        xmlParser.next();
                    }
                    catch (XmlPullParserException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    //Get the text value and put it in the array
                    String currentCityName = xmlParser.getText();
                    cityNameArray.add(currentCityName);
                }
            }

            //grab the next event's type and back to the top of the loop
            try {
                eventType = xmlParser.next();
            }
            catch (XmlPullParserException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        } //end while not END_DOCUMENT
    }
}
