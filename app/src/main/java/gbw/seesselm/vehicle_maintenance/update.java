package gbw.seesselm.vehicle_maintenance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

public class update extends DialogFragment
{
    interface UpdateDialogListener
    {
        void onUpdateButtonClick(DialogFragment dialog);

    }

    UpdateDialogListener updateListener;
    Context context;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            updateListener = (UpdateDialogListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement UpdateDialogListener");
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_update, null))

                // Add action buttons
                .setPositiveButton(R.string.Update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        updateListener.onUpdateButtonClick(update.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        update.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
