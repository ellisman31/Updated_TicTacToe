import Interface.GameLogicInterface;

import java.util.Objects;

public class GameLogic implements GameLogicInterface {

    public TableConfiguration tableConfiguration;
    private int winningConditionPoint;
    private Player player;
    private Computer computer;
    public int conditionRowCounter;
    public int conditionColCounter;
    public int conditionDiagonalCounter;

    public GameLogic(TableConfiguration tableConfiguration, Player player, Computer computer) {
        this.tableConfiguration = tableConfiguration;
        this.player = player;
        this.computer = computer;
        this.winningConditionPoint = 3;
        this.conditionRowCounter = 0;
        this.conditionColCounter = 0;
        this.conditionDiagonalCounter = 0;
    }

    @Override
    public boolean isWinningCondition(String design) {
        int rowTableSize = getTableConfiguration().getRowTableSize();
        int colTableSize = getTableConfiguration().getColTableSize();
        for (int row = 0; row < rowTableSize; row++) {
            for (int col = 0; col < colTableSize; col++) {

                if (isEnoughWinningPoints()) {
                    return true;
                } else {
                    setConditionRowCounter(0);
                    setConditionColCounter(0);
                    setConditionDiagonalCounter(0);
                    checkPositionForWinning(row, col, colTableSize, rowTableSize, design);
                }
            }
        }
        return false;
    }

    public void checkPositionForWinning(int row, int col, int colTableSize, int rowTableSize, String design) {
        Table currentTable = getTableConfiguration().getTable()[row][col];
        if (getPlayer().getTablePosition().contains(currentTable)) {
            if (Objects.equals(getTableConfiguration().getTable()[row][col].getDesign(), design)) {
                setConditionRowCounter(getConditionRowCounter() + 1);
                setConditionColCounter(getConditionColCounter() + 1);
                setConditionDiagonalCounter(getConditionDiagonalCounter() + 1);
            }
            if ((col + 1) < colTableSize) {
                if (Objects.equals(getTableConfiguration().getTable()[row][col + 1].getDesign(), design)) {
                    setConditionRowCounter(getConditionRowCounter() + 1);
                }
                if ((row + 1) < rowTableSize) {
                    if (Objects.equals(getTableConfiguration().getTable()[row + 1][col + 1].getDesign(), design)) {
                        setConditionDiagonalCounter(getConditionDiagonalCounter() + 1);
                    }
                    if (Objects.equals(getTableConfiguration().getTable()[row + 1][col].getDesign(), design)) {
                        setConditionColCounter(getConditionColCounter() + 1);
                    }
                    if ((row - 1) >= 0 && Objects.equals(getTableConfiguration().getTable()[row - 1][col].getDesign(), design)) {
                        setConditionColCounter(getConditionColCounter() + 1);
                    }
                }
            }
            if ((col - 1) >= 0) {
                if (Objects.equals(getTableConfiguration().getTable()[row][col - 1].getDesign(), design)) {
                    setConditionRowCounter(getConditionRowCounter() + 1);
                }
                if ((row - 1) >= 0) {
                    if (Objects.equals(getTableConfiguration().getTable()[row - 1][col - 1].getDesign(), design)) {
                        setConditionDiagonalCounter(getConditionDiagonalCounter() + 1);
                    }
                }
            }
        }
    }

    private boolean isEnoughWinningPoints() {
        return getConditionRowCounter() == getWinningConditionPoint()
                || getConditionColCounter() == getWinningConditionPoint() || getConditionDiagonalCounter() == getWinningConditionPoint();
    }

    public TableConfiguration getTableConfiguration() {
        return tableConfiguration;
    }

    public void setTableConfiguration(TableConfiguration tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
    }

    public int getWinningConditionPoint() {
        return winningConditionPoint;
    }

    public void setWinningConditionPoint(int winningConditionPoint) {
        this.winningConditionPoint = winningConditionPoint;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public int getConditionRowCounter() {
        return conditionRowCounter;
    }

    public void setConditionRowCounter(int conditionRowCounter) {
        this.conditionRowCounter = conditionRowCounter;
    }

    public int getConditionColCounter() {
        return conditionColCounter;
    }

    public void setConditionColCounter(int conditionColCounter) {
        this.conditionColCounter = conditionColCounter;
    }

    public int getConditionDiagonalCounter() {
        return conditionDiagonalCounter;
    }

    public void setConditionDiagonalCounter(int conditionDiagonalCounter) {
        this.conditionDiagonalCounter = conditionDiagonalCounter;
    }
}
