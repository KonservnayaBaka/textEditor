import java.io.*;

public class TextEditor {
    private BufferedReader reader;
    public static String filePath;
    public TextEditor() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        System.out.println("Добро пожаловать в текстовый редактор!");
        while (true) {
            System.out.println("1. Создать файл");
            System.out.println("2. Редактировать файл");
            System.out.println("3. Просмотреть содержимое файла");
            System.out.println("4. Удалить файл");
            System.out.println("5. Установить путь к файлу"); // Новый пункт меню
            System.out.println("6. Выйти");

            try {
                String choice = reader.readLine().trim();
                switch (choice) {
                    case "1":
                        FileOperations.createFile();
                        break;
                    case "2":
                        FileOperations.editFile(filePath);
                        break;
                    case "3":
                        FileOperations.viewFileContent(filePath);
                        break;
                    case "4":
                        FileOperations.deleteFile(filePath);
                        break;
                    case "5": // Новый case для вызова метода setFilePath()
                        setFilePath();
                        break;
                    case "6":
                        System.out.println("Выход из текстового редактора. До свидания!");
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFilePath() {
        try {
            System.out.println("Введите путь к файлу:");
            String filePath = reader.readLine();
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Файл не существует.");
                return;
            }
            this.filePath = filePath; // Сохраните путь к файлу в переменной класса
            System.out.println("Путь к файлу успешно установлен.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}