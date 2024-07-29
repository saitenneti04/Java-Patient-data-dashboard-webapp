package uk.ac.ucl.model;

import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class Model{
  private DataFrame dataFrame;

  public Model(){
    this.dataFrame = new DataFrame();
  }

  public void loadCSVData(String fileName){
    DataLoader loader = new DataLoader();
    this.dataFrame = loader.loadCSVData(fileName);
  }

  //returns all data from dataframe as a list of maps
  //each map is a row with column names as keys
  public List<Map<String, String>> getAllData() {
    List<Map<String, String>> allData = new ArrayList<>();
    int rowCount = this.dataFrame.getRowCount();

    for (int i = 0; i < rowCount; i++) {
      Map<String, String> rowData = new LinkedHashMap<>();
      for (String columnName : this.dataFrame.getColumnNames()) {
        String value = this.dataFrame.getValue(columnName, i);
        rowData.put(columnName, value);
      }
      allData.add(rowData);
    }

    return allData;
  }

  //adds a new row of data to the dataframe
  public void addDataRow(Map<String, String> data) {
    for (Map.Entry<String, String> entry : data.entrySet()) {
      this.dataFrame.addValue(entry.getKey(), entry.getValue());
    }
  }

  //updates specific value in the dataframe
  public void updateDataValue(String columnName, int rowIndex, String newValue) {
    this.dataFrame.putValue(columnName, rowIndex, newValue);
  }

  public List<String> getColumnNames(){
    return this.dataFrame.getColumnNames();
  }

}
