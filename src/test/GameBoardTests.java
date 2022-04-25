package test;

import main.GameBoard;
import main.Ship;
import main.ShipRotation;
import main.ShipTypes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTests{
    GameBoard gameBoard;
    GameBoard PCgameBoard;
    @Before
    public void setup(){
        gameBoard = new GameBoard(6);
        PCgameBoard = new GameBoard(6);
    }

    @Test
    public void isGameBoardGridFilledCorrectly(){
        String exceptedBoard =
                        " ~  ~  ~  ~  ~  ~ \n" +
                        " ~  ~  ~  ~  ~  ~ \n" +
                        " ~  ~  ~  ~  ~  ~ \n" +
                        " ~  ~  ~  ~  ~  ~ \n" +
                        " ~  ~  ~  ~  ~  ~ \n" +
                        " ~  ~  ~  ~  ~  ~ \n";

        assertEquals(exceptedBoard, gameBoard.show());
        assertEquals(exceptedBoard, PCgameBoard.show());
    }

    @Test
    public void cellSelectionTest(){
        assertEquals(" ~ ", PCgameBoard.select(2, 2));
        assertEquals("Incorrect coordinates", PCgameBoard.select(2, 12));
    }

    @Test
    public void cellMarkedProperly(){
        PCgameBoard.setHit(2, 2);
        assertEquals(" X ", PCgameBoard.select(2, 2));
        PCgameBoard.setShip(1, 1);
        assertEquals(" ■ ", PCgameBoard.select(1, 1));
        PCgameBoard.setMiss(2, 1);
        assertEquals(" O ", PCgameBoard.select(2, 1));
        assertEquals(-1, PCgameBoard.setHit(8, 8));
    }

    @Test
    public void isShipNotPlaceableBeyondTheGrid(){
        Ship ship = new Ship(ShipTypes.Battleship, 3, 3, ShipRotation.Horizontal);
        assertFalse(PCgameBoard.isShipDeployable(ship));
    }

    @Test
    public void isShipPlacedCorrectly(){
        Ship ship1 = new Ship(ShipTypes.Destroyer, 2, 2, ShipRotation.Horizontal);
        Ship ship2 = new Ship(ShipTypes.Destroyer, 1, 2, ShipRotation.Vertical);
        PCgameBoard.deployShip(ship1);
        PCgameBoard.deployShip(ship2);
        String exceptedBoard =
                        " ~  ~  ~  ~  ~  ~ \n" +
                        " ~  ~  ~  ~  ~  ~ \n" +
                        " ~  ■  ■  ■  ■  ■ \n" +
                        " ~  ■  ~  ~  ~  ~ \n" +
                        " ~  ■  ~  ~  ~  ~ \n" +
                        " ~  ■  ~  ~  ~  ~ \n";
        assertEquals(exceptedBoard, PCgameBoard.show());
    }

    @Test
    public void areShipsNotCrossingEachOther(){
        Ship ship1 = new Ship(ShipTypes.Destroyer, 1, 1, ShipRotation.Vertical);
        Ship ship2 = new Ship(ShipTypes.Destroyer, 0, 2, ShipRotation.Horizontal);
        PCgameBoard.deployShip(ship1);
        assertFalse(PCgameBoard.isShipDeployable(ship2));
    }

    @Test
    public void selectionTestAfterShipDeployment(){
        Ship ship = new Ship(ShipTypes.Destroyer, 1, 1, ShipRotation.Horizontal);
        PCgameBoard.deployShip(ship);
        assertEquals(" ■ ", PCgameBoard.select(1, 1));
        assertEquals(" ■ ", PCgameBoard.select(1, 3));
        assertEquals(" ~ ", PCgameBoard.select(3, 1));
    }

    @Test
    public void isShotSpotMarkedAsMissOrHitCorrectly(){
        Ship ship = new Ship(ShipTypes.Destroyer, 0, 1, ShipRotation.Vertical);
        PCgameBoard.deployShip(ship);
        assertEquals(1 , gameBoard.shoot(1, 0, PCgameBoard));
        assertEquals(1 , gameBoard.shoot(1, 4, PCgameBoard));
    }

    @Test
    public void isPlayerNotAbleToShootTheSameLocationTwice(){
        Ship ship = new Ship(ShipTypes.Destroyer, 0, 1, ShipRotation.Vertical);
        PCgameBoard.deployShip(ship);
        assertEquals(1 , gameBoard.shoot(1, 0, PCgameBoard));
        assertEquals(0 , gameBoard.shoot(1, 0, PCgameBoard));
    }
}
