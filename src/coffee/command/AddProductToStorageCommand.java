package coffee.command;

import coffee.model.*;

import java.util.Scanner;

public class AddProductToStorageCommand implements Command {
    private final CoffeeStorage coffeeStorage;
    private final Scanner scanner;

    public AddProductToStorageCommand(CoffeeStorage coffeeStorage) {
        this.coffeeStorage = coffeeStorage;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        CoffeeProduct product = createProduct();
        int quantity = getValidIntInput("Введіть кількість для додавання на склад: ");
        coffeeStorage.addProduct(product, quantity);
        System.out.printf("Продукт %s додано на склад у кількості %d.%n", product.getName(), quantity);
    }

    private CoffeeProduct createProduct() {
        System.out.println("""
                Оберіть тип кави:
                1. Зернова
                2. Мелена
                3. Розчинна в банках
                4. Розчинна в пакетиках
                """);
        int coffeeTypeChoice = getValidIntInput("Оберіть тип кави: ");

        System.out.print("Назва кави: ");
        String name = scanner.next();
        double price = getValidDoubleInput("Ціна кави: ");
        double weight = getValidDoubleInput("Вага кави (кг): ");
        double volume = getValidDoubleInput("Об'єм кави (л): ");
        double aroma = getValidDoubleInput("Аромат (1-10): ");
        double taste = getValidDoubleInput("Смак (1-10): ");
        System.out.print("Походження: ");
        String origin = scanner.next();

        QualityParams qualityParams = new QualityParams(aroma, taste, origin);

        return switch (coffeeTypeChoice) {
            case 1 -> new WholeBeanCoffee(name, price, weight, volume, qualityParams);
            case 2 -> new GroundCoffee(name, price, weight, volume, qualityParams);
            case 3 -> new InstantCoffee(name, price, weight, volume, qualityParams, "Jar");
            case 4 -> new InstantCoffee(name, price, weight, volume, qualityParams, "Packet");
            default -> throw new IllegalArgumentException("Невірний вибір кави.");
        };
    }

    private double getValidDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Невірний формат. Введіть число.");
                scanner.next();
            }
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