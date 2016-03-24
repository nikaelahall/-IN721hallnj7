package bit.hallnj7.langtrainer;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class questionClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_class);

        final ImageView BackCard = (ImageView)findViewById(R.id.imgvQuestion);

            BackCard.setImageResource(R.drawable.placeholder);
            BackCard.postDelayed(new Runnable() {

                public void run() {
                    int randomCard = randomCard();
                    BackCard.setImageResource(randomCard);
                }
            }, 150);
    }

    public int randomCard()
    {
        Resources res = getResources();
        TypedArray images = res.obtainTypedArray(R.array.imagesArray);

        int randomNumber = (int)(Math.random()*((2-0)+1));
        int card = images.getResourceId(randomNumber, randomNumber);
        return card;
    }

       // final TypedArray imgs = getResources().obtainTypedArray(R.array.imagesArray);
       // final Random rand = new Random();
       // final int rndInt = rand.nextInt(imgs.length());
       // final int resID = imgs.getResourceId(rndInt, 0);

        //ImageView imgView = (ImageView)findViewById(R.id.imgvQuestion);
        //imgView.setImageResource(rndInt);

        //this code displays an image.
        //Resources res = getResources();
       // TypedArray imgArray = res.obtainTypedArray(R.array.imagesArray);
        //Drawable drawable = imgArray.getDrawable(0);
        //ImageView imgView = (ImageView)findViewById(R.id.imgvQuestion);
       // imgView.setImageResource(imgArray.getResourceId(0,3));
    }
