package bit.hallnj7.langtrainer;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class welcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new changeToQuestionPageHandler());

        TextView text = (TextView) findViewById(R.id.tvSettext);
    }

    class changeToQuestionPageHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            Intent changeActivityIntent = new Intent(welcomeScreen.this, questionClass.class);
            startActivity(changeActivityIntent);
        }
    }
}
