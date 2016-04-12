package bit.hallnj7.jsonfile;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String JSONInput;
    ArrayList<String> eventNames;
    ListView displayListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventNames = new ArrayList<>(); //creates the ArrayList


        String assetFileName = "dunedin_events.json"; //reads in the json file

        try {
            AssetManager am = getAssets();
            InputStream is = am.open(assetFileName);
            int fileSizeInBytes = is.available();
            byte[] JSONBuffer = new byte[fileSizeInBytes];

            is.read(JSONBuffer);
            is.close();

            JSONInput = new String(JSONBuffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        try
        {
            JSONObject eventsData = new JSONObject(JSONInput);
            JSONObject events = eventsData.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");

            for (int i = 0; i < eventArray.length(); i++) {
                JSONObject event = eventArray.getJSONObject(i);
                String eventTitle = event.getString("title");
                eventNames.add(eventTitle);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


        Button displayButton = (Button) findViewById(R.id.button);
        displayButton.setOnClickListener(new ButtonClickHandler());

        displayListView = (ListView) findViewById(R.id.listView);
        displayListView.setOnItemClickListener(new listViewClickHandler());
    }



    public class ButtonClickHandler implements View.OnClickListener //listener for the onClickHandler on the button
    {
        @Override
        public void onClick(View v)
        {
            int androidLayout = android.R.layout.simple_list_item_1;
            ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(MainActivity.this, androidLayout, eventNames);
            displayListView.setAdapter(nameAdapter);
        }
    }


    public class listViewClickHandler implements AdapterView.OnItemClickListener //listener for the listView whilst clicking
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            int chosenItem = position;

            String description = getDescriptionOfEvent(chosenItem);

            if (description != null)
                Toast.makeText(MainActivity.this, description, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "This event does not contain a description", Toast.LENGTH_LONG).show();
        }
    }


    public String getDescriptionOfEvent(int chosenItem) //gets the description of the event when clicked
    {
        String eventDesc = null;

        try
        {
            JSONObject eventsData = new JSONObject(JSONInput);
            JSONObject events = eventsData.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");
            JSONObject event = eventArray.getJSONObject(chosenItem);
            eventDesc = event.getString("description");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return eventDesc;
    }



    public void descToast() //writes the description to the screen when event is clicked
    {
        try {
            JSONObject eventInfo = new JSONObject(JSONInput);
            JSONObject eventsFound =  eventInfo.getJSONObject("events");
            JSONArray eventArray = eventsFound.getJSONArray("event");

            Toast.makeText(MainActivity.this, eventArray.toString(), Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
