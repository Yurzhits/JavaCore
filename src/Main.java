import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Выберите дейтсвие: ");
        System.out.println("""
                1. Парсинг данных из входного файла
                2. Выгрузка отчета
                0. Выход из программы
                """);
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
            }
            case 2 -> {
                System.out.println("Выберите дейтсвие: ");
                System.out.println("""
                        1. Полный отчет
                        2. Отчет по датам
                        0. Главное меню
                        """);
                int choiceOutput = scanner.nextInt();
                switch (choiceOutput) {
                    case 1 -> {
                    }
                    case 2 -> {
                    }
                    case 0 -> {
                    }
                }
            }
            case 0 -> {
            }
        }
        scanner.close();
    }
}