import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GameLoadManager {
    public static void main(String[] args) {

        String zipPath = "D://Games/savegames/saves.zip";
        String unpackDir = "D://Games/savegames";

        List<String> unpackedFiles = openZip(zipPath, unpackDir);

        if (unpackedFiles != null && !unpackedFiles.isEmpty()) {
            GameProgress progress = openProgress(unpackedFiles.get(0));
            if (progress != null) {
                System.out.println("Загружен игровой прогресс:");
                System.out.println(progress);
            }
        }
    }

    public static List<String> openZip(String zipPath, String unpackDir) {
        List<String> unpackedFiles = new ArrayList<>();

        File zipFile = new File(zipPath);
        if (!zipFile.exists()) {
            System.out.println("Архив не найден: " + zipPath);
            return unpackedFiles;
        }

        File unpackDirFile = new File(unpackDir);
        if (!unpackDirFile.exists()) {
            if (!unpackDirFile.mkdirs()) {
                System.out.println("Не удалось создать директорию для распаковки: " + unpackDir);
                return unpackedFiles;
            }
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String filePath = unpackDir + File.separator + entry.getName();
                File newFile = new File(filePath);

                File parentDir = newFile.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    if (!parentDir.mkdirs()) {
                        System.out.println("Не удалось создать директорию: " + parentDir.getPath());
                        continue;
                    }
                }

                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }

                    unpackedFiles.add(filePath);
                    System.out.println("Файл " + entry.getName() + " распакован в " + filePath);
                } catch (IOException e) {
                    System.out.println("Ошибка при распаковке файла " + entry.getName() + ": " + e.getMessage());
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при распаковке архива: " + e.getMessage());
        }

        return unpackedFiles;
    }

    public static GameProgress openProgress(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            GameProgress progress = (GameProgress) ois.readObject();
            System.out.println("Файл " + filePath + " успешно загружен");
            return progress;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке файла сохранения: " + e.getMessage());
            return null;
        }
    }
}