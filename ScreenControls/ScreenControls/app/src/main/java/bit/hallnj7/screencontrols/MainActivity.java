package bit.hallnj7.screencontrols;

import android.content.res.Resources;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
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

        Button confirmButton = (Button) findViewById(R.id.ConfirmationButton); //getting a reference to the button control
        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.rbgrpInstruments); //getting a reference to the radio group control

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
                }; //creates the array for the months required - will put this into the resource folder

        Spinner monthsSpinner = (Spinner)findViewById(R.id.monthSpinner); //gets the reference to the spinner control
        int layoutID = android.R.layout.simple_spinner_item; //sets the layout of the spinner
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, layoutID, months);
        monthsSpinner.setAdapter(monthAdapter); //sets the adapter for the spinner

        confirmButton.setOnClickListener(new radioGroupListener()); //sets the onclick listener for the button
        instrumentGroup.setOnClickListener(new radioGroupListener()); //creates the new class for radioGroupListener, sets onclick listener for the radio group
    } //end on create

    public class radioGroupListener implements Button.OnClickListener //creates the new class for the radiogroup listener
    {
        @Override
        public void onClick(View v) //when the button is pressed
        {
            Spinner monthSpinner = (Spinner)findViewById(R.id.monthSpinner); //gets the reference to the spinner control
            String selectedMonth = monthSpinner.getSelectedItem().toString(); //converts the chosen month to a string called selectedMonth

            RadioButton rbAccordion = (RadioButton)findViewById(R.id.rbAccordion); //gets the reference to the radio button accordion control
            RadioButton rbCello = (RadioButton)findViewById(R.id.rbCello); //gets the reference to the radio button cello control
            RadioButton rbBassoon = (RadioButton)findViewById(R.id.rbBassoon); //gets the reference to the radio button bassoon control

            RadioButton rdSelected = null; //sets the radiobutton to be unselected

            if (rbBassoon.isChecked()) //if the bassoon radio button is checked
                rdSelected = rbBassoon; //the selected one is the bassoon

            if (rbAccordion.isChecked()) //if the accordion radio button is checked
                rdSelected = rbAccordion; //the selected one is the accordion

            if(rbCello.isChecked())
                rdSelected = rbCello;

            String chosenInstrument = "";

            if (rdSelected != null) //if the chosen radio button is not null
                chosenInstrument = rdSelected.getText().toString(); //get the text for the chosen radio button, saving as chosenInstrument

            Toast.makeText(MainActivity.this, "You have enrolled for " + chosenInstrument + " lessons in " + selectedMonth, Toast.LENGTH_LONG).show();
            //writes the chosen instrument and month starting to the screen once the button is pressed.
        }
    }
}
