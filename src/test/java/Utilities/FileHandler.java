package Utilities;

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

    //To create a file
    public static void create_file(String filePath) {
        try {
            Files.createFile(Path.of(filePath));
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    //To write a file
    public static void write_file(String msg)
    {
        try {
            FileWriter Writer = new FileWriter("C:\\Users\\supriya4\\IdeaProjects\\AMAZON_TASK\\Resources\\DataFile.txt");
            //String msg = "Files in Java are seriously good!!";
            Writer.write(msg);
            Writer.close();
            System.out.println("The data written in file is:"+msg);
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    //read from a file
    public static void read_file()
    {
        try {
            File file = new File("C:\\Users\\supriya4\\IdeaProjects\\AMAZON_TASK\\Resources\\DataFile.txt");
            Scanner Reader = new Scanner(file);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                System.out.println("The data present in file is:"+data);
            }
            Reader.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    //To delete the file
    public static void delete_file()
    {
        File myObj = new File("C:\\Users\\supriya4\\IdeaProjects\\AMAZON_TASK\\Resources\\DataFile.txt");
        if (myObj.delete()) {
            System.out.println("The deleted file is : " + myObj.getName());
        }
        else {
            System.out.println("Failed in deleting the file.");
        }
    }
}
