package za.co.prog6112.q1;

/** Stores one hospital inspection record. */
public class HospitalInspection {
    private final String hospitalName;
    private final String location;
    private final int yearsSinceInspection;

    public HospitalInspection(String hospitalName, String location, int yearsSinceInspection) {
        this.hospitalName = hospitalName;
        this.location = location;
        this.yearsSinceInspection = yearsSinceInspection;
    }

    public String getHospitalName() { return hospitalName; }
    public String getLocation() { return location; }
    public int getYearsSinceInspection() { return yearsSinceInspection; }

    @Override
    public String toString() {
        return hospitalName + " | " + location + " | " + yearsSinceInspection + " year(s) ago";
    }
}
