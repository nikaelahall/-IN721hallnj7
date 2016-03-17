package bit.hallnj7.requestingdata;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChangeColour = (Button) findViewById(R.id.ColourButton);
        btnChangeColour.setOnClickListener(new ButtonChangeColourClickHandler());
    }

    public class ButtonChangeColourClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(MainActivity.this, ChangingColours.class);
            startActivityForResult(changeActivityIntent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if ((requestCode == 0) && (resultCode == Activity.RESULT_OK))
        {
            String result = data.getStringExtra("requestedResult");
            TextView textChanging = (TextView) findViewById(R.id.TextViewBody);
            textChanging.setTextColor(Color.parseColor(result));
        }
    }
}
