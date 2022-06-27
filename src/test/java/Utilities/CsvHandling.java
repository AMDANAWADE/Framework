package Utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;

public class CsvHandling {
    private String filePath;
    private int sheetNo = 0;
    private Workbook wb;
    private Sheet sh;
    private int headerRow = 0;

    public CsvHandling(String file_location, String sheet_name) {
        this.filePath = file_location;
        try {
            this.wb = WorkbookFactory.create(new FileInputStream(filePath));
            this.sh = wb.getSheet(sheet_name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CsvHandling(String file_location, int sheet_no) {
        this.filePath = file_location;
        this.sheetNo = sheet_no;
        try {
            this.wb = WorkbookFactory.create(new FileInputStream(filePath));
            sh = wb.getSheetAt(sheetNo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CsvHandling() {
    }

    public String get_cell_value(int row_no, int column_no) {
        return new DataFormatter().formatCellValue(sh.getRow(row_no).getCell(column_no));
    }

    public void set_header(int row_no) {
        headerRow = row_no;
    }

    public List<String> get_header_names() {
        Row row = sh.getRow(headerRow);
        int row_len = row.getLastCellNum();
        List<String> colNames = new ArrayList<>();
        for (int i = 0; i < row_len; i++) {
            colNames.add(get_cell_value(headerRow, i));
        }
        return colNames;
    }

    public List<Integer> get_column_index_having_value_in_row(int row_num, String value) {
        if (isEmptyRow(row_num))
            return Collections.emptyList();
        Row row = sh.getRow(row_num);
        int row_len = row.getPhysicalNumberOfCells();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < row_len; i++) {
            if (get_cell_value(row_num, i).trim().equalsIgnoreCase(value.trim()))
                cols.add(i);
        }
        return Collections.unmodifiableList(cols);
    }

    public int get_first_column_index_having_value_in_row(int row_num, String value) {
        return get_column_index_having_value_in_row(row_num, value).get(0);
    }

    public List<Integer> get_row_index_having_value_in_column(int col_num, String value) {
        int col_len = (sh.getLastRowNum() - sh.getFirstRowNum()) + 1;
        List<Integer> rows = new ArrayList<>();
        for (int i = headerRow + 1; i < col_len; i++) {
            if (isEmptyRow(i))
                continue;
            if (get_cell_value(i, col_num).trim().equalsIgnoreCase(value.trim()))
                rows.add(i);
        }
        return Collections.unmodifiableList(rows);
    }

    public boolean isEmptyRow(int rowNum) {
        Row rw = sh.getRow(rowNum);
        return rw == null;
    }

    public Map<String, String> getData(int rowNum) {
        if (isEmptyRow(rowNum))
            return Collections.emptyMap();
        List<String> headerNames = get_header_names();
        Row row = sh.getRow(rowNum);
        Map<String, String> dataMap = new LinkedHashMap<>();
        forEachWithCounter(row, (index, cell) -> {
            if (headerNames.get(index).trim().length() > 0)
                dataMap.put(headerNames.get(index).trim(), get_cell_value(rowNum, cell.getColumnIndex()));
        });
        return Collections.unmodifiableMap(dataMap);
    }


    public int getRowSize() {
        return sh.getLastRowNum() + 1;
    }

    public int getColumnSize(int rowNum) {
        if (isEmptyRow(rowNum))
            return 0;
        return sh.getRow(rowNum).getLastCellNum() + 1;
    }

    private void forEachWithCounter(Iterable<Cell> source, BiConsumer<Integer, Cell> biConsumer) {
        int i = 0;
        for (Cell cell : source) {
            biConsumer.accept(i, cell);
            i++;
        }

    }

    //This method is to read single line from the start of csv file
    public static String[] readSingleline(String file_path) throws IOException {
        try {
            File file = new File(file_path);
            String[] Data;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                Data = br.readLine().split(",");
            }
            return Data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found! check the correct file path.");
        }
        return null;
    }

    //This method is read last line from csv file
    public static String readFromLast(String filePath) throws IOException {
        String last = "", line;
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                last = line;
            }
            return last;
        } catch (FileNotFoundException e) {
            System.out.println("File not found! check the correct file path.");
        }
        return null;

    }

    //This method is to read all the lines from a csv file in form of list inside list
    public static List<List<String>> alllines(String filepath) throws IOException {
        try {
            String line = "";
            List<List<String>> productDetails = new ArrayList<>();
            File file = new File(filepath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] products = line.split(",");
                productDetails.add(Arrays.asList(products));
            }
            return productDetails;

        } catch (FileNotFoundException e) {
            System.out.println("File not found! check the correct file path.");
        }
        return null;
    }

    public static void main(String args[]) throws IOException {
        CsvHandling c = new CsvHandling();
        c.readSingleline("C:\\Users\\AMDANAWADE\\Downloads\\CSV\\src\\test\\Utilities\\DATAFILE.csv");
        String[] Data;
        Data = c.readSingleline("C:\\Users\\AMDANAWADE\\Downloads\\CSV\\src\\test\\Utilities\\DATAFILE.csv");
        System.out.println(Data[2]);

        CsvHandling c1 = new CsvHandling();
        c1.readFromLast("C:\\Users\\AMDANAWADE\\Downloads\\CSV\\src\\test\\Utilities\\DATAFILE.csv");
        System.out.println(c1.readFromLast("C:\\Users\\AMDANAWADE\\Downloads\\CSV\\src\\test\\Utilities\\DATAFILE.csv"));

        CsvHandling c2 = new CsvHandling();
        c2.alllines("C:\\Users\\AMDANAWADE\\Downloads\\CSV\\src\\test\\Utilities\\DATAFILE.csv");
        System.out.println(c2.alllines("C:\\Users\\AMDANAWADE\\Downloads\\CSV\\src\\test\\Utilities\\DATAFILE.csv"));

    }
}