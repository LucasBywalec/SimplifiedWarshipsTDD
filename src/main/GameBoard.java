package main;


public class GameBoard {
    private final int size;
    private final String[][] grid;
    private final String water = " ~ ";
    private final String hit = " X ";
    private final String miss = " O ";
    private final String ship = " ■ ";

    public GameBoard(int size){
        this.size = size;
        grid = new String[size][size];

        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                grid[row][col] = water;
            }
        }
    }

    public String show() {
        String toShow = "";
        for (String[] row: grid) {
            for (String col: row) {
                toShow += col;
            }
            toShow += '\n';
        }
        return toShow;
    }

    public String select(int row, int col){
        if(row >= size || col >= size || row < 0 || col < 0) return "Incorrect coordinates";
        return grid[row][col];
    }

    public int setHit(int row, int col){
        if(row >= size || col >= size || row < 0 || col < 0) return -1;
        grid[row][col] = hit;
        return 0;
    }

    public int setMiss(int row, int col){
        if(row >= size || col >= size || row < 0 || col < 0) return -1;
        grid[row][col] = miss;
        return 0;
    }

    public int setShip(int row, int col){
        if(row >= size || col >= size || row < 0 || col < 0) return -1;
        grid[row][col] = ship;
        return 0;
    }

    public boolean isShipDeployable(Ship ship){
        int shipX = ship.getColOrigin();
        int shipY = ship.getRowOrigin();
        int shipLen = ship.getLen();
        int rot;
        if(ship.getRotation().equals(ShipRotation.Horizontal)){
            if(shipX + shipLen > this.size) return false;
            rot = 1;
        } else{
            if(shipY + shipLen > this.size) return false;
            rot = 2;
        }
        switch (rot){
            case 1:
                for (int i = 0; i < shipLen; i++) {
                    if(grid[shipY][shipX + i].equals(this.ship)) return false;
                }
                break;
            case 2:
                for (int i = 0; i < shipLen; i++) {
                    if(grid[shipY + i][shipX].equals(this.ship)) return false;
                }
                break;
        }
        return true;
    }
    public Ship deployShip(Ship ship) {
        int shipX = ship.getColOrigin();
        int shipY = ship.getRowOrigin();
        int shipLen = ship.getLen();
        int rot;
        if(ship.getRotation().equals(ShipRotation.Horizontal)){
            rot = 1;
        } else{
            rot = 2;
        }
        switch (rot){
            case 1:
                for (int i = 0; i < shipLen; i++) {
                    grid[shipY][shipX + i] = this.ship;
                }
                break;
            case 2:
                for (int i = 0; i < shipLen; i++) {
                    grid[shipY + i][shipX] = this.ship;
                }
                break;
        }
        return ship;
    }

    public int shoot(int row, int col, GameBoard AI) {
        if(row >= size || col >= size || row < 0 || col < 0) return -1;
        String cell = AI.select(row, col);
        if(cell.equals(hit) || cell.equals(miss)) return 0;
        if(cell.equals(water)){
            setMiss(row, col);
            AI.setMiss(row, col);
            return 1;
        } else {
            setHit(row, col);
            AI.setHit(row, col);
            return 1;
        }
    }
}
