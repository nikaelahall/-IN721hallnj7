package bit.hallnj7.langtrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class scoreScreen extends AppCompatActivity {

    Manager manager = new Manager();
    Question[] displayQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        Intent launchIntent = getIntent();
        TextView scoretext = (TextView)findViewById(R.id.etScore);
        String calculatedScore;
        calculatedScore = launchIntent.getStringExtra("totscore");

        scoretext.setText(calculatedScore);
    }
}
