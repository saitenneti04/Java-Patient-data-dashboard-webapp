package uk.ac.ucl.model;
import java.util.ArrayList;
import java.util.List;

public class DataFrame {
    private ArrayList<Column> columns;

    public DataFrame() {
        this.columns = new ArrayList<>();
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public List<String> getColumnNames() {
        List<String> names = new ArrayList<>();
        for (Column column : columns) {
            names.add(column.getName());
        }
        return names;
    }

    public int getRowCount() {
        if (columns.size() > 0) {
            return columns.get(0).getSize();
        }
        return 0;
    }

    public String getValue(String columnName, int row) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                return column.getRowValue(row);
            }
        }
        return null;
    }

    public void putValue(String columnName, int row, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.setRowValue(row, value);
                return;
            }
        }
    }

    public void addValue(String columnName, String value) {
        for (Column column : columns) {
            if (column.getName().equals(columnName)) {
                column.addRowValue(value);
                return;
            }
        }
    }
}
