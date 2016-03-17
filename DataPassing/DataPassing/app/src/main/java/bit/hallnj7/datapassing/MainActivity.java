package bit.hallnj7.datapassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPassData = (Button)findViewById(R.id.btnSettings);
        btnPassData.setOnClickListener(new PassDataButtonClickHandler());

        Intent launchIntent = getIntent();
        TextView username = (TextView) findViewById(R.id.usernameText);

        if(username.getText() == getResources().getString(R.string.usernameText))
        {
            //Toast.makeText(this, "Please enter a username", Toast.LENGTH_LONG).show();
        }

        else
        {
            TextView TextUserName = (TextView) findViewById(R.id.usernameText);
            TextUserName.setText(launchIntent.getStringExtra("Username"));
        }

    } //end onCreate

    public class PassDataButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(changeActivityIntent);
        }
    }
}
