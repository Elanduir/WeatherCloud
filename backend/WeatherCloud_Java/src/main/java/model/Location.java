package model;

public enum Location {
    WOHLEN("5610_wohlen"),
    LENZBURG("5600_lenzburg"),
    INVALID("");

    private final String name;
    private Location(String s){
        this.name = s;
    }

    public Location getByString(String loc){
        switch(loc){
            case "wohlen":
                return WOHLEN;
            case "lenzburg":
                return LENZBURG;
            default:
                return INVALID;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
