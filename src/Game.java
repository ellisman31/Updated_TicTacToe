import Interface.GameInterface;

import java.util.Random;
import java.util.Scanner;

public class Game implements GameInterface {

    public TableConfiguration tableConfiguration;
    public int inputTableRowSize;
    public int inputTableColSize;
    public Scanner scanner;
    public Player player;
    public Computer computer;
    public boolean isGameOver;
    public Table[][] gameTable;
    public Random random;
    public GameLogic gameLogic;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.player = new Player();
        this.computer = new Computer();
        beforeStartGame();
        this.tableConfiguration = new TableConfiguration(getInputTableRowSize(), getInputTableColSize());
        this.isGameOver = false;
        this.gameTable = getTableConfiguration().getTable();
        this.gameLogic = new GameLogic(getTableConfiguration(), getPlayer(), getComputer());
        startGame();
    }

    private void beforeStartGame() {
        System.out.println("Welcome to the Tic Tac Toe!\n Please provide the table size before start to play the game!");
        askForTableSize();
    }

    @Override
    public void startGame() {
        while (!isGameOver()) {
            //Clear terminal.
            System.out.print("\033[H\033[2J");
            System.out.flush();
            getTableConfiguration().printTable();
            askForPlacement();
        }
    }

    private void askForTableSize() {
        System.out.print("Row size of the table: ");
        int askForRowSize = getScanner().nextInt();
        System.out.print("Col size of the table: ");
        int askForColSize = getScanner().nextInt();
        while (askForRowSize < 3 || askForColSize < 3) {
            System.out.println("The table should be at least 3 x 3!");
            if (askForRowSize < 3) {
                System.out.print("Please provide a new row size of the table: ");
                askForRowSize = getScanner().nextInt();
            }
            if (askForColSize < 3) {
                System.out.print("Please provide a new col size of the table: ");
                askForColSize = getScanner().nextInt();
            }
        }
        setInputTableRowSize(askForRowSize);
        setInputTableColSize(askForColSize);
    }

    private void askForPlacement() {
        System.out.print("Please provide the row position: ");
        int playerRowPosition = getScanner().nextInt()-1;
        System.out.print("Please provide the col position: ");
        int playerColPosition = getScanner().nextInt()-1;
        while (playerRowPosition < 0 || playerColPosition < 0 ||
         playerRowPosition >= getTableConfiguration().getRowTableSize() || playerColPosition >= getTableConfiguration().getColTableSize()) {
            if (playerRowPosition < 0 || playerRowPosition >= getTableConfiguration().getRowTableSize()) {
                System.out.print("The row position is invalid!\nPlease provide a new row position: ");
                playerRowPosition = getScanner().nextInt() - 1; }
            if (playerColPosition < 0 || playerColPosition >= getTableConfiguration().getColTableSize()) {
                System.out.print("The col position is invalid!\nPlease provide a new col position: ");
                playerColPosition = getScanner().nextInt() - 1;
            }
        }
        String playerDesign = getPlayer().getDesign();
        placePlacementAndCheckWin(playerRowPosition, playerColPosition, playerDesign);

    }

    private void placePlacementAndCheckWin(int playerRowPosition, int playerColPosition, String playerDesign) {
        if (checkPlacementIsValid(playerRowPosition, playerColPosition)) {
            Table playerTable = getTableConfiguration().getTable()[playerRowPosition][playerColPosition];
            playerTable.setDesign(playerDesign);
            getPlayer().getTablePosition().add(playerTable);
            randomPlacementForComputer();
            boolean isPlayerWin = getGameLogic().isWinningCondition(getPlayer().getDesign());
            boolean isComputerWin = getGameLogic().isWinningCondition(getComputer().getDesign());
            if (isPlayerWin) {
                System.out.println("You have won the game!");
                setGameOver(true);
                getTableConfiguration().printTable();
            } else if (isComputerWin) {
                System.out.println("The computer has won the game!");
                setGameOver(true);
                getTableConfiguration().printTable();
            }
        } else {
            System.out.println(errorMessage());
            askForPlacement();
        }
    }

    private void randomPlacementForComputer() {
        int randomRowPosition = getRandom().nextInt(getTableConfiguration().getRowTableSize());
        int randomColPosition = getRandom().nextInt(getTableConfiguration().getColTableSize());
        String computerDesign = getComputer().getDesign();
        while (!checkPlacementIsValid(randomRowPosition, randomColPosition)) {
            randomRowPosition = getRandom().nextInt(getTableConfiguration().getRowTableSize());
            randomColPosition = getRandom().nextInt(getTableConfiguration().getColTableSize());
        }
        Table computerTable = getTableConfiguration().getTable()[randomRowPosition][randomColPosition];
        computerTable.setDesign(computerDesign);
        getComputer().getTablePosition().add(computerTable);
    }

    private boolean checkPlacementIsValid(int rowPosition, int colPosition) {
        Table checkTable = getGameTable()[rowPosition][colPosition];
        return rowPosition <= getTableConfiguration().getRowTableSize() &&
                colPosition <= getTableConfiguration().getColTableSize() &&
                !getPlayer().getTablePosition().contains(checkTable) &&
                !getComputer().getTablePosition().contains(checkTable);
    }

    private String errorMessage() {
        String errorMessage;
        errorMessage = "The given placements had been already placed on the table! Please provide new placements!";
        return errorMessage;
    }

    public TableConfiguration getTableConfiguration() {
        return tableConfiguration;
    }

    public void setTableConfiguration(TableConfiguration tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
    }

    public int getInputTableRowSize() {
        return inputTableRowSize;
    }

    public void setInputTableRowSize(int inputTableRowSize) {
        this.inputTableRowSize = inputTableRowSize;
    }

    public int getInputTableColSize() {
        return inputTableColSize;
    }

    public void setInputTableColSize(int inputTableColSize) {
        this.inputTableColSize = inputTableColSize;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
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

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Table[][] getGameTable() {
        return gameTable;
    }

    public void setGameTable(Table[][] gameTable) {
        this.gameTable = gameTable;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
}
