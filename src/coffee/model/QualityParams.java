package coffee.model;

public class QualityParams {
    private double aroma;
    private double taste;
    private String origin;

    public QualityParams(double aroma, double taste, String origin) {
        this.aroma = aroma;
        this.taste = taste;
        this.origin = origin;
    }

    // Getters and Setters
    public double getAroma() { return aroma; }
    public double getTaste() { return taste; }
    public String getOrigin() { return origin; }
}
