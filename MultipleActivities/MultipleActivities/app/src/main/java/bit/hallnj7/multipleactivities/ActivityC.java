package bit.hallnj7.multipleactivities;

import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_c);

        Button ActivityCButton = (Button) findViewById(R.id.ActivityCButton); //reference to a button control
        ActivityCButton.setOnClickListener(new ChangeActCButtonClickHandler()); //creates the new class
    }

    class ChangeActCButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Uri goSeeMickey = Uri.parse("http://www.disney.com");
            Intent mickeyIntent = new Intent(Intent.ACTION_VIEW, goSeeMickey);
            startActivity(mickeyIntent);
        }
    }
}
