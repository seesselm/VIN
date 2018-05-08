package gbw.seesselm.vehicle_maintenance;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Add.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Add#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add extends DialogFragment
{
    interface AddDialogListener
    {
        void onSaveButtonClick(DialogFragment dialog);
    }

    AddDialogListener addListener;
    Context context;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        try
        {
            addListener = (AddDialogListener) activity;
        }

        catch(ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement AddDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_add, null))
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addListener.onSaveButtonClick(Add.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Add.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
