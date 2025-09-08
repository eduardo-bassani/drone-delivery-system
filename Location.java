public class Location {
    private int code;
    private String street;
    private double latitude;
    private double longitude;

    public Location(int code, String street, double latitude, double longitude) {
        this.code = code;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCode() {
        return code;
    }

    public String getStreet() {
        return street;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toCsv() {
        return code + ";" + street + ";" + latitude + ";" + longitude;
    }

    public String toString() {
        return "[Code: " + code + ", Latitude: " + latitude + ", Street: " + street
                + ", Longitude: " + longitude + "]";
    }
}
