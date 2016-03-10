package bit.hallnj7.allaboutdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        setUpDunedinGroupList(); //calls the class setUpDunedinGroupList

        ListView dunedinGroupListView = (ListView) findViewById(R.id.left_drawer); //finds the id of the listView
        dunedinGroupListView.setOnItemClickListener(new DunedinGroupListClickHandler());
    }

    public void setUpDunedinGroupList() {
        String[] groups = {"Services", "Fun things to do", "Dining", "Shopping"}; //creates the array for the groups in Dunedin
        ArrayAdapter<String> dunedinGroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        ListView dunedinGroupListView = (ListView) findViewById(R.id.left_drawer);
        dunedinGroupListView.setAdapter(dunedinGroupAdapter);
    }

    public class DunedinGroupListClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedItem = (String) parent.getItemAtPosition(position).toString(); //gets the position of the clicked item
            Intent goToIntent;

            switch (clickedItem) { //creates the switch for each of the list items
                case "Services":
                    goToIntent = new Intent(MainActivity.this, ServiceActivity.class); //goes to the service class
                    break;
                case "Fun things to do": //if the activities list item is selected
                    goToIntent = new Intent(MainActivity.this, FunThingsToDoActivity.class); //goes to the activities class
                    break;
                case "Dining": //if the dining list item is selected
                    goToIntent = new Intent(MainActivity.this, DiningActivity.class); //goes to the dining class
                    break;
                case "Shopping": //if the shopping list item is selected
                    goToIntent = new Intent(MainActivity.this, ShoppingActivity.class); //goes to the shopping class
                    break;
                default:
                    goToIntent = null;
            } //end switch

            if (goToIntent != null) {
                startActivity(goToIntent);
            }
        }//end on itemClick
    }
}
