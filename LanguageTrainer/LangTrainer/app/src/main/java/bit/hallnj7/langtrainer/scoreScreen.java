package bit.hallnj7.langtrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        TextView scoretext = (TextView)findViewById(R.id.tvScore);
        String calculatedScore;
        calculatedScore = launchIntent.getStringExtra("totscore"); //gets the totalScore screen from the questionActivity

        scoretext.setText(calculatedScore + " /11");//Displays the total score to the TextView

        Button tryagain = (Button)findViewById(R.id.btnTryAgain);
        tryagain.setOnClickListener(new tryagainHandler());
    }

    class tryagainHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(scoreScreen.this, welcomeScreen.class); //changes to the questionActivity
            startActivity(changeActivityIntent);
        }
    }
}
