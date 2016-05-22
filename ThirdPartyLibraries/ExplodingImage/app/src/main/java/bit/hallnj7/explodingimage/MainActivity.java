package bit.hallnj7.explodingimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    Button btnAnimate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnimate = (Button)findViewById(R.id.btnAnimate);
        btnAnimate.setOnClickListener(new ButtonClickHandler());

        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public class ButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            new ExplodeAnimation(imageView).animate();
        }
    }
}
