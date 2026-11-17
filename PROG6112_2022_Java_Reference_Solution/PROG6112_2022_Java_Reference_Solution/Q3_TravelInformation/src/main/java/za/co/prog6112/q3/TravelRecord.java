package za.co.prog6112.q3;

/** One journey between two locations, using the selected travel type. */
public class TravelRecord {
    private final String startLocation;
    private final String endLocation;
    private final String travelBy;

    public TravelRecord(String startLocation, String endLocation, String travelBy) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.travelBy = travelBy;
    }

    public String getStartLocation() { return startLocation; }
    public String getEndLocation() { return endLocation; }
    public String getTravelBy() { return travelBy; }

    @Override public String toString() {
        return startLocation + " to " + endLocation + " by " + travelBy;
    }
}
