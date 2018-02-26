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



public class NotesFragment extends Fragment implements OnClickListener{

    private TextView output_n;
    private Button minus_n;
    private Button plus_n;

    private SharedPreferences saved_n;
    private int outval_n = 5;

    public NotesFragment()
    {
        // Required empty public constructor
    }


    public static NotesFragment newInstance()
    {
        NotesFragment frag = new NotesFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saved_nInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        output_n = view.findViewById(R.id.output_n);
        minus_n = view.findViewById(R.id.minus_n);
        plus_n = view.findViewById(R.id.plus_n);

        minus_n.setOnClickListener(this);
        plus_n.setOnClickListener(this);

        return view;
    }
    @Override
    public void onCreate(Bundle saved_nInstanceState)
    {
        super.onCreate(saved_nInstanceState);



        saved_n = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onPause()
    {
        Editor editor = saved_n.edit();
        editor.putInt("output_n", outval_n);
        editor.commit();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();

        outval_n=saved_n.getInt("output_n",5);
        DrawDisplay();
    }

    public void DrawDisplay()
    {
        NumberFormat val = NumberFormat.getIntegerInstance();
        output_n.setText(val.format(outval_n));
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.minus_n:
                outval_n=outval_n-1;
                DrawDisplay();
                break;

            case R.id.plus_n:
                outval_n=outval_n+1;
                DrawDisplay();
                break;
        }
    }



}
