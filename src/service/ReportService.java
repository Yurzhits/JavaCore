package service;

import model.Transaction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportService {
    private static final String OUTPUT_DIR = "output";
    private static final String REPORT_FILE = "output.txt";

    public ReportService() {
        new File(OUTPUT_DIR).mkdirs();
    }

    public void saveReport(List<Transaction> transactions) {
        String reportPath = OUTPUT_DIR + File.separator + REPORT_FILE;

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportPath, true))) {
            for (Transaction transaction : transactions) {
                writer.println(transaction.toString());
            }
            System.out.println("Отчет сохранен: " + reportPath);
            System.out.println("Записей добавлено: " + transactions.size());
        } catch (IOException e) {
            System.out.println("Ошибка сохранения отчета: " + e.getMessage());
        }
    }

    public void showFullReport() {
        String reportPath = OUTPUT_DIR + File.separator + REPORT_FILE;
        File reportFile = new File(reportPath);

        if (!reportFile.exists()) {
            System.out.println("Файл отчета не найден");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            System.out.println("=== ВЫВОД ===");
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                count++;
            }
            System.out.println("Всего записей: " + count);
            System.out.println("=============\n");
        } catch (IOException e) {
            System.out.println("Ошибка чтения отчета: " + e.getMessage());
        }
    }
}