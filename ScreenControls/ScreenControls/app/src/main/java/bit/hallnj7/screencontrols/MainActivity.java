package bit.hallnj7.screencontrols;

import android.content.res.Resources;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources resourceResolver = getResources();

        Button confirmButton = (Button) findViewById(R.id.ConfirmationButton);
        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.rbgrpInstruments);

        String[] months =
                {
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"
                };

        Spinner monthsSpinner = (Spinner)findViewById(R.id.monthSpinner);
        int layoutID = android.R.layout.simple_spinner_item;
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, layoutID, months);
        monthsSpinner.setAdapter(monthAdapter);

        confirmButton.setOnClickListener(new radioGroupListener());
        instrumentGroup.setOnClickListener(new radioGroupListener()); //creates the new class for radioGroupListener
    } //end on create

    public class radioGroupListener implements Button.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Toast.makeText(MainActivity.this, "You have enrolled for <instrument> lessons in <month>", Toast.LENGTH_LONG).show();
        }
    }
}
