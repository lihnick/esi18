package esi18.simple; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class SimpleShip extends Ship {
    
    public SimpleShip() {
        this.initializeName("Simple Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(1);
        this.initializeFirepower(1);
        this.initializeSpeed(1);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        // get a random integer between 0 (inclusive) and 4 (exclusive)
        int randomMovement = arena.getRandom().nextInt(4);

        // randomMovement ranges from 0-3, which corresponds to the 4 possible direction of movement
        if (randomMovement == 0) {
            this.move(arena, Direction.NORTH);
        }
        else if (randomMovement == 1) {
            this.move(arena, Direction.SOUTH);
        }
        else if (randomMovement == 2) {
            this.move(arena, Direction.WEST);
        }
        else if (randomMovement == 3) {
            this.move(arena, Direction.EAST);
        }

        // Get a list of nearby ships, ships that are within this ship's range
        // If there's no nearby ship, this list will be size 0
        List<Ship> nearby = this.getNearbyShips(arena);
        
        for (int i = 0; i < nearby.size(); i++) {
            Ship target = nearby.get(i);
            Coord location = target.getCoord();
            int x = location.getX();
            int y = location.getY();
            this.fire(arena, x, y);
        }
        
    }
    
}