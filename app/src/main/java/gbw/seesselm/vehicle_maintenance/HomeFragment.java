package gbw.seesselm.vehicle_maintenance;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.app.DialogFragment;
import android.widget.EditText;
import android.widget.Toast;


public class HomeFragment extends Fragment implements OnClickListener
{



    private Button btnAdd;
    private Button btnDel;
    private Button btnUpdate;
    private Button btnNotes;
    private Button btnVin;
    private Button btnAll;
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
                             Bundle save_hInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        db = new DBHandler(getActivity());

        btnAdd=view.findViewById(R.id.btnAdd);
        btnDel=view.findViewById(R.id.btnDel);
        btnUpdate=view.findViewById(R.id.btnUpdate);
        btnNotes=view.findViewById(R.id.btnNotes);
        btnVin=view.findViewById(R.id.btnVin);
        btnAll=view.findViewById(R.id.btnAll);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnNotes.setOnClickListener(this);
        btnVin.setOnClickListener(this);
        btnAll.setOnClickListener(this);

        return view;
    }

    /*@Override
    public void onSaveButtonClick(DialogFragment dialog)
    {
        EditText entryMake = (EditText) dialog.getDialog().findViewById(R.id.edtMake);
        String make= entryMake.getText().toString();

        EditText entryModel = (EditText) dialog.getDialog().findViewById(R.id.edtModel);
        String model = entryModel.getText().toString();

        EditText entryYear = (EditText) dialog.getDialog().findViewById(R.id.edtYear);
        int year = Integer.parseInt(entryYear.getText().toString());

        EditText entryVIN = (EditText) dialog.getDialog().findViewById(R.id.edtVIN);
        String vin = entryVIN.getText().toString();

        boolean chkMake = checkMake(make);
        boolean chkModel = checkModel(model);
        boolean chkYear = checkYear(year);
        if(!chkMake || !chkModel || !chkYear)
            Toast.makeText(getActivity().getApplicationContext(),"Double check information",Toast.LENGTH_LONG);
        else
        {
            db.addCar(new Car(make,model,year,vin));
            Toast.makeText(getActivity().getApplicationContext(),"Success",Toast.LENGTH_LONG);
        }
    }

    public boolean checkMake(String make)
    {
        if(make == "")
            return false;
        else
            return true;
    }

    public boolean checkModel(String model)
    {
        if(model == "")
            return false;
        else
            return true;
    }

    public boolean checkYear(int year)
    {
        if(year<0)
            return false;
        else
            return true;
    }*/

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
                Add adddialog=new Add();
                adddialog.show(getActivity().getFragmentManager(), TAG);
                break;

            case R.id.btnDel:

                break;

            case R.id.btnAll:
                //getActivity().getFragmentManager()
                break;

            case R.id.btnVin:
                //Do View
                break;

            case R.id.btnUpdate:
                update updialog=new update();
                updialog.show(getActivity().getFragmentManager(), TAG);
                Toast.makeText(getActivity().getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                break;

            case R.id.btnNotes:
                //Do notes
                break;
        }
    }



}
