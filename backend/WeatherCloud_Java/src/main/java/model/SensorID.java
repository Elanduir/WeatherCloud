package model;

public enum SensorID {
    sensor01W("5610_sensor01"),
    sensor02W("5610_sensor02");

    private final String id;

    SensorID(String id){
        this.id = id;
    }

    public SensorID getByString(String sensor){
        switch(sensor){
            case "5610_sensor01":
                return sensor01W;
            case "5610_sensor02":
                return sensor02W;
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
