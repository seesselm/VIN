package gbw.seesselm.vehicle_maintenance;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.view.View.OnKeyListener;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;




public class AddRemoveFragment extends Fragment
        implements OnEditorActionListener,
        OnCheckedChangeListener, OnKeyListener{

    private SharedPreferences saved_ar;
    private String VIN;
    private String VDS;
    private String VIS;
    private TextView status;
    private int error; //0=valid,1=length,2=checkdigit
    private final int[] weight={8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2};
    private boolean twodoor=false;
    private boolean truck=false;

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

        status=(TextView) view.findViewById(R.id.status);

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

        if (year == pre2010)
        {
            radio_1980.setChecked(true);
        }
        else if (year == post2010)
        {
            radio_2010.setChecked(true);
        }


        drawDisplay();
    }

    public void drawDisplay()
    {
        switch(error)
        {
            case 0:
                status.setText("All clear");
                break;
            case 1:
                status.setText("Invalid length");
                break;
            case 2:
                status.setText("Invalid check");
                break;
            case 3:
                //status.setText("DONE");
                String test= VIN.substring(0,3);
                status.setText(test);
                break;
        }
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

    public void checksum()
    {
        String in;
        int[] out=new int[17];
        in=vin_in.getText().toString();
        int checkdig;
        if(in.charAt(8)=='X')
            checkdig=10;
        else
            checkdig=Character.getNumericValue(in.charAt(8));
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
                case '5':
                case 'E':
                case 'N':
                case 'V':
                    out[i]=5;
                    break;
                case '6':
                case 'F':
                case 'W':
                    out[i]=6;
                    break;
                case '7':
                case 'G':
                case 'P':
                case 'X':
                    out[i]=7;
                    break;
                case '8':
                case 'H':
                case 'Y':
                    out[i]=8;
                    break;
                case '9':
                case 'R':
                case 'Z':
                    out[i]=9;
                    break;

            }


        }

        int precheck=0;
        for(int j=0;j<weight.length;j++)
        {
            precheck+=(weight[j]*out[j]);
        }
        int isvalid=precheck%11;
        if(checkdig==isvalid)
        {
            VIN=vin_in.getText().toString();
            decryptVin();
        }
        else
        {
            error = 2;
            drawDisplay();
        }


    }

    public void decryptVin()
    {
        String WMI=VIN.substring(0,3);

        vin_1.setText(Character.toString(VIN.charAt(0)));
        vin_2.setText(Character.toString(VIN.charAt(1)));
        vin_3.setText(Character.toString(VIN.charAt(2)));
        vin_4.setText(Character.toString(VIN.charAt(3)));
        vin_5.setText(Character.toString(VIN.charAt(4)));
        vin_6.setText(Character.toString(VIN.charAt(5)));
        vin_7.setText(Character.toString(VIN.charAt(6)));
        vin_8.setText(Character.toString(VIN.charAt(7)));
        vin_10.setText(Character.toString(VIN.charAt(9)));
        vin_11.setText(Character.toString(VIN.charAt(10)));



        switch(WMI)//VIN 1-3
        {
            case "1G1":
            case "1G2":
            case "1G3":
            case "1G4":
            case "1G5":
            case "1G6":
            case "1G8":
            case "5Y2":
                out_1.setText("United States");
                out_2.setText("GM");
                decryptGM();
                break;
            case "2G1":
            case "2G2":
            case "2G3":
            case "2G4":
                out_1.setText("Canada");
                out_2.setText("GM");
                decryptGM();
                break;
            case "3G1":
            case "3G2":
            case "3G4":
                out_1.setText("Mexico");
                out_2.setText("GM");
                decryptGM();
                break;
            default:
                out_1.setText(Character.toString(VIN.charAt(0)));
                out_2.setText(Character.toString(VIN.charAt(1)));
                out_3.setText(Character.toString(VIN.charAt(2)));
                break;


        }
        error=3;
        drawDisplay();
    }

    public void decryptYearPre2010()
    {
        switch(VIN.charAt(9))
        {
            case 'A':
            case '0':
                out_10.setText("1980");
                break;
            case 'B':
                out_10.setText("1981");
                break;
            case 'C':
                out_10.setText("1982");
                break;
            case 'D':
                out_10.setText("1983");
                break;
            case 'E':
                out_10.setText("1984");
                break;
            case 'F':
                out_10.setText("1985");
                break;
            case 'G':
                out_10.setText("1986");
                break;
            case 'H':
                out_10.setText("1987");
                break;
            case 'J':
                out_10.setText("1988");
                break;
            case 'K':
                out_10.setText("1989");
                break;
            case 'L':
                out_10.setText("1990");
                break;
            case 'M':
                out_10.setText("1991");
                break;
            case 'N':
                out_10.setText("1992");
                break;
            case 'P':
                out_10.setText("1993");
                break;
            case 'R':
                out_10.setText("1994");
                break;
            case 'S':
                out_10.setText("1995");
                break;
            case 'T':
                out_10.setText("1996");
                break;
            case 'V':
                out_10.setText("1997");
                break;
            case 'W':
                out_10.setText("1998");
                break;
            case 'X':
                out_10.setText("1999");
                break;
            case 'Y':
                out_10.setText("2000");
                break;
            default:
                out_10.setText(Character.toString(VIN.charAt(9)));
                break;


        }
    }

    public void decryptYearPost2010()
    {
        switch(VIN.charAt(9))
        {
            case '1':
                out_10.setText("2001");
                break;
            case '2':
                out_10.setText("2002");
                break;
            case '3':
                out_10.setText("2003");
                break;
            case '4':
                out_10.setText("2004");
                break;
            case '5':
                out_10.setText("2005");
                break;
            case '6':
                out_10.setText("2006");
                break;
            case '7':
                out_10.setText("2007");
                break;
            case '8':
                out_10.setText("2008");
                break;
            case '9':
                out_10.setText("2009");
                break;
            case 'A':
                out_10.setText("2010");
                break;
            case 'B':
                out_10.setText("2011");
                break;
            case 'C':
                out_10.setText("2012");
                break;
            case 'D':
                out_10.setText("2013");
                break;
            case 'E':
                out_10.setText("2014");
                break;
            case 'F':
                out_10.setText("2015");
                break;
            case 'G':
                out_10.setText("2016");
                break;
            case 'H':
                out_10.setText("2017");
                break;
            case 'I':
                out_10.setText("2018");
                break;
            case 'J':
                out_10.setText("2019");
                break;
            case 'K':
                out_10.setText("2020");
                break;
            case 'L':
                out_10.setText("2021");
                break;
            default:
                out_10.setText(Character.toString(VIN.charAt(9)));
                break;


        }
    }

    public void decryptGM()
    {
        switch(VIN.charAt(3))//VIN 4+5
        {
            case 'C':
                decryptGM_C();
                break;
            case 'D':
                decryptGM_D();
                break;
            case 'E':
                decryptGM_E();
                break;
            case 'F':
                decryptGM_F();
                break;
            /*case 'G':
                decryptGM_G();
                break;
            case 'H':
                decryptGM_H();
                break;
            case 'J':
                decryptGM_J();
                break;
            case 'K':
                decryptGM_K();
                break;
            case 'L':
                decryptGM_L();
                break;
            case 'M':
                decryptGM_M();
                break;
            case 'N':
                decryptGM_N();
                break;
            case 'P':
                decryptGM_P();
                break;
            case 'S':
                decryptGM_S();
                break;
            case 'V':
                decryptGM_V();
                break;
            case 'W':
                decryptGM_W();
                break;
            case 'Y':
                decryptGM_Y();
                break;
            case 'Z':
                decryptGM_Z();
                break;
                */
            default:
                out_4.setText(Character.toString(VIN.charAt(3)));
                out_5.setText(Character.toString(VIN.charAt(4)));
                break;

        }

        switch(VIN.charAt(5))//VIN 6
        {
            case '1':
                out_6.setText("Two Door Coupe");
                break;
            case '2':
                out_6.setText("Two Door");
                break;
            case '3':
                out_6.setText("Two Door Convertible");
                break;
            case '5':
                out_6.setText("Four Door Sedan");
                break;
            case '6':
                out_6.setText("Four Door Sedan Hatchback");
                break;
            case '8':
                if(twodoor)
                {
                    out_6.setText("Two Door Hatchback");
                }
                else
                {
                    out_6.setText("Four Door Station Wagon");
                }
                break;
            case '9':
                out_6.setText("Four Door Station Wagon");
                break;
            default:
                out_6.setText(Character.toString(VIN.charAt(5)));
                break;
        }

        switch(VIN.charAt(6))//VIN 7
        {
            default:
                out_7.setText(Character.toString(VIN.charAt(6)));
                break;
        }

        switch(VIN.charAt(7))//VIN 8
        {
            default:
                out_8.setText(Character.toString(VIN.charAt(7)));
            /*if(truck)
            {
                decryptGM_Truck();
            }
            else
            {
                decryptGM_Passenger();
            }
            */

        }

        if(year==pre2010)//VIN 10
            decryptYearPre2010();
        else if(year==post2010)
            decryptYearPost2010();

        out_11.setText(Character.toString(VIN.charAt(10)));



    }

    public void decryptGM_C()
    {
        twodoor=false;
        truck=false;
        switch(VIN.charAt(4))
        {
            case 'B':
                out_4.setText("Cadillac");
                out_5.setText("Fleetwood");
                break;
            case 'D':
            case 'T':
                out_4.setText("Cadillac");
                out_5.setText("DeVille");
                break;
            case 'G':
                out_4.setText("Cadillac");
                out_5.setText("Sexty Special");
                break;
            case 'W':
                out_4.setText("Buick");
                out_5.setText("Park Avenue");
                break;
            default:
                out_4.setText("C BODY");
                out_5.setText(Character.toString(VIN.charAt(4)));
                break;

        }
    }

    public void decryptGM_D()
    {
        twodoor=false;
        truck=false;
        switch(VIN.charAt(4))
        {
            case 'G':
            case 'M':
            case 'P':
            case 'R':
            case 'U':
                out_4.setText("Cadillac");
                out_5.setText("CTS");
                break;
            case 'V':
                out_4.setText("Buick");
                out_5.setText("Terraza");
                break;
            case 'W':
                out_4.setText("Buick");
                out_5.setText("Brougham");
                break;
            default:
                out_4.setText("D Body");
                out_5.setText(Character.toString(VIN.charAt(4)));
                break;
        }
    }

    public void decryptGM_E()
    {
        twodoor=true;
        truck=false;
        switch(VIN.charAt(4))
        {
            case 'C':
                out_4.setText("Pontiac");
                out_5.setText("G8");
                break;
            case 'L':
            case 'T':
                out_4.setText("Cadillac");
                out_5.setText("Eldorado");
                break;
            case 'Z':
                out_4.setText("Buick");
                out_4.setText("Riviera");
                break;
            default:
                out_4.setText("E BODY");
                out_5.setText(Character.toString(VIN.charAt(4)));
                break;
        }
    }

    public void decryptGM_F()
    {
        twodoor=true;
        truck=false;
        switch(VIN.charAt(4))
        {
            case 'P':
                out_4.setText("Chevrolet");
                out_5.setText("Camaro");
                break;
            case 'S':
                switch(VIN.charAt(2))
                {
                    case '1':
                        out_4.setText("Chevrolet");
                        out_5.setText("Camaro Berlinetta");
                        break;
                    case '2':
                        out_4.setText("Pontiac");
                        out_5.setText("Firebird");
                        break;
                    default:
                        out_4.setText("F Body");
                        out_5.setText("S Series");
                        break;
                }
            case 'V':
            case 'W':
                out_4.setText("Pontiac");
                out_5.setText("Firebird Trans Am");
                break;
            case 'X':
                out_4.setText("Pontiac");
                out_5.setText("Firebird SE");
                break;
            default:
                out_4.setText("F BODY");
                out_5.setText(Character.toString(VIN.charAt(4)));
                break;
        }
    }



}

