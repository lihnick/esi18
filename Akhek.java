package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Akhek extends Ship {
    
    public Akhek() {
        this.initializeName("Simple Ship");
        this.initializeOwner("Your Name");
        this.initializeHull(3);
        this.initializeFirepower(4);
        this.initializeSpeed(1);
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
            if ( this.isSameTeamAs(nearby.get(i)) ) {
            }
            else {
                Ship enemy = nearby.get(i);
                int health = enemy.getHealth();
                Coord enemyLoc = enemy.getCoord();
                int x = enemyLoc.getX();
                int y = enemyLoc.getY();
                while (health > 0) {
                    this.fire(arena, x, y);
                }
                
            }
        }
    }
    
}

