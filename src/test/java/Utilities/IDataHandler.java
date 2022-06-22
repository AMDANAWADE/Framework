package Utilities;

import java.util.List;
import java.util.Map;

interface IDataHandler {
    Map<String, String> getData(int num);
    List<Map<String, String>> getAllData();
}
