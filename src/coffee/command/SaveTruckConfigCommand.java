package coffee.command;

import coffee.service.ConfigService;

import java.util.Scanner;

public class SaveTruckConfigCommand implements Command {
    private final ConfigService configService;
    private final Scanner scanner;

    public SaveTruckConfigCommand(ConfigService configService) {
        this.configService = configService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        double maxVolume = getValidDoubleInput("Новий об'єм: ");
        double maxBudget = getValidDoubleInput("Новий бюджет: ");
        configService.saveProperties(maxVolume, maxBudget);
        System.out.println("Параметри збережено у файл.");
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