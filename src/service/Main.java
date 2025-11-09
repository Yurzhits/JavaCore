package service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileParser parserService = new FileParser();
        ReportService reportService = new ReportService();

        System.out.println("=== СИСТЕМА ДЕНЕЖНЫХ ПЕРЕВОДОВ ===");

        while (true) {
            System.out.println("""
                    Выберите действие:
                    1. Парсинг данных из input"
                    2. Вывод отчета
                    0. Выход
                    Ваш выбор: """);
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Парсинг файлов");
                        var transactions = parserService.parseInputFiles();
                        reportService.saveReport(transactions);
                    }
                    case 2 -> {
                        System.out.println("Отчет");
                        reportService.showFullReport();
                    }
                    case 0 -> {
                        System.out.println("Выход из программы");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода");
                scanner.nextLine();
            }
        }
    }
}