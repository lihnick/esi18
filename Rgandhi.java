package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class RGandhi extends Ship {
    
    public SimpleShip() {
        this.initializeName("The Mahatma");
        this.initializeOwner("Raj");
        this.initializeHull(2);
        this.initializeFirepower(4);
        this.initializeSpeed(2);
        this.initializeRange(2);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        
        List<Ship> nearby = this.getNearbyShips(arena);
        
        for (int i = 0; i < nearby.size(); i++) {
            if(this.isSameTeamas(nearby,get(i))) {
        }
        else {
            Ship enemy = nearby.get(i);
            Coord target = enemy.getCoord();
            int x = target.getX();
            int y = target.getY();
            this.fire(arena, x, y);
            this.fire(arena, x, y);
            this.fire(arena, x, y);
            this.fire(arena, x, y);
            
        }
    }
    
}