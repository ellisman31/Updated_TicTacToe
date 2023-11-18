import java.util.ArrayList;

public abstract class PlayerBase {

    public String design;
    public ArrayList<Table> tablePosition;

    public PlayerBase() {
        this.tablePosition = new ArrayList<>();
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public ArrayList<Table> getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(ArrayList<Table> tablePosition) {
        this.tablePosition = tablePosition;
    }
}
