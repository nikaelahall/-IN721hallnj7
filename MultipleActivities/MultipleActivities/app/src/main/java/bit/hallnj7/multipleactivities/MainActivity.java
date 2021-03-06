package bit.hallnj7.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ActivityAButton = (Button) findViewById(R.id.ActivityAButton); //reference to a button control
        ActivityAButton.setOnClickListener(new ChangeActivityButtonClickHandler()); //creates the new class
    }

    class ChangeActivityButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(MainActivity.this, ActivityB.class);
            startActivity(changeActivityIntent);
        }
    }
}
