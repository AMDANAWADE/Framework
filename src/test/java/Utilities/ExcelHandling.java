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

    public int getRowSize() {
        return sh.getLastRowNum() + 1;
    }

    public String get_cell_value(int row_no, int column_no) {
        return new DataFormatter().formatCellValue(sh.getRow(row_no).getCell(column_no));
    }

    public int get_column_index_having_value(int num, String value) {
        int index = -1;
        for (int i = 0; i < getColCount(); i++) {
            if (get_cell_value(num, i).equalsIgnoreCase(value)) {
                index = i;
            }
        }
        return index;
    }

    public void get_row(int i) {
        Row row = sh.getRow(i);
        for (Cell cell : row)
            System.out.println(cell.getStringCellValue());

    }

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

    public boolean isEmptyRow(int rowNum) {
        Row rw = sh.getRow(rowNum);
        return rw == null;
    }

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

    public List<String> get_header_names() {
        Row row = sh.getRow(headerRow);
        int row_len = row.getLastCellNum();
        List<String> colNames = new ArrayList<>();
        for (int i = 0; i < row_len; i++) {
            colNames.add(get_cell_value(headerRow, i));
        }
        return colNames;
    }

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

    public List<Map<String, String>> get_test_cases(List<Integer> row_indexes) {
        List<Map<String, String>> testcases = new ArrayList<>();
        for (int i = 0; i < row_indexes.size(); i++) {
            testcases.add(getData(row_indexes.get(i)));
        }
        return testcases;
    }

    public List<String> get_values(List<Integer> row_indexes, String key) {
        List<Map<String, String>> testcases = get_test_cases(row_indexes);
        List<String> values = new ArrayList<>();
        for (int i = 0; i < testcases.size(); i++) {
            values.add(testcases.get(i).get(key));
        }
        return values;
    }


    public Map<String, String> get_test_details_using_classname_and_method(String method_name, String class_name, String method_col, String class_name_col) {
        int method_col_index = get_column_index_having_value(0, method_col);
        int class_col_index = get_column_index_having_value(0, class_name_col);
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < getTotalRowCount(); i++) {
            if (get_cell_value(i, method_col_index).equalsIgnoreCase(method_name)) {
                if (get_cell_value(i, class_col_index).equalsIgnoreCase(class_name))
                    data = getData(i);
            }
        }
        return data;
    }

    public Map<String, String> get_single_test_details(String name, String col_name) {
        int col_index = get_column_index_having_value(0, col_name);
        int row_index = get_row_index(col_index, name);
        Map<String, String> data = getData(row_index);
        return data;
    }

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

