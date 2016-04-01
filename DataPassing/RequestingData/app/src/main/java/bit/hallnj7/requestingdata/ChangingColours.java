package bit.hallnj7.requestingdata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChangingColours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changing_colours);

        Intent changeActivityIntent = new Intent();

        //not sure why this getColor is underlined, but this line of code still works?
        String strRedColor = "#" + Integer.toHexString(getResources().getColor(R.color.red, null));

        changeActivityIntent.putExtra("requestedResult", strRedColor);
        setResult(Activity.RESULT_OK, changeActivityIntent);
        finish();
    }
}
