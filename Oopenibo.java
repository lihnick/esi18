package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Oopenibo extends Ship {
    
    public Oopenibo() {
        this.initializeName("Sandy");
        this.initializeOwner("Toyin");
        this.initializeHull(4);
        this.initializeFirepower(2);
        this.initializeSpeed(3);
        this.initializeRange(1);

    }
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena){
        this.move(arena, Direction.EAST);
        Coord location = this.getCoord();
        int x = location.getX();
        int y = location.getY();
        this.fire(arena, x+1, y);
        
        int numMoves = this.getRemainingMoves();

         while(numMoves > 0) {
            // move north, west until there's only one turn left
           this.move(arena, Direction.NORTH);
           this.move(arena, Direction.EAST);
            // update the number of turn remaining
            numMoves = this.getRemainingMoves();
        }
    
        List<Ship> nearby = this.getNearbyShips(arena);
        for (int i = 0; i < nearby.size(); i++) {
        Ship other = nearby.get(i);
        boolean isOnMyTeam = this.isSameTeamAs(other);
        if (isOnMyTeam) {
            System.out.println("This ship is on my team!");
        } 
        else {
            System.out.println("This ship is an enemy.");
            Ship enemy = nearby.get(i);
            Coord enemyLoc = enemy.getCoord();
             int a = enemyLoc.getX();
            int b = enemyLoc.getY();
            this.fire(arena, a,b);
        }

        
        }
        //Determines if ship b is within the range of ship a.
//Ship a: ship to check range from
//Ship b: ship to check for
//Returns: boolean
Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
int randomNumber = arena.getRandom().nextInt(2);
    this.move(arena, possibleMovement[randomNumber]);
    }




}