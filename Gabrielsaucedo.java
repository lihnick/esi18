package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Gabrielsaucedo extends Ship {
    
    public Gabrielsaucedo() {
        this.initializeName("Gabe");
        this.initializeOwner("Gabe");
        this.initializeHull(2);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(4);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        // instruction for ship behavior here
        this.fire(arena, 1, 3);

        // get a list of nearby ships
        List<Ship> targets = this.getNearbyShips(arena);
        // access the list if there are any nearby ships
        if (targets.size() > 0) {
                // get the first ship in the targets list
                Ship ship = targets.get(0);
                Coord location = ship.getCoord();
                int x = location.getX();
                int y = location.getY();
                this.fire(arena, x, y);
     int numMoves = this.getRemainingMoves();

    while(numMoves > 0) {
        // move north, west until there's only one turn left
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.WEST);
        // update the number of turn remaining
        numMoves = this.getRemainingMoves();
    }
    this.move(arena, Direction.EAST);
     // instruction for ship behavior here
    this.fire(arena, 1, 3);

    // get a list of nearby ships
    List<Ship> targets = this.getNearbyShips(arena);

    // loop over all nearby ships
    for (int index = 0; index < targets.size(); index += 1) {
        Ship shipInfo = targets.get(index);
        System.out.println("One nearby ship has " + shipInfo.getHealth() + " HP left.");
    }

    System.out.println("There are " + targets.size() + " number of nearby ships");

    // if there is atleast one nearby ships
    if (targets.size() > 0) {
        // get the first ship in the list
        Ship first = targets.get(0);
        // get the fourth ship in the list
        Ship fourth = targets.get(0);
    }
    }
}
        
    }
    
}