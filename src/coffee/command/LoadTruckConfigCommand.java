package coffee.command;

import coffee.service.ConfigService;

public class LoadTruckConfigCommand implements Command {
    private final ConfigService configService;

    public LoadTruckConfigCommand(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public void execute() {
        System.out.println("Параметри завантажено з файлу.");
        System.out.println("Максимальний об'єм: " + configService.getMaxVolume());
        System.out.println("Максимальний бюджет: " + configService.getMaxBudget());
    }
}