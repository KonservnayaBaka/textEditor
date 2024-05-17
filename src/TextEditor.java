import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextEditor {
    private BufferedReader reader;
    public static String filePath;
    private String fileCreationTime;
    private String fileLastModifiedTime;

    public TextEditor() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        System.out.println("Добро пожаловать в текстовый редактор!");
        while (true) {
            if (filePath != null && !filePath.isEmpty()) {
                System.out.println("Текущий файл: " + filePath);
                System.out.println("Время создания: " + fileCreationTime);
                System.out.println("Время последнего редактирования: " + fileLastModifiedTime);
            }
            System.out.println("+---------------------------------+");
            System.out.println("| 1. Установить путь к файлу      |");
            System.out.println("| 2. Создать файл                 |");
            System.out.println("| 3. Редактировать файл           |");
            System.out.println("| 4. Просмотреть содержимое файла |");
            System.out.println("| 5. Удалить файл                 |");
            System.out.println("| 6. Выйти                        |");
            System.out.println("+---------------------------------+");

            try {
                String choice = reader.readLine().trim();
                switch (choice) {
                    case "1":
                        setFilePath();
                        break;
                    case "2":
                        FileOperations.createFile();
                        break;
                    case "3":
                        FileOperations.editFile(filePath);
                        break;
                    case "4":
                        FileOperations.viewFileContent(filePath);
                        break;
                    case "5":
                        FileOperations.deleteFile(filePath);
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
            Path file = Paths.get(filePath);
            File directory = new File(filePath);
            if (!directory.exists()) {
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
                        updateFileTimes(file);
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
                updateFileTimes(file);
                System.out.println("Путь к файлу успешно установлен.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateFileTimes(Path file) throws IOException {
        BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.fileCreationTime = dateFormat.format(new Date(attrs.creationTime().toMillis()));
        this.fileLastModifiedTime = dateFormat.format(new Date(attrs.lastModifiedTime().toMillis()));
    }
}
