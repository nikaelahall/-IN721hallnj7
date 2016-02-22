package bit.hallnj7.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
    }
}
