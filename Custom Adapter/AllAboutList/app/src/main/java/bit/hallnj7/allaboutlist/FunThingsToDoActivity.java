package bit.hallnj7.allaboutlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FunThingsToDoActivity extends AppCompatActivity
{
    funActivity[] funActivitiesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_things_to_do);

        initialiseDataArray();

        ArrayAdapter<funActivity>funActivityAdapter = new ArrayAdapter<funActivity>(this, R.layout.custom_listview_item, funActivitiesArray);

        ListView lvFunActivities = (ListView)findViewById(R.id.lvFunActivities);
        lvFunActivities.setAdapter(funActivityAdapter);
    }

    public class funActivitiesArrayAdapter extends ArrayAdapter<funActivity>
    {
        public funActivitiesArrayAdapter(Context context, int resource, funActivity[] objects)
        {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            //get an inflater from the activity
            LayoutInflater inflater = LayoutInflater.from(FunThingsToDoActivity.this);

            View customView = inflater.inflate(R.layout.custom_listview_item, container, false);

            ImageView itemImageView = (ImageView)customView.findViewById(R.id.ivItemImage);
            TextView itemTextView = (TextView)customView.findViewById(R.id.tvItemWords);

            funActivity currentItem = getItem(position);

            itemImageView.setImageDrawable(currentItem.funImage);
            itemTextView.setText(currentItem.toString());

            return customView;
        }
    }

    private void initialiseDataArray()
    {
        Resources resourceMachine = getResources();

        Drawable larnachImage = resourceMachine.getDrawable(R.drawable.luna, null);
        Drawable moanaImage = resourceMachine.getDrawable(R.drawable.moana_pool, null);
        Drawable monarchImage = resourceMachine.getDrawable(R.drawable.monarch, null);
        Drawable octagonImage = resourceMachine.getDrawable(R.drawable.octagon, null);
        Drawable olvestonImage = resourceMachine.getDrawable(R.drawable.olveston, null);
        Drawable peninsulaImage = resourceMachine.getDrawable(R.drawable.peninsula, null);

        funActivitiesArray = new funActivity[6];
        funActivitiesArray[0] = new funActivity("Larnach castle", larnachImage);
        funActivitiesArray[1] = new funActivity("Moana pool", moanaImage);
        funActivitiesArray[2] = new funActivity("Monarch cruise", monarchImage);
        funActivitiesArray[3] = new funActivity("Octagon", octagonImage);
        funActivitiesArray[4] = new funActivity("Olveston", olvestonImage);
        funActivitiesArray[5] = new funActivity("Peninsula", peninsulaImage); //need to add the ic_ to the start of the image name so it is small.
    }
}
