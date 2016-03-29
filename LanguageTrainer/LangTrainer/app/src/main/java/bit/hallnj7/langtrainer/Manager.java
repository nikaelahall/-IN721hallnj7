package bit.hallnj7.langtrainer;

import android.content.res.TypedArray;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Manager
{
    public Manager() //empty constructor
    { }

    public Question[] getQuestionArray() //returns the questionArray
    {
        return questionArray;
    }

    public Question[] questionArray; //creates the questionArray


    public void initialiseStart() //method that runs during start up
    {
        questionArray = new Question[11]; //creates the questionArray with 11 objects
        generateQuestions(); //runs the generateQuestions method
        shuffleQuestion(); //runs the shuffle questions method
    }

    public void generateQuestions() //generates the questions in an array
    {
        questionArray[0] = new Question("Auto", "Das", "das_auto"); //new question with an article, noun and image
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

    public void shuffleQuestion() //method that shuffles the questions
    {
        Random rand = new Random(); //random number

        for(int i = 0; i < questionArray.length; i++)
        {
            int randPosition = rand.nextInt(questionArray.length);
            Question temp = questionArray[i];//makes a temporary position in the array to swap objects
            questionArray[i] = questionArray[randPosition]; //swaps two of the array objects
            questionArray[randPosition] = temp; //puts one of the objects into the temporary position of the array
        }
    }
}



