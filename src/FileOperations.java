import java.io.*;

public class FileOperations {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void createFile() {
        try {
            System.out.println("Введите путь для создания файла:");
            String filePath = reader.readLine();
            File directory = new File(filePath);
            if (!directory.exists() || !directory.isDirectory()) {
                System.out.println("Неверный путь. Проверьте правильность введенного пути к директории.");
                return;
            }

            System.out.println("Введите название файла:");
            String fileName = reader.readLine();
            File file = new File(directory, fileName);
            if (file.createNewFile()) {
                System.out.println("Файл успешно создан.");
            } else {
                System.out.println("Ошибка при создании файла. Возможно, файл уже существует.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editFile() {
        try {
            System.out.println("Введите путь к файлу для редактирования:");
            String filePath = reader.readLine();
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Файл не существует.");
                return;
            }

            System.out.println("Выберите действие:");
            System.out.println("1. Переписать весь файл");
            System.out.println("2. Изменить определенную строку");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
                    System.out.println("Введите новое содержимое файла (введите 'exit' для завершения редактирования):");
                    String line;
                    while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
                        fileWriter.write(line);
                        fileWriter.newLine();
                    }
                    fileWriter.close();
                    System.out.println("Файл успешно отредактирован.");
                    break;
                case 2:
                    BufferedReader fileReader = new BufferedReader(new FileReader(file));
                    System.out.println("Содержимое файла:");
                    int lineNumber = 1;
                    while ((line = fileReader.readLine()) != null) {
                        System.out.println(lineNumber + ". " + line);
                        lineNumber++;
                    }
                    System.out.println("Введите номер строки, которую хотите изменить:");
                    int lineToEdit = Integer.parseInt(reader.readLine());
                    fileReader.close();

                    if (lineToEdit < 1 || lineToEdit > lineNumber - 1) {
                        System.out.println("Неверный номер строки.");
                        return;
                    }

                    System.out.println("Введите новое содержимое строки:");
                    String newContent = reader.readLine();

                    StringBuilder updatedContent = new StringBuilder();
                    fileReader = new BufferedReader(new FileReader(file));
                    int currentLine = 1;
                    while ((line = fileReader.readLine()) != null) {
                        if (currentLine == lineToEdit) {
                            updatedContent.append(newContent);
                        } else {
                            updatedContent.append(line);
                        }
                        updatedContent.append(System.lineSeparator());
                        currentLine++;
                    }
                    fileReader.close();

                    BufferedWriter fileWriter2 = new BufferedWriter(new FileWriter(file));
                    fileWriter2.write(updatedContent.toString());
                    fileWriter2.close();
                    System.out.println("Файл успешно отредактирован.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewFileContent() {
        try {
            System.out.println("Введите путь к файлу для просмотра содержимого:");
            String filePath = reader.readLine();
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Файл не существует.");
                return;
            }
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile() {
        try {
            System.out.println("Введите путь к файлу для удаления:");
            String filePath = reader.readLine();
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Файл не существует.");
                return;
            }
            if (file.delete()) {
                System.out.println("Файл успешно удален.");
            } else {
                System.out.println("Ошибка при удалении файла.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}