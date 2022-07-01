package Utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelHandling implements IDataHandler {
    private String filePath;
    private String sheetName = null;
    private int sheetNo = 0;
    private Workbook wb;
    private Sheet sh;
    private int headerRow = 0;
    /***
     * Constructor to initialize the workbook using file location and sheet name
     * @param file_location file path is passed as string
     * @param sheet_name is sheet name in file
     */
    public ExcelHandling(String file_location, String sheet_name) {

        this.filePath = "resources\\" + file_location;
        this.sheetName = sheet_name;
        try {
            this.wb = WorkbookFactory.create(new FileInputStream(filePath));
            this.sh = wb.getSheet(sheetName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /***
     * Constructor to initialize the workbook using file location and sheet number
     * @param file_location file path is passed as string
     * @param sheet_no is sheet number in file
     */
    public ExcelHandling(String file_location, int sheet_no) {
        this.filePath = file_location;
        this.sheetNo = sheet_no;
        try {
            this.wb = WorkbookFactory.create(new FileInputStream(filePath));
            this.sh = wb.getSheetAt(sheetNo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * This method is to get total number of rows in a sheet
     * @return returns the total number of rows
     */
    public int getTotalRowCount() {
        int rowCount = 0;
        try {
            rowCount = sh.getPhysicalNumberOfRows();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return rowCount;
    }

    /***
     * This method is to get total number of columns
     * @return  returns the total number of columns
     */
    public int getColCount() {
        int colCount = 0;
        try {
            colCount = sh.getRow(0).getPhysicalNumberOfCells();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return colCount;
    }

    /***
     * This method is to row size in a file
     * @return row size
     */
    public int getRowSize() {
        return sh.getLastRowNum() + 1;
    }
    /***
     * This method is to get single cell value using row number and column number
     * @param row_no is row number
     * @param column_no is column number
     * @return return single cell value
     */
    public String get_cell_value(int row_no, int column_no) {
        return new DataFormatter().formatCellValue(sh.getRow(row_no).getCell(column_no));
    }

    /***
     * This method is to get column index that matches the given value in row
     * @param num is row index
     * @param value is value in row
     * @return returns column index
     */
    public int get_column_index_having_value(int num, String value) {
        int index = -1;
        for (int i = 0; i < getColCount(); i++) {
            if (get_cell_value(num, i).equalsIgnoreCase(value)) {
                index = i;
            }
        }
        return index;
    }

    /***
     * This method is to get all values in a single row
     * @param i is row index
     */
    public void get_row_values(int i) {
        Row row = sh.getRow(i);
        for (Cell cell : row)
            System.out.println(cell.getStringCellValue());

    }

    /***
     * This method is to get row index having a value in column
     * @param col_num is column number
     * @param value is column value
     * @return returns row index that matches with value in a column
     */
    public int get_row_index(int col_num, String value) {
        int index = -1;
        for (int row_num = 0; row_num < getTotalRowCount(); row_num++) {
            if (get_cell_value(row_num, col_num).equalsIgnoreCase(value)) {
                index = row_num;
                break;
            }
        }
        return index;
    }
    /***
     * This method is to check if row is empty
     * @param rowNum is row index
     * @return returns true if row is empty
     */
    public boolean isEmptyRow(int rowNum) {
        Row rw = sh.getRow(rowNum);
        return rw == null;
    }

    /***
     * This method is to get row indexes having value in column
     * @param col_num is column number
     * @param value is column value
     * @return returns list of row indexes
     */
    public List<Integer> get_row_indexes_having_value_in_column(int start_row, int col_num, String value) {
        List<Integer> row_indexes = new ArrayList<>();
        for (int row_num = start_row; row_num < getTotalRowCount(); row_num++) {
            if (isEmptyRow(row_num))
                return Collections.emptyList();
            if (get_cell_value(row_num, col_num).equalsIgnoreCase(value)) {
                row_indexes.add(row_num);
            }
        }
        return row_indexes;
    }
    /***
     * This method is to get header names
     * @return  returns the list of header names
     */
    public List<String> get_header_names() {
        Row row = sh.getRow(headerRow);
        int row_len = row.getLastCellNum();
        List<String> colNames = new ArrayList<>();
        for (int i = 0; i < row_len; i++) {
            colNames.add(get_cell_value(headerRow, i));
        }
        return colNames;
    }
    /***
     * This method is to get single row data
     * @param rowNum is row index
     * @return returns map of values containing header names and cell value
     */
    @Override
    public Map<String, String> getData(int rowNum) {
        if (isEmptyRow(rowNum))
            return Collections.emptyMap();
        List<String> headerNames = get_header_names();
        Row row = sh.getRow(rowNum);
        Map<String, String> dataMap = new LinkedHashMap<>();
        for (Cell cell : row) {
            int index = cell.getColumnIndex();
            if (headerNames.get(index).trim().length() > 0)
                dataMap.put(headerNames.get(index).trim(), get_cell_value(rowNum, cell.getColumnIndex()));
        }
        return Collections.unmodifiableMap(dataMap);
    }

    /***
     * This method is to get all data from specific rows
     * @param row_indexes is list of row indexes
     * @return returns a list of map contains header values as key and row value as value in map
     */
    public List<Map<String, String>> get_test_cases(List<Integer> row_indexes) {
        List<Map<String, String>> testcases = new ArrayList<>();
        for (int i = 0; i < row_indexes.size(); i++) {
            testcases.add(getData(row_indexes.get(i)));
        }
        return testcases;
    }
    /***
     * This method is to get data from specific rows
     * @param row_indexes is list of row indexes
     * @return returns a list of row values
     */
    public List<String> get_values(List<Integer> row_indexes, String key) {
        List<Map<String, String>> testcases = get_test_cases(row_indexes);
        List<String> values = new ArrayList<>();
        for (int i = 0; i < testcases.size(); i++) {
            values.add(testcases.get(i).get(key));
        }
        return values;
    }

    /***
     * This method is to get single test details
     * @param value is the row value
     * @param col_name is column name
     * @return returns map of key containing header names and value containing row values
     */
    public Map<String, String> get_single_test_details(String value, String col_name) {
        int col_index = get_column_index_having_value(0, col_name);
        int row_index = get_row_index(col_index, value);
        Map<String, String> data = getData(row_index);
        return data;
    }
    /***
     * This method is to get all data from excel file
     * @return returns list of map of values
     */
    @Override
    public List<Map<String, String>> getAllData() {
        List<String> headerNames = get_header_names();
        int no_of_rows = getRowSize();
        List<Map<String, String>> dataList = new ArrayList<>();
        for (int i = 1; i < no_of_rows - 1; i++) {
            Row row = sh.getRow(i);
            if (isEmptyRow(i))
                continue;
            Map<String, String> dataMap = new LinkedHashMap<>();
            for (Cell cell : row) {
                int index = cell.getColumnIndex();
                if (headerNames.get(index).trim().length() > 0)
                    dataMap.put(headerNames.get(index).trim(), get_cell_value(cell.getRowIndex(), cell.getColumnIndex()));
            }
            ;
            dataList.add(dataMap);
        }
        return Collections.unmodifiableList(dataList);
    }
}

