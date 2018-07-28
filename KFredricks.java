package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class KFredricks extends Ship {
    
    public KFredricks() {
        this.initializeName("Kamakazi Bois");
        this.initializeOwner("Kai");
        this.initializeHull(2);
        this.initializeFirepower(1);
        this.initializeSpeed(3);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        Coord location = this.getCoord();
        int x = location.getX();
        int y = location.getY();
        this.fire(arena, x+1, y);
    }
    
}