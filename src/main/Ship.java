package main;

public class Ship {
    private final int col;
    private final int row;
    private final int len;
    private final ShipTypes type;
    private final ShipRotation rotation;
    private int HP;

    public Ship(ShipTypes type, int col, int row, ShipRotation rotation) {
        this.type = type;
        this.len = type.equals(ShipTypes.Destroyer) ? 4 : 5;
        this.col = col;
        this. row = row;
        this.rotation = rotation;
        this.HP = len;
    }

    public int getColOrigin() {
        return col;
    }

    public int getRowOrigin() {
        return row;
    }

    public int getLen() {
        return len;
    }

    public ShipRotation getRotation(){
        return rotation;
    }

    public int[][] getOccupiedCells(){
        int[][] pointArr = new int[len][];
        for (int i = 0; i < len; i++) {
            pointArr[i] = new int[2];
            pointArr[i][0] = col + (rotation.equals(ShipRotation.Horizontal) ? i : 0);
            pointArr[i][1] = row + (rotation.equals(ShipRotation.Vertical) ? i : 0);
        }
        return pointArr;
    }

    public void decreaseHP() {
        HP -= 1;
    }

    public int getCurrentHP() {
        return HP;
    }

    public ShipTypes getType() {
        return type;
    }
}
