package coffee.model;

public class InstantCoffee extends CoffeeProduct {
    private String packagingType; // Банка або пакетик

    public InstantCoffee(String name, double price, double weight, double volume, QualityParams quality, String packagingType) {
        super(name, price, weight, volume, quality);
        this.packagingType = packagingType;
    }

    @Override
    public String getType() {
        return "Instant Coffee (" + packagingType + ")";
    }
}
