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

        builder.setIcon(R.drawable.placeholder);
        builder.setTitle("Confirm answer?");
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
            questionActivity qActivity = (questionActivity)getActivity();
            qActivity.giveMeMyData(true);
        }
    }

    public class NoButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            questionActivity qActivity = (questionActivity)getActivity();
            qActivity.giveMeMyData(false);
        }
    }
}
