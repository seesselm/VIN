package gbw.seesselm.vehicle_maintenance;

import java.util.List;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ArrowKeyMovementMethod;
import android.view.Display;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements Add.AddDialogListener,
        update.UpdateDialogListener
{

    private SharedPreferences prefs;
    private String TAG = "carProj";
    SQLiteDatabase datab;
    private TextView DisplayCars;
    DBHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem select)
            {
                Fragment selectfrag = null;
                switch (select.getItemId()) {
                    case R.id.navigation_home:
                        selectfrag = HomeFragment.newInstance();
                        break;
                    case R.id.navigation_view:
                        displayDatabase();
                        selectfrag = ViewFragment.newInstance();
                        break;
                }
                FragmentTransaction fragtran = getSupportFragmentManager().beginTransaction();
                fragtran.replace(R.id.FrameLayout,selectfrag);
                fragtran.commit();
                return true;
            }
        });
        FragmentTransaction fragtran = getSupportFragmentManager().beginTransaction();
        fragtran.replace(R.id.FrameLayout,HomeFragment.newInstance());
        fragtran.commit();

    }

    public void displayDatabase()
    {
        DisplayCars = (TextView) findViewById(R.id.DisplayCar);
        DisplayCars.setText("");
        DisplayCars.setPadding(5,2,5,2);

        List<Car> carList = db.getAllCars();
        for(Car car : carList)
        {
            String carDetail = "\n\nValue:"+car.getDbNum()+"\n\tMake:"
                    +car.getMake()+"\n\tModel:"+car.getModel()+
                    "Year:"+car.getYear()+"Vin:"+car.getVIN();
            DisplayCars.append("\n"+carDetail);
        }

    }

    @Override
    public void onSaveButtonClick(DialogFragment dialog)
    {
        EditText entryMake = (EditText) dialog.getDialog().findViewById(R.id.edtMake);
        String make= entryMake.getText().toString();

        EditText entryModel = (EditText) dialog.getDialog().findViewById(R.id.edtModel);
        String model = entryModel.getText().toString();

        EditText entryYear = (EditText) dialog.getDialog().findViewById(R.id.edtYear);
        String syear = entryYear.getText().toString();
        int year;
        if(syear.length()==0)
        {
            year=0;
        }
        else
            year=Integer.parseInt(syear);

        EditText entryVIN = (EditText) dialog.getDialog().findViewById(R.id.edtVIN);
        String vin = entryVIN.getText().toString();

        System.out.println(make+model+year+vin);

        boolean chkMake = checkMake(make);
        boolean chkModel = checkModel(model);
        if(chkMake==false || chkModel==false)
            Toast.makeText(getApplicationContext(),"Double check information",Toast.LENGTH_LONG).show();
        else
        {
            db.addCar(new Car(make,model,year,vin));
            Toast.makeText(getApplicationContext(),make,Toast.LENGTH_LONG).show();
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
    }

    @Override
    public void onUpdateButtonClick(DialogFragment dialog)
    {
        EditText updateID = (EditText) dialog.getDialog().findViewById(R.id.edtUId);
        String sId = updateID.getText().toString();
        int iId = Integer.parseInt(sId);

        EditText updateMake = (EditText) dialog.getDialog().findViewById(R.id.edtUmake);
        String uMake = updateMake.getText().toString();

        EditText updateModel = (EditText) dialog.getDialog().findViewById(R.id.edtUmodel);
        String uModel = updateModel.getText().toString();

        EditText updateYear = (EditText) dialog.getDialog().findViewById(R.id.edtUyear);
        String uYear = updateYear.getText().toString();
        int iYear = Integer.parseInt(uYear);

        EditText updateVin = (EditText) dialog.getDialog().findViewById(R.id.edtUvin);
        String uVin = updateVin.getText().toString();

        boolean checkID = checkId(sId);

        if(checkID==false)
            Toast.makeText(getApplicationContext(),"Enter ID again",Toast.LENGTH_LONG).show();
        else
        {
            boolean checkUpdate = db.updateCarInfo(iId, uMake, uModel, iYear, uVin);
            if(checkUpdate==true)
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkId(String s)
    {
        if(s=="")
            return false;
        else
            return true;
    }

}
