import java.io.*;

public class TextEditor {
    private BufferedReader reader;

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
            System.out.println("5. Выйти");

            try {
                String choice = reader.readLine().trim();
                switch (choice) {
                    case "1":
                        FileOperations.createFile();
                        break;
                    case "2":
                        FileOperations.editFile();
                        break;
                    case "3":
                        FileOperations.viewFileContent();
                        break;
                    case "4":
                        FileOperations.deleteFile();
                        break;
                    case "5":
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
}