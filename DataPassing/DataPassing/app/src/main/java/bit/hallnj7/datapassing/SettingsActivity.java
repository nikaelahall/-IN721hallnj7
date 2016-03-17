package bit.hallnj7.datapassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnPassData = (Button) findViewById(R.id.returnMainButton);
        btnPassData.setOnClickListener(new returnMainButtonClickHandler());
    }

    public class returnMainButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(SettingsActivity.this, MainActivity.class);
            EditText TextUserName = (EditText) findViewById(R.id.usernameEntry);
            String username = TextUserName.getText().toString();

            changeActivityIntent.putExtra("Username", username);
            startActivity(changeActivityIntent);
        }
    }
}
