package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Ezamudio extends Ship {
    
    public Ezamudio() {
        this.initializeName("Dig Bick");
        this.initializeOwner("Edwin Zamudio");
        this.initializeHull(4);
        this.initializeFirepower(4);
        this.initializeSpeed(2);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        List<Ship> targets = this.getNearbyShips(arena);
    
    for (int index = 0; index < targets.size(); index += 1) {
        Ship other = targets.get(index);
        boolean isOnMyTeam = this.isSameTeamAs(other);
        if (isOnMyTeam == false) {
            if((other.getFirepower()>this.getHealth()&&other.getHealth()<this.getFirepower()))
                {
                    Coord coord = this.getCoord();
                    int x = coord.getX();
                    int y = coord.getY();
                    coord = other.getCoord();
                    int x2 = coord.getX();
                    int y2 = coord.getY();
                    
                    this.move(arena,Direction.EAST);
                }
        }
    }
}
    
}