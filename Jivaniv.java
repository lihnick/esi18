package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Jivaniv extends Ship {
    
    public Jivaniv() {
        this.initializeName("jivaniv");
        this.initializeOwner("Julia Ivaniv");
        this.initializeHull(4);
        this.initializeFirepower(3);
        this.initializeSpeed(2);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        Coord coord = this.getCoord():
int x = coord.getX();
int y = coord.getY();
System.out.println("My ship is at (" + x + ", " + y + ").");
int numMoves = this.getRemainingMoves();

        if (enemy.getSpeed() > this.getSpeed()) {
    System.out.println("The enemy ship will move before my ship.");
        if (x < 5){
            while(numMoves > 0) {
        // move north, west until there's only one turn left
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.WEST);
        // update the number of turn remaining
        numMoves = this.getRemainingMoves();
    }
    this.move(arena, Direction.EAST);
}
if (x > 5){
     while(numMoves > 0) {
        // move north, west until there's only one turn left
        this.move(arena, Direction.NORTH);
        this.move(arena, Direction.WEST);
        // update the number of turn remaining
        numMoves = this.getRemainingMoves();
    }
    this.move(arena, Direction.EAST);
}
}
            
        }
} else if (enemy.getSpeed() < this.getSpeed()) {
    System.out.println("The enemy ship will move after my ship.");
} else {
    System.out.println("The enemy ship might move before or after my ship.");
}

        
    }
    
}