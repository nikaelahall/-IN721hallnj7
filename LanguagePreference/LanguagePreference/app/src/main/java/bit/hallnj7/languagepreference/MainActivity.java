package bit.hallnj7.languagepreference;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
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

        String languagePreference = prefs.getString("language", null);
        String colorPreference = prefs.getString("colorWanted", null);

        if ((languagePreference != null) && (colorPreference != null))
        {
            String greetingInChosenLanguage = getGreeting(languagePreference);
            int greetingInChosenColor = getChosenColor(colorPreference);

            TextView tvGreeting = (TextView)findViewById(R.id.tvChosenLanguage);
            tvGreeting.setText(greetingInChosenLanguage);
            tvGreeting.setTextColor(greetingInChosenColor);
        }
    }




    public class SetLanguageClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Find out which language radio button is selected
            RadioGroup LangRg = (RadioGroup) findViewById(R.id.radioGroup1);
            int checkedId = LangRg.getCheckedRadioButtonId();
            RadioButton checkedButton = (RadioButton) findViewById(checkedId);
            String checkedLanguage = checkedButton.getText().toString();

            RadioGroup ColRg = (RadioGroup)findViewById(R.id.rgColor);
            int checkedColorId = ColRg.getCheckedRadioButtonId();
            RadioButton checkedRadio = (RadioButton)findViewById(checkedColorId);
            String checkedColor = checkedRadio.getText().toString();

            //store new chosen language in prefs
            prefsEditor.putString("colorWanted", checkedColor);
            prefsEditor.putString("language", checkedLanguage);
            prefsEditor.commit();

            String languagePreference = prefs.getString("language", null);
            String colorPreference = prefs.getString("colorWanted", null);
            if ((languagePreference != null) && (colorPreference != null))
            {
                String greetingInChosenLanguage = getGreeting(languagePreference);
                int greetingInChosenColor = getChosenColor(colorPreference);

                TextView tvGreeting = (TextView)findViewById(R.id.tvChosenLanguage);
                tvGreeting.setText(greetingInChosenLanguage);
                tvGreeting.setTextColor(greetingInChosenColor);
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




    private int getChosenColor(String colorWanted)
   {
        int chosenColor = 0;

        switch (colorWanted)
        {
            case ("Green"): 
                chosenColor = ContextCompat.getColor(this, R.color.Green);
                break;
            case ("Blue"):
                chosenColor = ContextCompat.getColor(this, R.color.colorPrimary);
               break;
           case ("Red"):
               chosenColor = ContextCompat.getColor(this, R.color.Red);
                break;
       }

        return chosenColor;
    }
}
