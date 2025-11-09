package service;

import model.Transaction;
import exception.InvalidAccountException;
import exception.InvalidAmountException;
import exception.FileProcessingException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private static final String INPUT_DIR = "input";
    private static final String ARCHIVE_DIR = "archive";

    private final FileReaderService fileReaderService;
    private final TransactionParser transactionParser;
    private final FileArchiver fileArchiver;

    public FileParser() {
        this.fileReaderService = new FileReaderService();
        this.transactionParser = new TransactionParser();
        this.fileArchiver = new FileArchiver(ARCHIVE_DIR);
    }

    public List<Transaction> parseInputFiles() {
        List<Transaction> allTransactions = new ArrayList<>();

        File inputDir = new File(INPUT_DIR);
        if (!inputDir.exists() || !inputDir.isDirectory()) {
            System.out.println("Папка input не существует");
            return allTransactions;
        }

        File[] files = fileReaderService.findTxtFiles(INPUT_DIR);

        if (files == null || files.length == 0) {
            System.out.println("В папке input нет txt файлов");
            return allTransactions;
        }

        System.out.println("Найдено файлов: " + files.length);

        for (File file : files) {
            try {
                System.out.println("Обработка файла: " + file.getName());
                List<Transaction> fileTransactions = processFile(file);
                allTransactions.addAll(fileTransactions);
                fileArchiver.moveToArchive(file);
                System.out.println("Файл " + file.getName() + " обработан. Транзакций: " + fileTransactions.size());
            } catch (Exception e) {
                System.out.println("Ошибка обработки файла " + file.getName() + ": " + e.getMessage());
                allTransactions.add(transactionParser.createErrorTransaction(
                        file.getName(), 0, "ошибка файла: " + e.getMessage()));
            }
        }

        return allTransactions;
    }

    private List<Transaction> processFile(File file) throws FileProcessingException {
        List<Transaction> transactions = new ArrayList<>();
        List<String> lines = fileReaderService.readFileLines(file);

        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            try {
                Transaction transaction = transactionParser.parseTransactionLine(file.getName(), line, lineNumber);
                transactions.add(transaction);
            } catch (InvalidAccountException | InvalidAmountException e) {
                Transaction errorTransaction = transactionParser.createErrorTransaction(
                        file.getName(), lineNumber, e.getMessage());
                transactions.add(errorTransaction);
            } catch (Exception e) {
                Transaction errorTransaction = transactionParser.createErrorTransaction(
                        file.getName(), lineNumber, "ошибка формата данных");
                transactions.add(errorTransaction);
            }
        }

        return transactions;
    }
}