package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        //Setting up the game
        GameBoard playerBoard = new GameBoard(10);
        GameBoard computerBoard = new GameBoard(10);
        GameManager gameManager = new GameManager();

        //setting up th board
        String[] typesToDeploy = {"Battleship", "Destroyer", "Destroyer"};
        Random randomizer = new Random();
        for (int i = 0; i < 3; i++) {
            int col = randomizer.nextInt(0, 10);
            int row = randomizer.nextInt(0, 10);
            int rot = randomizer.nextInt(0, 2);
            Ship ship = new Ship(ShipTypes.valueOf(typesToDeploy[i]), col, row, rot > 0 ? ShipRotation.Vertical : ShipRotation.Horizontal);
            if(!computerBoard.isShipDeployable(ship)){
                i--;
            } else {
                gameManager.addShipToList(computerBoard.deployShip(ship));
            }
        }

        System.out.println("Welcome in Simplified Warships Game. The game will start automatically after this message.\n" +
                "If you want to quit, just type 'q' or 'Q'! Enjoy the game and good luck.");
        Scanner reader = new Scanner(System.in);
        while (true){
            gameManager.printGrid(playerBoard);

            int inputReturn;
            String line = reader.nextLine();
            inputReturn = gameManager.inputValidator(line);
            ArrayList<Ship> shipsArray = gameManager.getShipsList();

            if(inputReturn == 0) break;
            else if(inputReturn == -1){
                System.out.println("The command you passed is not valid.");
            }
            else{
                int[] cell = gameManager.inputHandler(line);
                int ret = playerBoard.shoot(cell[1], cell[0], computerBoard);
                if(ret == -1){
                    System.out.println("Incorrect coordinates.");
                    continue;
                }
                else if(ret == 0){
                    System.out.println("We've already shot this location");
                    continue;
                }
                gameManager.decreaseShipsHp(cell[0], cell[1]);
                if(gameManager.getCurrentCombinedHP() == 0){
                    System.out.println("#########\nYou've won!\n#########");
                    break;
                }
            }
        }
    }


}
