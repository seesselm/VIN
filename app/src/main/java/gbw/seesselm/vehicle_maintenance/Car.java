package gbw.seesselm.vehicle_maintenance;



public class Car
{
    int dbNum;
    String make;
    String model;
    int year;
    String VIN;

    public Car()
    {

    }

    public Car(int numIn, String makeIn, String modelIn, int yearIn, String vinIn)
    {
        dbNum=numIn;
        make=makeIn;
        model=modelIn;
        year=yearIn;
        VIN=vinIn;
    }

    public Car(String makeIn, String modelIn, int yearIn, String vinIn)
    {
        make=makeIn;
        model=modelIn;
        year=yearIn;
        VIN=vinIn;
    }

    public int getDbNum()
    {
        return dbNum;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public int getYear()
    {
        return year;
    }

    public String getVIN()
    {
        return VIN;
    }

    public void setDbNum(int dbNumIn)
    {
        dbNum = dbNumIn;
    }

    public void setMake(String makeIn)
    {
        make = makeIn;
    }

    public void setModel(String modelIn)
    {
        model = modelIn;
    }

    public void setYear(int yearIn)
    {
        year = yearIn;
    }

    public void setVIN(String VININ)
    {
        VIN = VININ;
    }
}
