package Utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileHandler {
    public TextFileHandler() {

    }

    /***
     * This method is to read text file
     * @param filepath is text file location
     * @return returns list of all lines in text file
     */
    public List<String> Read_Text_File_Line_To_Line(String filepath) {
        List<String> readData = new ArrayList<String>();
        File f = new File(filepath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                readData.add(line);
            }
            br.close();
            return readData;
        } catch (Exception e) {
            Log.info(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /***
     * This method is get complete text from text file as string
     * @param filepath is text file location
     * @return returns complete data as string
     */
    public String read_whole_TextFile(String filepath) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (Exception e) {
            Log.info(e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    /***
     * This method is to return text file data as string
     * @param filepath is text file location
     * @return returns text file data as string
     */
    public String readFile_Store_Data(String filepath) {
        String data = "";
        File f = new File(filepath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                data += line + "\n";
            }
            br.close();
        } catch (Exception e) {
            Log.info(e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    /***
     * This method is to write data to text file
     * @param filepath is text file path
     * @param strContent is data to be written into text file
     */
    public void write_Text_File(String filepath, String strContent) {
        File f = new File(filepath);
        BufferedWriter bw;
        try {
            FileWriter writer = new FileWriter(f, true);
            bw = new BufferedWriter(writer);
            bw.write(strContent);
            writer.write("\r\n");
            bw.close();
            writer.close();
        } catch (Exception e) {
            Log.info(e.getMessage());
            e.printStackTrace();
        }
    }

    /***
     * This method is to create new text file
     * @param filepath is text file location
     * @return returns true if file is created successfully, false otherwise
     */
    public boolean create_new_TextFile(String filepath) {
        boolean newFile = false;
        File f = new File(filepath);
        try {
            newFile = f.createNewFile();
            Log.info("File Created: " + newFile);
        } catch (Exception e) {
            Log.info(e.getMessage());
            e.printStackTrace();
        }
        return newFile;
    }

}
