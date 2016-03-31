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
import android.widget.TextView;
import android.widget.Toast;

public class questionActivity extends AppCompatActivity {

    Manager manager = new Manager();
    Question[] displayQuestion;

    AlertBuilderFragment confirmClass;

    String answer = ""; //sets the initial chosen answer to zero
    int currentQuestion = 0;
    int score = 0; //sets the initial score to zero
    TextView qNumber;

    public String getAnswer()
    {
        return answer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        manager.initialiseStart(); //runs the initialise start method from the manager class

        displayQuestion = manager.getQuestionArray();
        generateImg(); //runs the generate image method

        ImageView imgView = (ImageView) findViewById(R.id.imgvQuestion);
        qNumber = (TextView)findViewById(R.id.tvQuestionNumber);

        Button btnDie = (Button) findViewById(R.id.btnDie);
        btnDie.setOnClickListener(new ButtonClickHandler());

        Button btnDer = (Button) findViewById(R.id.btnDer);
        btnDer.setOnClickListener(new ButtonClickHandler());

        Button btnDas = (Button) findViewById(R.id.btnDas);
        btnDas.setOnClickListener(new ButtonClickHandler());

        questionNumber();
    }

    public void questionNumber()
    {
        qNumber.setText("Question number " + ((String.valueOf(currentQuestion +1 )) + " /11"));
    }

    public class ButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId()) //gets the ID of the pressed button
            {
                case R.id.btnDie:
                    answer = "Die"; //if the die button is pressed, answer equals "die".
                    break;
                case R.id.btnDer:
                    answer = "Der";
                    break;
                case R.id.btnDas:
                    answer = "Das";
                    break;
            }

            confirmClass = new AlertBuilderFragment(); //calls the alertbuilder fragment when a button is pressed
            FragmentManager fm = getFragmentManager();
            confirmClass.show(fm, "confirm");

            Bundle bundle = new Bundle(); //sends the chosen answer to the dialogue fragment.
            bundle.putString("chosenAns", answer);
            confirmClass.setArguments(bundle);
        }
    }

    public void giveMeMyData(boolean confirm)
    {
        confirmClass.dismiss();

        if (confirm)
        {
           String chosenAnswer = displayQuestion[currentQuestion].getArticle();
           if (chosenAnswer == answer) //if the answer is correct
           {
               Toast.makeText(questionActivity.this, "Correct", Toast.LENGTH_SHORT).show();
               currentQuestion++; //generates the next question when confirm button is pressed
               score++; //generates the score with each correct answer chosen
               generateImg();
               questionNumber();
            }

            else //if the answer is incorrect and the confirm button is pressed
            {
                Toast.makeText(questionActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                currentQuestion++; //generate the next question
                generateImg(); //generate the image on the screen
                questionNumber();
            }
        }
    }

    public void generateImg() //generates the image on the screen
    {
        if (currentQuestion < displayQuestion.length) //while the question number is less than the array length
        {
            Resources res = getResources();
            String imgName = displayQuestion[currentQuestion].getImageId(); //display the question
            int imgId = res.getIdentifier(imgName, "drawable", getPackageName()); //gets the image for the current question
            ImageView imgView = (ImageView) findViewById(R.id.imgvQuestion);
            imgView.setImageResource(imgId); //sets the imageView to the image for the particular question
        }

        else
        {
            Intent changeActivityIntent = new Intent(questionActivity.this, scoreScreen.class); //changes the intent to the score Screen
            String totScore = Integer.toString(score); //Changes the score value to a string
            changeActivityIntent.putExtra("totscore", totScore); //send the score value to the score screen
            startActivity(changeActivityIntent);
        }
    }
}
