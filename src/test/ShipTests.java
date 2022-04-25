package test;

import main.Ship;
import main.ShipRotation;
import main.ShipTypes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShipTests {
    Ship ship;

    @Before
    public void setup(){
        ship = new Ship(ShipTypes.Destroyer, 0, 1, ShipRotation.Vertical);
    }

    @Test
    public void isOriginColAndRowReturnedProperly(){
        assertEquals(0, ship.getColOrigin());
        assertEquals(1, ship.getRowOrigin());
    }

    @Test
    public void isLengthreturnedProperly(){
        assertEquals(4, ship.getLen());
    }

    @Test
    public void isShipHpDecreased(){
        ship.decreaseHP();
        assertEquals(3, ship.getCurrentHP());
    }

    @Test
    public void areOccupiedCellsReturnedProperly(){
        int[][] arr = {{0, 1}, {0, 2}, {0, 3}, {0, 4}};
        var points = ship.getOccupiedCells();
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i][0], points[i][0]);
            assertEquals(arr[i][1], points[i][1]);
        }
    }
}
