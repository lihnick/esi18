package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * DroneShip
 * @author TA
 */
public class GunDroneShip extends Ship {

    private int waitToMove;
    
    public GunDroneShip() {
        this.initializeName("Gun Drone");
        this.initializeOwner("TA");
        this.initializeHull(1);
        this.initializeFirepower(4);
        this.initializeSpeed(1);
        this.initializeRange(2);
        waitToMove = 2;
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        if (waitToMove > 0) {
            waitToMove = waitToMove - 1;
            // do nothing
        }
        else {
            Coord location = this.getCoord();
            this.move(arena, Direction.WEST);

            // Get a list of enemy ships
            List<Ship> targets = this.getNearbyShips(arena);
            // Get the number of shots left on this ship
            int numShots = this.getRemainingShots();

            while (targets.size() > 0 && numShots > 0) {
                Ship enemy = targets.get(0);

                while(enemy.getHealth() > 0 && numShots > 0) {
                    Coord enemyLoc = enemy.getCoord();
                    int x = enemyLoc.getX();
                    int y = enemyLoc.getY();
                    this.fire(arena, x, y);
                    numShots = this.getRemainingShots();
                }

                // if we get here, that means a ship has been sunk
                // call this function again to update the list to not shoot at a sunk ship
                targets = this.getNearbyShips(arena);
            }
        }
    }
    
}