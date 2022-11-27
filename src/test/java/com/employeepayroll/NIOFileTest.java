package com.employeepayroll;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import static junit.framework.Assert.assertTrue;
public class NIOFileTest {
    private static String pathDirectory = "C:\\Users\\hp\\IdeaProjects\\EmployeePayRollProject";// home directory path
    private static String folderName = "TempPlayGround"; // folder name present in home directory

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        //Check file exists
        Path homePath = Paths.get(pathDirectory);
        assertTrue(Files.exists(homePath));

      //Delete file and check file not exist
         Path playPath = Paths.get(pathDirectory + "/" + folderName);//creating path
        if (Files.exists(playPath)) deleteFiles(playPath.toFile());
        assertTrue(Files.notExists(playPath));

        //Create Directory
        Files.createDirectory(playPath);
        assertTrue(Files.exists(playPath));

        //Create file
        IntStream.range(1, 10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath + "/TempFile" + cntr);

            assertTrue(Files.notExists(tempFile));

            try {Files.createFile(tempFile);}
            catch (IOException e) { System.out.println("IO Exception Occurred."); }

            assertTrue(Files.exists(tempFile));
        });

     //List file,directories as well as file with Extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);//created path check regular file and listed

        Files.newDirectoryStream(playPath).forEach(System.out::println);

        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && path.toString().startsWith("TempFile"))
                .forEach(System.out::println);
    }
    public static boolean deleteFiles(File contentsToDelete) {
        File[] allContents = contentsToDelete.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteFiles(file);
            }
        }
        return contentsToDelete.delete();
    }
    @Test
    public void givenADirWhenWatchedListsAllTheActivities() throws IOException {
        Path dir = Paths.get(pathDirectory+"/"+folderName);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        assertTrue(new JavaWatchService(dir).processEvents());
    }
}

