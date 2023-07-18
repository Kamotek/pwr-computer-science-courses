import java.util.ArrayList;

public class Obj {
    
    String value;
    ArrayList<Integer> rows = new ArrayList<>();

    public Obj(String value, int row) {
        this.value = value;
        this.rows.add(row);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<Integer> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Integer> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i : rows) {
            sb.append(i);
            sb.append(", ");
        }

        String rowNumbers = sb.toString();

        return value + "    " + rowNumbers;
    }

}
