package bit.hallnj7.langtrainer;

import android.content.res.TypedArray;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Manager
{
    public Manager()
    { }

    public Question[] getQuestionArray()
    {
        return questionArray;
    }

    public Question[] questionArray;


    public void initialiseStart()
    {
        questionArray = new Question[11];
        generateQuestions();
        shuffleQuestion();
    }

    public void generateQuestions()
    {
        questionArray[0] = new Question("Auto", "Das", "das_auto");
        questionArray[1] = new Question("Haus", "Das", "das_haus");
        questionArray[2] = new Question("Schaf", "Das", "das_schaf");
        questionArray[3] = new Question("Apfel", "Der", "der_apfel");
        questionArray[4] = new Question("Baum", "Der", "der_baum");
        questionArray[5] = new Question("Stuhl", "Der", "der_stuhl");
        questionArray[6] = new Question("Ente", "Die","die_ente");
        questionArray[7] = new Question("Hexe", "Die", "die_hexe");
        questionArray[8] = new Question("Kuh", "Die","die_kuh");
        questionArray[9] = new Question("Milch", "Die", "die_milch");
        questionArray[10] = new Question("Strasse", "Die", "die_strasse");
    }

    public void shuffleQuestion()
    {
        Random rand = new Random();

        for(int i = 0; i < questionArray.length; i++)
        {
            int randPosition = rand.nextInt(questionArray.length);
            Question temp = questionArray[i];
            questionArray[i] = questionArray[randPosition];
            questionArray[randPosition] = temp;
        }
    }
}



