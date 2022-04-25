package test;

import main.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameManagerTests {
    GameManager gameManager;
    GameBoard gameBoard;

    @Before
    public void setup(){
        gameManager = new GameManager();
        gameBoard = new GameBoard(10);

        gameManager.addShipToList(gameBoard.deployShip(new Ship(ShipTypes.Destroyer, 0, 0, ShipRotation.Horizontal)));
        gameManager.addShipToList(gameBoard.deployShip(new Ship(ShipTypes.Destroyer, 0, 2, ShipRotation.Horizontal)));
        gameManager.addShipToList(gameBoard.deployShip(new Ship(ShipTypes.Destroyer, 0, 4, ShipRotation.Horizontal)));
    }

    @Test
    public void isGameManagerInputValidated(){
        assertEquals(-1, gameManager.inputValidator("5A"));
        assertEquals(0, gameManager.inputValidator("q"));
        assertEquals(1, gameManager.inputValidator("C1"));
    }

    @Test
    public void isGameManagerTranslatingTheGridPositionProperly(){
        int[] arr = {0, 5};
        int[] pos = gameManager.inputHandler("A5");
        assertEquals(arr[0], pos[0]);
        assertEquals(arr[1], pos[1]);
    }

    @Test
    public void isShipListOfCorrectLength(){
        assertEquals(3, gameManager.getShipsList().size());
    }

    @Test
    public void isShipsCombinedHpSet(){
        assertEquals(12, gameManager.getCurrentCombinedHP());
    }

    @Test
    public void isShipsCombinedHpDecreasedProperly(){
        gameManager.decreaseShipsHp(0, 2);
        assertEquals(11, gameManager.getCurrentCombinedHP());
    }

    @Test
    public void isShipHpDecreasedCorrectly(){
        gameManager.decreaseShipsHp(0, 2);
        var list = gameManager.getShipsList();
        Ship ship = list.get(1);
        assertEquals(3, ship.getCurrentHP());
    };

}
