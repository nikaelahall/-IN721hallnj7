package bit.hallnj7.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnImageFragment = (Button) findViewById(R.id.btnImage);
        Button btnListFragment = (Button) findViewById(R.id.btnListView);
        btnListFragment.setOnClickListener(new ButtonClickListHandler());
        btnImageFragment.setOnClickListener(new ButtonClickImageHandler());
    }

    public class ButtonClickImageHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Fragment dynamicFragment = new ImageView();
            FragmentManager fragmentManager = getFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_image, dynamicFragment);
            fragmentTransaction.commit();
        }
    }

    public class ButtonClickListHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            Fragment dynamicFragment = new ListViewFragment();
            FragmentManager fragmentManager = getFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_image, dynamicFragment);
            fragmentTransaction.commit();
        }
    }
}
