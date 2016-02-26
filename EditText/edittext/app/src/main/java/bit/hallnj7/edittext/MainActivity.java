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
    } //end of onCreate

    public class TextHandler implements View.OnKeyListener
    {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            int viewID = v.getId(); //gets the id and stores it in the variable viewID
            EditText etInput = (EditText)findViewById(viewID);
            String userName = etInput.getText().toString(); /**Converts the EditText input into a String**/

            if (keyCode == KeyEvent.KEYCODE_AT) /**If the @ symbol is pressed**/
            {
                Toast.makeText(MainActivity.this, "That was at...", Toast.LENGTH_LONG).show(); /**Writes to the user when they type in the @ symbol into the editText**/
            }


            if (keyCode == KeyEvent.KEYCODE_ENTER) /**If the enter key is pressed**/
            {
                if ((userName.length() < 8) && (userName.length() > 8))            /**checks if the userName has a length of less than or more than 8**/
                {
                    Toast.makeText(MainActivity.this, "Must be 8 characters, " + userName, Toast.LENGTH_LONG).show(); /**Writes to the user stating the userName must be 8 characters**/
                }

                else /**The userName has a length of at least 8**/
                {
                    Toast.makeText(MainActivity.this, "Thank you, " + userName, Toast.LENGTH_LONG).show(); /**Writes to the user thanking them**/
                }
            }
            return false;
        }
    }
}
