package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Aespitia extends Ship {
    
    public Aespitia() {
        this.initializeName("Aespitia");
        this.initializeOwner("Your Name");
        this.initializeHull(1);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(5);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
      
        Coord location = this.getCoord();
        
         List<Ship> nearby = this.getNearbyShips(arena);

        // loop through the list of nearby ships
        for (int i = 0; i < nearby.size(); i++) {
            if ( this.isSameTeamAs(nearby.get(i)) ) {
                // if same team, don't shoot
            }
            else {
                Ship enemy = nearby.get(i);
                Coord enemyLoc = enemy.getCoord();
                int x = enemyLoc.getX();
                int y = enemyLoc.getY();
                this.fire(arena, x, y);
                 this.fire(arena, x, y);
                  this.fire(arena, x, y);
    }
      if (0< nearby.size()) {
          //boat shouldnt move East, because nearby ship is greater than 0
      }
      else{this.move(arena, Direction.EAST);
          
      }
}
}
}