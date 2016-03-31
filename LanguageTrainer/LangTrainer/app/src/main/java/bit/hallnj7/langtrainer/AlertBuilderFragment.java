package bit.hallnj7.langtrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertBuilderFragment extends DialogFragment
{
    public AlertBuilderFragment() {}

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        questionActivity question = new questionActivity();

        String chosenAns = getArguments().getString("chosenAns"); //gets the text of the chosen answer

        builder.setIcon(R.drawable.placeholder); //sets the fragment icon image
        builder.setTitle("Confirm answer of " + chosenAns + " ?");
        builder.setPositiveButton("Confirm", new YesButtonHandler()); //sets the positive confirm button
        builder.setNegativeButton("Cancel", new NoButtonHandler()); //sets the negative cancel button


        Dialog customDialog = builder.create();

        return customDialog;
    }

    public class YesButtonHandler implements DialogInterface.OnClickListener //onCLick listener for the yes button
    {
        @Override
        public void onClick(DialogInterface dialog, int which) //if the Confirm button is pressed
        {
            questionActivity qActivity = (questionActivity)getActivity();
            qActivity.giveMeMyData(true); //run the giveMeMyData method
        }
    }

    public class NoButtonHandler implements DialogInterface.OnClickListener //onCLickListener for the no button
    {
        @Override
        public void onClick(DialogInterface dialog, int which) //if the Cancel button is pressed
        {
            questionActivity qActivity = (questionActivity)getActivity();
            qActivity.giveMeMyData(false); //do not run the giveMeMyData method
        }
    }
}
