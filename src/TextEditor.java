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
            if (filePath != null && !filePath.isEmpty()) {
                System.out.println("Текущий файл: " + filePath);
            }
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Создать файл                 |");
            System.out.println("| 2. Редактировать файл           |");
            System.out.println("| 3. Просмотреть содержимое файла |");
            System.out.println("| 4. Удалить файл                 |");
            System.out.println("| 5. Установить путь к файлу      |");
            System.out.println("| 6. Выйти                        |");
            System.out.println("+---------------------------------+");

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
                    case "5":
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
                System.out.println("Файл не существует. Что вы хотите сделать?");
                System.out.println("1. Создать файл с такими параметрами");
                System.out.println("2. Создать файл с другими параметрами");
                System.out.println("3. Указать путь еще раз");
                System.out.println("4. Выйти");
                int choice = Integer.parseInt(reader.readLine());
                switch (choice) {
                    case 1:
                        FileOperations.createFileWithPath(filePath);
                        this.filePath = filePath;
                        System.out.println("Путь к файлу успешно установлен.");
                        break;
                    case 2:
                        FileOperations.createFile();
                        setFilePath();
                        break;
                    case 3:
                        setFilePath();
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
                        setFilePath();
                }
            } else {
                this.filePath = filePath;
                System.out.println("Путь к файлу успешно установлен.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}