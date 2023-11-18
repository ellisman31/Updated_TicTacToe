import Interface.TableConfigurationInterface;

public class TableConfiguration implements TableConfigurationInterface {

    public Table[][] table;
    public int rowTableSize;
    public int colTableSize;

    public TableConfiguration(int rowTableSize, int colTableSize) {
        this.rowTableSize = rowTableSize;
        this.colTableSize = colTableSize;
        this.table = new Table[getRowTableSize()][getColTableSize()];
        initTable();
    }

    private void initTable() {
        for (int row = 0; row < getRowTableSize(); row++) {
            for (int col = 0; col < getColTableSize(); col++) {
                getTable()[row][col] = new Table(row,col);
            }
        }
    }

    @Override
    public void printTable() {
        for (int row = 0; row < getRowTableSize(); row++) {
            for (int col = 0; col < getColTableSize(); col++) {
                System.out.print(getTable()[row][col].getDesign());
            }
            System.out.println();
        }
    }

    public Table[][] getTable() {
        return table;
    }

    public void setTable(Table[][] table) {
        this.table = table;
    }

    public int getRowTableSize() {
        return rowTableSize;
    }

    public void setRowTableSize(int rowTableSize) {
        this.rowTableSize = rowTableSize;
    }

    public int getColTableSize() {
        return colTableSize;
    }

    public void setColTableSize(int colTableSize) {
        this.colTableSize = colTableSize;
    }
}
