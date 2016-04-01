package bit.hallnj7.allaboutlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDunedinGroupList();

        ListView dunedinGroupListView = (ListView) findViewById(R.id.dunedinGrouplistView);
       // dunedinGroupListView.setOnItemClickListener(new ListViewWithToastHandler());
        dunedinGroupListView.setOnItemClickListener(new DunedinGroupListClickHandler());
    }

    public void setUpDunedinGroupList()
    {
        String[] groups = {"Services", "Fun things to do", "Dining", "Shopping"};
        ArrayAdapter<String> dunedinGroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        ListView dunedinGroupListView = (ListView) findViewById(R.id.dunedinGrouplistView);
        dunedinGroupListView.setAdapter(dunedinGroupAdapter);
    }

    //public class ListViewWithToastHandler implements AdapterView.OnItemClickListener
   // {
      //  @Override
      //  public void onItemClick(AdapterView<?> parent, View view, int position, long id)
      //  {
       //     String clickedItemString = (String) parent.getItemAtPosition(position).toString();
       //     Toast.makeText(MainActivity.this, clickedItemString, Toast.LENGTH_LONG).show();
      //  }
  //  }

    public class DunedinGroupListClickHandler implements AdapterView.OnItemClickListener
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String clickedItem = (String) parent.getItemAtPosition(position).toString();
                Intent goToIntent;

                switch(clickedItem)
                {
                    case "Services":
                        goToIntent = new Intent(MainActivity.this, ServiceActivity.class);
                        break;
                    case "Fun things to do":
                        goToIntent = new Intent(MainActivity.this, FunThingsToDoActivity.class);
                        break;
                    case "Dining":
                        goToIntent = new Intent(MainActivity.this, DiningActivity.class);
                        break;
                    case "Shopping":
                        goToIntent = new Intent(MainActivity.this, ShoppingActivity.class);
                        break;
                    default:
                        goToIntent = null;
                } //end switch

                if (goToIntent != null)
                {
                    startActivity(goToIntent);
                }
            }//end on itemClick
    }
}
