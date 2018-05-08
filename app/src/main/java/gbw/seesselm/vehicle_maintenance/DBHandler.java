package gbw.seesselm.vehicle_maintenance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vehicle";
    private static final String TABLE_CAR_DETAIL = "cardetails";
    private static final String HEADER_CAR_NUM = "number";
    private static final String HEADER_CAR_MAKE = "make";
    private static final String HEADER_CAR_MODEL = "model";
    private static final String HEADER_CAR_YEAR = "year";
    private static final String HEADER_VIN = "vin";

    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CAR_DETAIL_TABLE = " CREATE TABLE "
                + TABLE_CAR_DETAIL + "("
                + HEADER_CAR_NUM + " INTEGER PRIMARY KEY,"
                + HEADER_CAR_MAKE + " TEXT,"
                + HEADER_CAR_MODEL + " TEXT,"
                + HEADER_CAR_YEAR + " INTEGER,"
                + HEADER_VIN + " TEXT "
                + ")";
        db.execSQL(CREATE_CAR_DETAIL_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR_DETAIL);
        onCreate(db);
    }

    void addCar(Car newCar)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HEADER_CAR_MAKE, newCar.getMake());
        values.put(HEADER_CAR_MODEL, newCar.getModel());
        values.put(HEADER_CAR_YEAR, newCar.getYear());
        values.put(HEADER_VIN, newCar.getVIN());
        db.insert(TABLE_CAR_DETAIL,null,values);
        db.close();
    }

    public boolean updateCarInfo(int num, String make, String model, int year, String vin)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues upVal = new ContentValues();
        upVal.put(HEADER_CAR_NUM, num);
        upVal.put(HEADER_CAR_MAKE, make);
        upVal.put(HEADER_CAR_MODEL, model);
        upVal.put(HEADER_CAR_YEAR, year);
        upVal.put(HEADER_VIN, vin);
        return db.update(TABLE_CAR_DETAIL, upVal, HEADER_CAR_NUM + "-" + num, null) > 0;
    }

    public boolean removeCar(int num)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CAR_DETAIL, HEADER_CAR_NUM + "=" + num, null) > 0;
    }

    public List<Car> getAllCars()
    {
        List<Car> carList = new ArrayList<Car>();
        String query = "SELECT * FROM " + TABLE_CAR_DETAIL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do
            {
                Car car = new Car();
                car.setDbNum(Integer.parseInt(cursor.getString(0)));
                car.setMake(cursor.getString(1));
                car.setModel(cursor.getString(2));
                car.setYear(Integer.parseInt(cursor.getString(3)));
                car.setVIN(cursor.getString(4));

                carList.add(car);
            }
            while (cursor.moveToNext());


        }
        return carList;
    }
}