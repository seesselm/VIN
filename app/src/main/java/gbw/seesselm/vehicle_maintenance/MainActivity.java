package gbw.seesselm.vehicle_maintenance;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;




public class MainActivity extends AppCompatActivity
{

    private SharedPreferences prefs;
    SQLiteDatabase datab;
    DBHandler db;
    private String TAG = "CarProj";


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
                    /*case R.id.navigation_view:
                        selectfrag = ViewFragment.newInstance();
                        break;*/
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

}
