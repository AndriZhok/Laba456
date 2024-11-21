package coffee.command;

import coffee.model.CoffeeProduct;
import coffee.model.CoffeeStorage;
import coffee.model.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RemoveTruckProductCommand implements Command {
    private final Truck truck;
    private final CoffeeStorage coffeeStorage;
    private final Scanner scanner;

    public RemoveTruckProductCommand(Truck truck, CoffeeStorage coffeeStorage) {
        this.truck = truck;
        this.coffeeStorage = coffeeStorage;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        if (truck.getProducts().isEmpty()) {
            System.out.println("Фургон порожній, немає продуктів для видалення.");
            return;
        }

        System.out.println("Продукти у фургоні:");
        List<Map.Entry<CoffeeProduct, Integer>> productList = new ArrayList<>(truck.getProducts().entrySet());
        int index = 1;

        for (Map.Entry<CoffeeProduct, Integer> entry : productList) {
            CoffeeProduct product = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("""
                    %d. Назва: %s
                       Тип: %s
                       Кількість: %d
                       Ціна за одиницю: %.2f грн
                       Загальна вага: %.2f кг
                       Загальний обʼєм: %.2f л
                       Аромат: %.1f
                       Смак: %.1f
                       Походження: %s
                    """, index++, product.getName(), product.getType(), quantity,
                    product.getPrice(), product.getWeight() * quantity, product.getVolume() * quantity,
                    product.getQuality().getAroma(), product.getQuality().getTaste(), product.getQuality().getOrigin());
        }

        int productIndex = getValidIntInput("Введіть номер продукту для видалення: ") - 1;
        if (productIndex < 0 || productIndex >= productList.size()) {
            System.out.println("Невірний вибір.");
            return;
        }

        Map.Entry<CoffeeProduct, Integer> selectedEntry = productList.get(productIndex);
        CoffeeProduct selectedProduct = selectedEntry.getKey();
        int currentQuantity = selectedEntry.getValue();

        int quantityToRemove = getValidIntInput("Введіть кількість для видалення (або 0 для видалення всього): ");
        if (quantityToRemove <= 0 || quantityToRemove > currentQuantity) {
            quantityToRemove = currentQuantity;
        }

        truck.removeProduct(selectedProduct, quantityToRemove);
        coffeeStorage.addProduct(selectedProduct, quantityToRemove);
        System.out.printf("Продукт %s видалено у кількості %d.%n", selectedProduct.getName(), quantityToRemove);
    }

    private int getValidIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Невірний формат. Введіть ціле число.");
                scanner.next();
            }
        }
    }
}