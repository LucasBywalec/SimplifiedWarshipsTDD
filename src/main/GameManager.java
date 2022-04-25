package main;

import java.util.ArrayList;
import java.util.Locale;

public class GameManager {
    private final ArrayList<Ship> shipsList = new ArrayList<>();
    private int combinedHp;

    public int inputValidator(String input){
        input = input.toUpperCase(Locale.ROOT);
        if(input.equals("Q")) return 0;
        char[] inputCharArr = input.toCharArray();
        if(inputCharArr.length != 2) return -1;
        if(Character.isLetter(inputCharArr[0]) && Character.isDigit(inputCharArr[1])) return 1;
        return -1;
    }

    public int[] inputHandler(String input) {
        input = input.toUpperCase(Locale.ROOT);
        char[] inputCharArr = input.toCharArray();
        int[] returnArr = new int[2];
        returnArr[0] = inputCharArr[0]-'A';
        returnArr[1] = Character.getNumericValue(inputCharArr[1]);
        return returnArr;
    }


    public ArrayList<Ship> getShipsList(){
        return shipsList;
    }

    public void printGrid(GameBoard gb){
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        int rowNum = 0;
        for (String row: gb.show().split("\n")){
            System.out.println(rowNum + row);
            rowNum++;
        }
    }

    public void addShipToList(Ship ship) {
        shipsList.add(ship);
        combinedHp += ship.getLen();
    }

    public int getCurrentCombinedHP() {
        return combinedHp;
    }

    public void decreaseShipsHp(int row, int col){
        for (Ship ship: shipsList) {
            var points = ship.getOccupiedCells();
            for (int[] point: points) {
                if(point[1] == col && point[0] == row){
                    ship.decreaseHP();
                    combinedHp -= 1;
                    if(ship.getCurrentHP() == 0) {
                        System.out.println("#########\n" + ship.getType() + " sunk!\n#########");
                        return;
                    }
                }
            }
        }
    }
}
