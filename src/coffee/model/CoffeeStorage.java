package coffee.model;

import java.util.HashMap;
import java.util.Map;

public class CoffeeStorage {
    private final Map<String, StockItem> stock;

    public CoffeeStorage() {
        this.stock = new HashMap<>();
    }

    // Додає новий продукт або збільшує кількість на складі
    public void addProduct(CoffeeProduct product, int quantity) {
        String name = product.getName();
        stock.computeIfAbsent(name, k -> new StockItem(product, 0))
                .increaseQuantity(quantity);
    }

    // Перевіряє, чи є достатня кількість товару на складі
    public boolean hasStock(String name, int quantity) {
        StockItem item = stock.get(name);
        return item != null && item.getQuantity() >= quantity;
    }

    // Повертає продукт із заданою кількістю, зменшуючи його на складі
    public CoffeeProduct getProduct(String name, int quantity) {
        if (!hasStock(name, quantity)) {
            throw new IllegalArgumentException("Недостатньо запасів продукту " + name + " на складі.");
        }
        CoffeeProduct product = stock.get(name).getProduct();
        reduceStock(name, quantity);
        return product;
    }

    // Зменшує кількість товару на складі
    public void reduceStock(String name, int quantity) {
        if (!hasStock(name, quantity)) {
            throw new IllegalArgumentException("Недостатньо запасів для зменшення " + name);
        }
        StockItem item = stock.get(name);
        item.decreaseQuantity(quantity);
        if (item.getQuantity() <= 0) {
            stock.remove(name);
        }
    }

    public Map<CoffeeProduct, Integer> getAllProducts() {
        Map<CoffeeProduct, Integer> allProducts = new HashMap<>();
        for (StockItem item : stock.values()) {
            allProducts.put(item.getProduct(), item.getQuantity());
        }
        return allProducts;
    }

    // Внутрішній клас для зберігання продукту і його кількості
    private static class StockItem {
        private final CoffeeProduct product;
        private int quantity;

        public StockItem(CoffeeProduct product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public CoffeeProduct getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void increaseQuantity(int amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Кількість для додавання має бути більше нуля.");
            }
            quantity += amount;
        }

        public void decreaseQuantity(int amount) {
            if (amount <= 0 || quantity < amount) {
                throw new IllegalArgumentException("Недостатньо товару на складі для зменшення.");
            }
            quantity -= amount;
        }
    }
}