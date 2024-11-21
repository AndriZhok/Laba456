package coffee.command;

import coffee.model.CoffeeProduct;
import coffee.model.CoffeeStorage;
import coffee.model.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddProductFromStorageCommand implements Command {
    private CoffeeStorage coffeeStorage;
    private Truck truck;
    private Scanner scanner;

    public AddProductFromStorageCommand(CoffeeStorage coffeeStorage, Truck truck) {
        this.coffeeStorage = coffeeStorage;
        this.truck = truck;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        if (coffeeStorage.getAllProducts().isEmpty()) {
            System.out.println("Склад порожній. Немає продуктів для додавання у фургон.");
            return;
        }

        System.out.println("Доступні продукти на складі:");
        Map<CoffeeProduct, Integer> storageProducts = coffeeStorage.getAllProducts();
        List<Map.Entry<CoffeeProduct, Integer>> productList = new ArrayList<>(storageProducts.entrySet());

        int index = 1;
        for (Map.Entry<CoffeeProduct, Integer> entry : productList) {
            CoffeeProduct product = entry.getKey();
            int quantity = entry.getValue();

            System.out.printf("%d. Назва: %s, Тип: %s, Ціна: %.2f, Вага: %.2f кг, Обʼєм: %.2f л, ",
                    index++, product.getName(), product.getType(), product.getPrice(), product.getWeight(), product.getVolume());

            if (product.getQuality() != null) {
                System.out.printf("Аромат: %.1f, Смак: %.1f, Походження: %s, ",
                        product.getQuality().getAroma(), product.getQuality().getTaste(), product.getQuality().getOrigin());
            } else {
                System.out.print("Якість: невизначена, ");
            }

            System.out.printf("Кількість: %d%n", quantity);
        }

        int productIndex = getValidIntInput("Введіть номер продукту для додавання у фургон: ");
        if (productIndex < 1 || productIndex > productList.size()) {
            System.out.println("Невірний номер продукту.");
            return;
        }

        Map.Entry<CoffeeProduct, Integer> selectedEntry = productList.get(productIndex - 1);
        CoffeeProduct selectedProduct = selectedEntry.getKey();
        int maxQuantity = selectedEntry.getValue();

        int quantity = getValidIntInput("Введіть кількість для додавання: ");
        if (quantity <= 0 || quantity > maxQuantity) {
            System.out.println("Невірна кількість.");
            return;
        }

        try {
            coffeeStorage.reduceStock(selectedProduct.getName(), quantity);
            truck.addProduct(selectedProduct, quantity);
            System.out.printf("Продукт %s додано у фургон у кількості %d.%n", selectedProduct.getName(), quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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