package Utilities;

import Pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileHandler {

    public boolean checkFileExists(String file_path) {
        return Files.exists(Path.of(file_path));
    }
    static Logger log = LogManager.getLogger(LoginPage.class);
    public static void create_file(String filePath) {
        try {
            Files.createFile(Path.of(filePath));
        } catch (IOException e) {
            log.info("An error has occurred.");
            e.printStackTrace();
        }
    }

    public static void write_file(String msg, String file_path)
    {
        try {
            FileWriter Writer = new FileWriter(file_path);

            Writer.write(msg);
            Writer.close();
            log.info("The data written in file is:"+msg);
        } catch (IOException e) {
            log.info("An error has occurred.");
            e.printStackTrace();
        }
    }

    public static void read_file(String file_path)
    {
        try {
            File file = new File(file_path);
            Scanner Reader = new Scanner(file);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                log.info("The data present in file is:"+data);
            }
            Reader.close();
        }

        catch (FileNotFoundException e) {
            log.info("An error has occurred.");
            e.printStackTrace();
        }
    }

    public static void delete_file(String file_path)
    {
        File myObj = new File(file_path);
        if (myObj.delete()) {
            log.info("The deleted file is : " + myObj.getName());
        }
        else {
            log.info("Failed in deleting the file.");
        }
    }
}