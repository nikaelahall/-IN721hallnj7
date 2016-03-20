package bit.hallnj7.screencontrols;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Nikaela on 21/03/2016.
 */
public class AlertBuilderFragment extends DialogFragment
{
    public AlertBuilderFragment() {}

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setIcon(R.drawable.music);
        builder.setTitle("Really enrol?");
        builder.setPositiveButton("Confirm", new YesButtonHandler());
        builder.setNegativeButton("Cancel", new NoButtonHandler());

        Dialog customDialog = builder.create();

        return customDialog;
    }

    public class YesButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(true);
        }
    }

    public class NoButtonHandler implements  DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity myActivity = (MainActivity) getActivity();
            myActivity.giveMeMyData(false);
        }
    }
}

