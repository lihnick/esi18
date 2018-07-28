package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Amartinez2 extends Ship {
    
    public Amartinez2() {
        this.initializeName("Patrick Star");
        this.initializeOwner("Your Name");
        this.initializeHull(1);
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
        int numMoves = this.getRemainingMoves();
        while(numMoves > 0) {
            this.move(arena, Direction.EAST);
            this.move(arena, Direction.EAST);
            numMoves = this.getRemainingMoves();
        } 
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.EAST);
        Coord location = this.getCoord();
        int x = location.getX();
        int y = location.getY();
        this.fire(arena, x+3, y);
        List<Ship> targets = this.getNearbyShips(arena);
        if(targets.size() > 0);
        Ship ship = targets.get(0);
        List<Ship> nearby = this.getNearbyShips(arena);
        for (int i = 0; i < nearby.size(); i++){        
            Ship other = nearby.get(i);
         Coord placement = ship.getCoord();
        int enemyx = placement.getX();
        int enemyy = placement.getY();
            boolean isOnMyTeam = this.isSameTeamAs(other);
            if (isOnMyTeam) {
                System.out.println("This ship is on my Team!");
            } else {
                System.out.println("This ship is an enemy.");
                this.fire(arena, x,y);}
            }
    }
    
}