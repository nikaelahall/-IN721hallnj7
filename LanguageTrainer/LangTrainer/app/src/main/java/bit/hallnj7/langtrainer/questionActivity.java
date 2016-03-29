package bit.hallnj7.langtrainer;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class questionActivity extends AppCompatActivity {

    Manager manager = new Manager();
    Question[] displayQuestion;

    AlertBuilderFragment confirmClass;
    String answer = "";
    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        manager.initialiseStart();

        displayQuestion = manager.getQuestionArray();
        generateImg();

        ImageView imgView = (ImageView) findViewById(R.id.imgvQuestion);

        Button btnDie = (Button) findViewById(R.id.btnDie);
        btnDie.setOnClickListener(new ButtonClickHandler());

        Button btnDer = (Button) findViewById(R.id.btnDer);
        btnDer.setOnClickListener(new ButtonClickHandler());

        Button btnDas = (Button) findViewById(R.id.btnDas);
        btnDas.setOnClickListener(new ButtonClickHandler());
    }

    public class ButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnDie:
                    answer = "Die";
                    break;
                case R.id.btnDer:
                    answer = "Der";
                    break;
                case R.id.btnDas:
                    answer = "Das";
                    break;
            }

            confirmClass = new AlertBuilderFragment();
            FragmentManager fm = getFragmentManager();
            confirmClass.show(fm, "confirm");
        }
    }

    public void giveMeMyData(boolean confirm)
    {
        confirmClass.dismiss();

        if (confirm)
        {
           String chosenAnswer = displayQuestion[currentQuestion].getArticle();
           if (chosenAnswer == answer)
           {
               Toast.makeText(questionActivity.this, "Correct", Toast.LENGTH_SHORT).show();
               currentQuestion++;
               score++; //use data passing to get the score to the score page
               generateImg();
            }

            else
            {
                Toast.makeText(questionActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                currentQuestion++;
                generateImg();
            }
        }
    }

    public void generateImg()
    {
        if (currentQuestion < displayQuestion.length)
        {
            Resources res = getResources();
            String imgName = displayQuestion[currentQuestion].getImageId();
            int imgId = res.getIdentifier(imgName, "drawable", getPackageName());
            ImageView imgView = (ImageView) findViewById(R.id.imgvQuestion);
            imgView.setImageResource(imgId);
        }

        else
        {
            Intent changeActivityIntent = new Intent(questionActivity.this, scoreScreen.class);
            changeActivityIntent.putExtra("Score", score);
            startActivity(changeActivityIntent);
        }
    }
}
