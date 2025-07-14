import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameInstaller {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();

        File gamesDir = new File("D://Games"); // Замените на ваш путь
        if (!gamesDir.exists()) {
            log.append("Основная папка Games не найдена! Создайте её вручную.\n");
            System.out.println(log.toString());
            return;
        }

        File srcDir = createDirectory(gamesDir, "src", log);
        File resDir = createDirectory(gamesDir, "res", log);
        File savegamesDir = createDirectory(gamesDir, "savegames", log);
        File tempDir = createDirectory(gamesDir, "temp", log);

        if (srcDir != null) {
            File mainDir = createDirectory(srcDir, "main", log);
            File testDir = createDirectory(srcDir, "test", log);

            if (mainDir != null) {
                createFile(mainDir, "Main.java", log);
                createFile(mainDir, "Utils.java", log);
            }
        }

        if (resDir != null) {
            createDirectory(resDir, "drawables", log);
            createDirectory(resDir, "vectors", log);
            createDirectory(resDir, "icons", log);
        }

        if (tempDir != null) {
            createFile(tempDir, "temp.txt", log);

            // Записываем лог в файл temp.txt
            try (FileWriter writer = new FileWriter(new File(tempDir, "temp.txt"))) {
                writer.write(log.toString());
                log.append("Лог успешно записан в temp.txt\n");
            } catch (IOException e) {
                log.append("Ошибка при записи лога в temp.txt: ").append(e.getMessage()).append("\n");
            }
        }

        System.out.println(log.toString());
    }

    private static File createDirectory(File parentDir, String dirName, StringBuilder log) {
        File dir = new File(parentDir, dirName);
        if (dir.mkdir()) {
            log.append("Директория ").append(dir.getPath()).append(" создана успешно\n");
            return dir;
        } else {
            log.append("Не удалось создать директорию ").append(dir.getPath()).append("\n");
            return null;
        }
    }

    private static void createFile(File parentDir, String fileName, StringBuilder log) {
        File file = new File(parentDir, fileName);
        try {
            if (file.createNewFile()) {
                log.append("Файл ").append(file.getPath()).append(" создан успешно\n");
            } else {
                log.append("Не удалось создать файл ").append(file.getPath()).append("\n");
            }
        } catch (IOException e) {
            log.append("Ошибка при создании файла ").append(file.getPath()).append(": ")
                    .append(e.getMessage()).append("\n");
        }
    }
}