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



public class HomeFragment extends Fragment implements OnClickListener{

    private TextView output_h;
    private Button minus_h;
    private Button plus_h;

    private SharedPreferences saved_h;
    private int outval_h = 5;

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

        output_h = view.findViewById(R.id.output_h);
        minus_h = view.findViewById(R.id.minus_h);
        plus_h = view.findViewById(R.id.plus_h);

        minus_h.setOnClickListener(this);
        plus_h.setOnClickListener(this);

        return view;
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
        editor.putInt("output_h", outval_h);
        editor.commit();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();

        outval_h=saved_h.getInt("output_h",5);
        DrawDisplay();
    }

    public void DrawDisplay()
    {
        NumberFormat val = NumberFormat.getIntegerInstance();
        output_h.setText(val.format(outval_h));
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.minus_h:
                outval_h=outval_h-1;
                DrawDisplay();
                break;

            case R.id.plus_h:
                outval_h=outval_h+1;
                DrawDisplay();
                break;
        }
    }



}
