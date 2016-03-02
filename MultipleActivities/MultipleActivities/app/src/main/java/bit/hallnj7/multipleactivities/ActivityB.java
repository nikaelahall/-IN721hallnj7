package bit.hallnj7.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_b);

        Button ActivityBButton = (Button) findViewById(R.id.ActivityBButton); //reference to a button control
        ActivityBButton.setOnClickListener(new ChangeActBButtonClickHandler()); //creates the new class
    }

    class ChangeActBButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActCIntent = new Intent(ActivityB.this, ActivityC.class);
            startActivity(changeActCIntent);
        }
    }
}
