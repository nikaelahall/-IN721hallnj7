package bit.hallnj7.langtrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class scoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        Intent launchIntent = getIntent();
        int calculatedScore = launchIntent.getIntExtra("Score", 0);

        EditText scoreText = (EditText)findViewById(R.id.etScore);
        scoreText.setText(calculatedScore);
    }
}
