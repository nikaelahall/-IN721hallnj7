package bit.hallnj7.edittext;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText TextHandler = (EditText)findViewById(R.id.inputText);
        TextHandler.setOnKeyListener(new TextHandler());
    }

    public class TextHandler implements View.OnKeyListener
    {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            int viewID = v.getId();
            EditText etInput = (EditText)findViewById(viewID);
            String userName = etInput.getText().toString();

            if (keyCode == KeyEvent.KEYCODE_AT)
            {
                Toast.makeText(MainActivity.this, "That was at...", Toast.LENGTH_LONG).show();
            }


            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (userName.length() < 8) {
                    Toast.makeText(MainActivity.this, "Must be 8 characters, " + userName, Toast.LENGTH_LONG).show();
                }

                else {
                    Toast.makeText(MainActivity.this, "Thank you, " + userName, Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }
    }
}
