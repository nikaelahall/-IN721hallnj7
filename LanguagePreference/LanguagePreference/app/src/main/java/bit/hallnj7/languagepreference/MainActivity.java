package bit.hallnj7.languagepreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLanguage = (Button)findViewById(R.id.btnSetLanguage);
        btnLanguage.setOnClickListener(new SetLanguageClickHandler());

        prefs = getSharedPreferences("prefsDemo", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        //fetch the stored langauge preference
        //Returns null if the provided key-value pair hasn't been set
        String languagePreference = prefs.getString("language", null);
        if (languagePreference != null)
        {
            String greetingInChosenLanguage = getGreeting(languagePreference);

            TextView tvGreeting = (TextView)findViewById(R.id.tvChosenLanguage);
            tvGreeting.setText(greetingInChosenLanguage);
        }

        String colourPreference = prefs.getString("chosenColor", null);
        if(colourPreference != null)
        {

        }
    }

    public class SetLanguageClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Find out which language radio button is selected
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
            int checkedId = rg.getCheckedRadioButtonId();
            RadioButton checkedButton = (RadioButton) findViewById(checkedId);
            String checkedLanguage = checkedButton.getText().toString();

            //store new chosen language in prefs
            prefsEditor.putString("language", checkedLanguage);
            prefsEditor.commit();

            String languagePreference = prefs.getString("language", null);
            if (languagePreference != null)
            {
                String greetingInChosenLanguage = getGreeting(languagePreference);

                TextView tvGreeting = (TextView)findViewById(R.id.tvChosenLanguage);
                tvGreeting.setText(greetingInChosenLanguage);
            }
        }
    }

        private String getGreeting(String language)
        {
            String greeting = "";

            switch (language)
            {
                case("French"):
                    greeting = "Bonjour Le Monde";
                    break;
                case("German"):
                    greeting = "Hallo Welt";
                    break;
                case("Spanish"):
                    greeting = "Hola Mundo";
                    break;
                default:
                    break;
            }

            return greeting;
        }

    private String getColor(String colorChosen)
    {
        String ChosenColor = "";

        switch (colorChosen)
        {
            case("Blue"):
                ChosenColor = "hi" ;
                break;
        }

        return ChosenColor;
    }
}
