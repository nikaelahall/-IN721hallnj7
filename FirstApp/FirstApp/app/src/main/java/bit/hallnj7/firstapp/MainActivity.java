package bit.hallnj7.firstapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtRandomString = (TextView)findViewById(R.id.authorText);

        //pick a string
        String dogBreed = "";

        Random rGen = new Random();
        int dogValue = rGen.nextInt(4);

        switch(dogValue) {
            case 0:
                dogBreed = "Poodle";
                break;
            case 1:
                dogBreed = "Labrador";
                break;
            case 2:
                dogBreed = "Shar Pei";
                break;
            case 3:
                dogBreed = "Newfoundland";
                break;
        }

        txtRandomString.setText(dogBreed);

        TextView textString;
        String dateText = " ";

            Resources resourceResolver = getResources();
            int datesArray[] = resourceResolver.getIntArray(R.array.FebFridays);
            textString = (TextView) findViewById(R.id.FebText);

        dateText += "February Fridays are on: ";

        for(int i = 0; i < datesArray.length; i++)
        {
            dateText += (Integer.toString(datesArray[i]) + " ");
        }

        textString.setText(dateText);
    }
}
