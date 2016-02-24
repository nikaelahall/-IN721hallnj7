package bit.hallnj7.buttonclick;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ButtonClickHandler = (Button)findViewById(R.id.ClickButton);
        ButtonClickHandler.setOnClickListener(new ButtonClickHandler());
        ButtonClickHandler.setOnLongClickListener(new LongButtonClickHandler());
    }

    public class ButtonClickHandler implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Toast statusToast = Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_LONG);
            statusToast.show();
        }
    }

    public class LongButtonClickHandler implements View.OnLongClickListener
    {
        @Override
        public boolean onLongClick(View v)
        {
            Toast ToastStatus = Toast.makeText(MainActivity.this, "Button held", Toast.LENGTH_LONG);
            ToastStatus.show();
            return true;
        }
    }
}
