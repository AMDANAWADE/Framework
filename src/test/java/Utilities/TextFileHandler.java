package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextFileHandler {
    public TextFileHandler() {

    }

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

            e.printStackTrace();
            return null;
        }
    }

    public String read_whole_TextFile(String filepath) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (Exception e) {

            e.printStackTrace();
        }
        return data;
    }


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

            e.printStackTrace();
        }
        return data;
    }


    public void write_Text_File(String filepath,String strContent) {
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

            e.printStackTrace();
        }
    }

    public boolean create_new_TextFile(String filepath) {
        boolean newFile = false;
        File f = new File(filepath);
        try {
            newFile = f.createNewFile();
            Log.info("File Created: " + newFile);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return newFile;
    }

}
