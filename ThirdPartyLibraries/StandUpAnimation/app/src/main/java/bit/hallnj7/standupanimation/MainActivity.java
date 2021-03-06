package bit.hallnj7.standupanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidanimations.library.Techniques;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new ButtonClickHandler());


    }

    public class ButtonClickHandler implements View.OnClickListener {
       @Override
      public void onClick(View v)
       {
           YoYo.with(Techniques.StandUp).duration(2000).playOn(v);
      }
    }


  // public class StandUpAnimator extends BaseViewAnimator implements View.OnClickListener {
   //     @Override
     //   public void prepare(View target) {
       //     float x = (target.getWidth() - target.getPaddingLeft() - target.getPaddingRight()) / 2
         //           + target.getPaddingLeft();
          //  float y = target.getHeight() - target.getPaddingBottom();
           // getAnimatorAgent().playTogether(
             //       ObjectAnimator.ofFloat(target, "pivotX", x, x, x, x, x),
               //     ObjectAnimator.ofFloat(target, "pivotY", y, y, y, y, y),
                //    ObjectAnimator.ofFloat(target, "rotationX", 55, -30, 15, -15, 0)
          //  );
        //}


}
