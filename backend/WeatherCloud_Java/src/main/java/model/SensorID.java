package model;

public enum SensorID {
    SENSOR01W("5610_sensor01"),
    SENSOR02W("5610_sensor02"),
    INVALID("");

    private final String id;

    SensorID(String id){
        this.id = id;
    }

    public SensorID getByString(String sensor){
        switch(sensor){
            case "5610_sensor01":
                return SENSOR01W;
            case "5610_sensor02":
                return SENSOR02W;
            default:
                System.out.println("Invalid paramenter");
                return null;
        }
    }


    @Override
    public String toString() {
        return this.id;
    }
}
