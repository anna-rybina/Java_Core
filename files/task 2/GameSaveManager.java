import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameSaveManager {
    public static void main(String[] args) {

        String savegamesDir = "D://Games/savegames";

        GameProgress progress1 = new GameProgress(100, 3, 1, 0.0);
        GameProgress progress2 = new GameProgress(85, 5, 2, 125.5);
        GameProgress progress3 = new GameProgress(30, 2, 5, 987.3);

        List<String> savedFiles = new ArrayList<>();
        savedFiles.add(saveGame(savegamesDir + "/save1.dat", progress1));
        savedFiles.add(saveGame(savegamesDir + "/save2.dat", progress2));
        savedFiles.add(saveGame(savegamesDir + "/save3.dat", progress3));

        String zipPath = savegamesDir + "/saves.zip";
        zipFiles(zipPath, savedFiles);

        deleteFiles(savedFiles);
    }

    public static String saveGame(String filePath, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("Сохранение создано: " + filePath);
            return filePath;
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла " + filePath + ": " + e.getMessage());
            return null;
        }
    }

    public static void zipFiles(String zipPath, List<String> filesToZip) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (String filePath : filesToZip) {
                if (filePath == null) continue;

                File fileToZip = new File(filePath);
                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                    System.out.println("Файл " + filePath + " добавлен в архив");
                } catch (IOException e) {
                    System.out.println("Ошибка при добавлении файла " + filePath + " в архив: " + e.getMessage());
                }
            }
            System.out.println("Архив создан: " + zipPath);
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }

    public static void deleteFiles(List<String> filesToDelete) {
        for (String filePath : filesToDelete) {
            if (filePath == null) continue;

            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("Файл " + filePath + " удален");
            } else {
                System.out.println("Не удалось удалить файл " + filePath);
            }
        }
    }
}