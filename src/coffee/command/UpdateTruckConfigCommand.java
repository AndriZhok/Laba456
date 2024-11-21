package coffee.command;

import coffee.model.Truck;

import java.util.Scanner;

public class UpdateTruckConfigCommand implements Command {
    private final Truck truck;
    private final Scanner scanner;

    public UpdateTruckConfigCommand(Truck truck) {
        this.truck = truck;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        double maxVolume = getValidDoubleInput("Введіть новий об'єм фургона: ");
        double maxBudget = getValidDoubleInput("Введіть новий бюджет фургона: ");
        truck.setMaxVolume(maxVolume);
        truck.setMaxBudget(maxBudget);
        System.out.println("Параметри фургона оновлено.");
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
}