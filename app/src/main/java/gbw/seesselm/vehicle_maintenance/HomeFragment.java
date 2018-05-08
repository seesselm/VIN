package gbw.seesselm.vehicle_maintenance;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.text.NumberFormat;
import android.support.v4.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.app.DialogFragment;
import android.content.Intent;




public class HomeFragment extends Fragment implements OnClickListener, Add.AddDialogListener
{



    private Button btnAdd, btnDel, btnUpdate, btnNotes, btnVin, btnAll;
    private String TAG = "carProj";
    SQLiteDatabase datab;
    DBHandler db;

    private SharedPreferences saved_h;

    public HomeFragment()
    {
        // Required empty public constructor
    }


    public static HomeFragment newInstance()
    {
        HomeFragment frag = new HomeFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saved_hInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnAdd=view.findViewById(R.id.btnAdd);
        btnDel=view.findViewById(R.id.btnDel);
        btnUpdate=view.findViewById(R.id.btnUpdate);
        btnNotes=view.findViewById(R.id.btnNotes);
        btnVin=view.findViewById(R.id.btnVin);
        btnAll=view.findViewById(R.id.btnAll);


        return view;
    }

    public boolean onSaveButtonClick(DialogFragment dialog)
    {
        //EditText
    }

    @Override
    public void onCreate(Bundle saved_hInstanceState)
    {
        super.onCreate(saved_hInstanceState);
        saved_h = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onPause()
    {
        Editor editor = saved_h.edit();
        editor.commit();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
        DrawDisplay();
    }

    public void DrawDisplay()
    {

    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAdd:
                Add dialog=new Add();
                dialog.show(getFragmentManager(), TAG);
                break;

            case R.id.btnDel:
                //Do Delete
                break;

            case R.id.btnAll:
                //Do All
                break;

            case R.id.btnVin:
                //Do View
                break;

            case R.id.btnUpdate:
                //Do Update
                break;

            case R.id.btnNotes:
                //Do notes
                break;
        }
    }



}
