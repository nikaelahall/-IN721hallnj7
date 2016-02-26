package bit.hallnj7.screencontrols;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.rbgrpInstruments);
        instrumentGroup.setOnClickListener(new radioGroupListener()); //creates the new class for radioGroupListener
    } //end on create

    public class radioGroupListener implements Button.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Toast.makeText(MainActivity.this, "You have enrolled for  .... lessons", Toast.LENGTH_LONG).show();
        }
    }
}
