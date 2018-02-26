package gbw.seesselm.vehicle_maintenance;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.text.NumberFormat;



public class AddRemoveFragment extends Fragment implements OnClickListener{

    private TextView output_ar;
    private Button minus_ar;
    private Button plus_ar;

    private SharedPreferences saved_ar;
    private int outval_ar = 5;

    public AddRemoveFragment()
    {
        // Required empty public constructor
    }


    public static AddRemoveFragment newInstance()
    {
        AddRemoveFragment frag = new AddRemoveFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saved_arInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_remove, container, false);

        output_ar = view.findViewById(R.id.output_ar);
        minus_ar = view.findViewById(R.id.minus_ar);
        plus_ar = view.findViewById(R.id.plus_ar);

        minus_ar.setOnClickListener(this);
        plus_ar.setOnClickListener(this);

        return view;
    }
    @Override
    public void onCreate(Bundle saved_arInstanceState)
    {
        super.onCreate(saved_arInstanceState);



        saved_ar = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onPause()
    {
        Editor editor = saved_ar.edit();
        editor.putInt("output_ar", outval_ar);
        editor.commit();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();

        outval_ar=saved_ar.getInt("output_ar",5);
        DrawDisplay();
    }

    public void DrawDisplay()
    {
        NumberFormat val = NumberFormat.getIntegerInstance();
        output_ar.setText(val.format(outval_ar));
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.minus_ar:
                outval_ar=outval_ar-1;
                DrawDisplay();
                break;

            case R.id.plus_ar:
                outval_ar=outval_ar+1;
                DrawDisplay();
                break;
        }
    }



}
