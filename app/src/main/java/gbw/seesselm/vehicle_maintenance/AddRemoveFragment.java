package gbw.seesselm.vehicle_maintenance;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.text.NumberFormat;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


import android.view.View.OnKeyListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView.OnEditorActionListener;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;


import android.view.KeyEvent;



public class AddRemoveFragment extends Fragment
        implements OnEditorActionListener,
        OnCheckedChangeListener, OnKeyListener{

    private SharedPreferences saved_ar;
    private String VIN;
    private TextView status;
    private int error; //0=valid,1=length,2=checkdigit
    private final int[] weight={8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2};

    private TextView vin_1;
    private TextView vin_2;
    private TextView vin_3;
    private TextView vin_4;
    private TextView vin_5;
    private TextView vin_6;
    private TextView vin_7;
    private TextView vin_8;
    private TextView vin_10;
    private TextView vin_11;

    private TextView out_1;
    private TextView out_2;
    private TextView out_3;
    private TextView out_4;
    private TextView out_5;
    private TextView out_6;
    private TextView out_7;
    private TextView out_8;
    private TextView out_10;
    private TextView out_11;

    private EditText vin_in;
    private TextView text_top;

    private RadioGroup radioGroup;
    private RadioButton radio_1980;
    private RadioButton radio_2010;

    private final int pre2010=0;
    private final int post2010=1;

    private int year=pre2010;


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
        vin_in=(EditText) view.findViewById(R.id.vin);
        text_top=(TextView) view.findViewById(R.id.text_top);

        vin_1=(TextView) view.findViewById(R.id.vin_1);
        vin_2=(TextView) view.findViewById(R.id.vin_2);
        vin_3=(TextView) view.findViewById(R.id.vin_3);
        vin_4=(TextView) view.findViewById(R.id.vin_4);
        vin_5=(TextView) view.findViewById(R.id.vin_5);
        vin_6=(TextView) view.findViewById(R.id.vin_6);
        vin_7=(TextView) view.findViewById(R.id.vin_7);
        vin_8=(TextView) view.findViewById(R.id.vin_8);
        vin_10=(TextView) view.findViewById(R.id.vin_10);
        vin_11=(TextView) view.findViewById(R.id.vin_11);

        out_1=(TextView) view.findViewById(R.id.out_1);
        out_2=(TextView) view.findViewById(R.id.out_2);
        out_3=(TextView) view.findViewById(R.id.out_3);
        out_4=(TextView) view.findViewById(R.id.out_4);
        out_5=(TextView) view.findViewById(R.id.out_5);
        out_6=(TextView) view.findViewById(R.id.out_6);
        out_7=(TextView) view.findViewById(R.id.out_7);
        out_8=(TextView) view.findViewById(R.id.out_8);
        out_10=(TextView) view.findViewById(R.id.out_10);
        out_11=(TextView) view.findViewById(R.id.out_11);

        status=(TextView) view.findViewByID(R.id.status);

        radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup);
        radio_1980=(RadioButton) view.findViewById(R.id.radio_1980);
        radio_2010=(RadioButton) view.findViewById(R.id.radio_2010);

        vin_in.setOnEditorActionListener(this);
        vin_in.setOnKeyListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.setOnKeyListener(this);



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
       // editor.putInt("output_ar", vin_in);
        editor.commit();
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();

        //vin_in=saved_ar.getInt("vin_in",5);
        DrawDisplay();
    }

    public void DrawDisplay()
    {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED)
        {
            testVin();
        }
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_1980:
                year = pre2010;
                break;
            case R.id.radio_2010:
                year = post2010;
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event)
    {
        switch(keyCode)
        {
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_DPAD_CENTER:

                testVin();
                InputMethodManager input = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(vin_in.getWindowToken(), 0);

                return true;

        }
        return false;
    }

    public void drawDisplay()
    {

    }


    public void testVin()
    {
        String test;
        test=vin_in.getText().toString();
        if(test.length()==17)
        {
            checksum();
        }
        else
        {
            error=1;
            drawDisplay();
        }
    }
//1=A,J  2=B,K,S 3=C,L,T 4=D,M,U 5=E,N,V 6=F,W 7=G,P,X 8=H,Y 9=R,Z
    public void checksum()
    {
        String in;
        int[] out=new int[17];
        in=vin_in.getText().toString();
        for(int i=0;i<in.length();i++)
        {
            switch(in.charAt(i))
            {
                case '1':
                case 'A':
                case 'J':
                    out[i]=1;
                    break;
                case '2':
                case 'B':
                case 'K':
                case 'S':
                    out[i]=2;
                    break;
                case '3':
                case 'C':
                case 'L':
                case 'T':
                    out[i]=3;
                    break;
                case '4':
                case 'D':
                case 'M':
                case 'U':
                    out[i]=4;
                    break;

            }
        }


    }



}
