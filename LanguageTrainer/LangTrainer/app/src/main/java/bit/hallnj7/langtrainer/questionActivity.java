package bit.hallnj7.langtrainer;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class questionActivity extends AppCompatActivity {

    Question displayQuestion;
    AlertBuilderFragment confirmClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        displayQuestion = Manager.questionArray[0]; //load the first question
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
                    Toast.makeText(questionActivity.this, "Die button pressed", Toast.LENGTH_LONG).show();
                    break;
                case R.id.btnDer:
                    Toast.makeText(questionActivity.this, "Der button pressed", Toast.LENGTH_LONG).show();
                    break;
                case R.id.btnDas:
                    Toast.makeText(questionActivity.this, "Das button pressed", Toast.LENGTH_LONG).show();
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
            Toast.makeText(questionActivity.this, "you chose yes", Toast.LENGTH_LONG).show();
        }

        else
        {
            Toast.makeText(questionActivity.this, "you chose no", Toast.LENGTH_LONG).show();
        }
    }

    public void generateImg()
    {
        Resources res = getResources();
        String imgName = displayQuestion.getImageId();
        int imgId = res.getIdentifier(imgName, "drawable", getPackageName());
        ImageView imgView = (ImageView) findViewById(R.id.imgvQuestion);
        imgView.setImageResource(imgId);
    }
}
