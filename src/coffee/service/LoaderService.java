package coffee.service;

import coffee.model.CoffeeProduct;
import coffee.model.Truck;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoaderService {

    public void addProductToTruck(Truck truck, CoffeeProduct product, int quantity) {
        if (truck.addProduct(product, quantity)) {
            System.out.println(product.getName() + " додано до фургона у кількості " + quantity);
        } else {
            System.out.println("Не вдалося додати " + product.getName() + " через обмеження по об'єму або бюджету.");
        }
    }

    public List<CoffeeProduct> sortProductsByPriceWeight(Truck truck) {
        List<CoffeeProduct> products = new ArrayList<>(truck.getProducts().keySet());
        products.sort(Comparator.comparingDouble(product -> product.getPrice() / product.getWeight()));
        return products;
    }

    public List<CoffeeProduct> findProductByQuality(Truck truck, double minAroma, double maxAroma, double minTaste, double maxTaste) {
        return truck.getProducts().entrySet().stream()
                .map(Map.Entry::getKey) // Отримуємо лише ключі (CoffeeProduct)
                .filter(product -> product.getQuality().getAroma() >= minAroma && product.getQuality().getAroma() <= maxAroma)
                .filter(product -> product.getQuality().getTaste() >= minTaste && product.getQuality().getTaste() <= maxTaste)
                .collect(Collectors.toList()); // Збираємо в список
    }
}