package bit.hallnj7.screencontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.logging.Handler;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        int STARTER_DELAY = this.getResources().getInteger(R.integer.startUpDelay);

        android.os.Handler handler = new android.os.Handler();

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent openNextActivity = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(openNextActivity);
            }
        }, STARTER_DELAY);
    }
}
