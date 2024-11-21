package coffee.model;

public abstract class CoffeeProduct {
    private String name;
    private double price;
    private double weight;
    private double volume; // Об'єм з упаковкою
    private QualityParams quality;

    public CoffeeProduct(String name, double price, double weight, double volume, QualityParams quality) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
        this.quality = quality;
    }

    public abstract String getType();

    // Getters and Setters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getWeight() { return weight; }
    public double getVolume() { return volume; }
    public QualityParams getQuality() {
        if (quality == null) {
            // Логування або використання значень за замовчуванням
            return new QualityParams(0, 0, "Unknown");  // Повертати за замовчуванням
        }
        return quality;
    }
}
