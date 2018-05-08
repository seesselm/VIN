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
import android.widget.Toast;




public class ViewFragment extends Fragment implements OnClickListener
{
    private TextView DisplayCar;
    private Button btnDispEdit, btnDispDel;

    private SharedPreferences savev;

    public ViewFragment()
    {

    }

    public static ViewFragment newInstance()
    {
        ViewFragment frag = new ViewFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savevInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        btnDispEdit = view.findViewById(R.id.btnDispEdit);
        btnDispDel = view.findViewById(R.id.btnDispDel);
        DisplayCar = view.findViewById(R.id.DisplayCar);

        btnDispEdit.setOnClickListener(this);
        btnDispDel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savevInstanceState)
    {
        super.onCreate(savevInstanceState);

        savev = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public void onPause()
    {
        Editor editor = savev.edit();
        editor.putString("DisplayCar",DisplayCar.getText().toString());
        editor.commit();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
        String disp = savev.getString("DisplayCar","");
        DisplayCar.append("\n"+disp);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnDispDel:
                //
                break;
            case R.id.btnDispEdit:
                //
                break;
        }
    }
}
