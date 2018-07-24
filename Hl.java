package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Hl extends Ship {
    
    private LinkedHashMap<Ship, int[][]> potentialThreat;

    public Hl() {
        this.initializeName("Cargo Ship");
        this.initializeOwner("Nick");
        this.initializeHull(2);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(4);
    }
    
    /*
        Goal: save information about each ship where they compose into a single action
        Individual map of all ship's firepower + range + speed in a list
        

        Filter the list with two cases:
        Ships that can be destroyed in a single turn:
        Destroying that ship will clear up potential threat map
        so we can optionally move in to attack if we know the threat will be destroyed


        Ships that cannot be destroyed in a single turn:
        In this case fire on the ship with the highest firepower/hull ratio that are within range
        target higher range as a tie breaker for the ratio
        Then move back into safety or into a group to lower risk of attack
        
    */

    private LinkedHashMap<Ship, int[][]> updateThreat(Arena arena) {
        LinkedHashMap<Ship, int[][]> result = new LinkedHashMap<>();

        List<Ship> allShips = arena.getAllShips();

        for (int shipIdx = 0; shipIdx < allShips.size(); shipIdx++) {
            
            Ship ship = allShips.get(shipIdx);
            int[][] heatmap = new int[arena.getXSize()][arena.getYSize()];
            // friendly ship will have a negative heatmap, foe ship positive
            int friendly = (this.isSameTeamAs(ship)) ? -1 : 1;
            // account for potential dangers from ship's movement then attack combo
            int multiplier = ship.getSpeed(); 
            
            for (int mapX = 0; mapX < heatmap.length; mapX++) {

                for (int mapY = 0; mapY < heatmap[0].length; mapY++) {

                    int xdiff = Math.abs(mapX - ship.getX());
                    int ydiff = Math.abs(mapY - ship.getY());
                    // if (x, y) is within the given ship's range
                    if (xdiff <= ship.getRange() &&
                        ydiff <= ship.getRange() ) {
                        heatmap[mapX][mapY] += firepower * friendly * multiplier;
                    } 
                    // these (x, y) areas are where movement is required inorder to attack a given ship
                    else if (xdiff - ship.getSpeed() <= ship.getRange() &&
                            ydiff - ship.getSpeed() <= ship.getRange() ) {
                        int reducedMultiplier = Math.max(xdiff - ship.getSpeed(), ydiff - ship.getSpeed());
                        if (reducedMultiplier < 0) {
                            // negative only when shooting a ship that's already within range
                            // this cannot happen because it's convered in the first if statement
                            throw new AssertionError("Invalid Multiplier: " + reducedMultiplier);
                        }
                        heatmap[mapX][mapY] += firepower * friendly * (multiplier - reducedMultiplier);
                    }

                }
            }
        }
    }

    private LinkedHashMap<Ship, int[][]> filterNearby(Arena arena) {

    }

    private LinkedHashMap<Ship, int[][]> filterSinkable(Arena arena, boolean sinkable) {

    }

    private LinkedHashMap<Ship, int[][]> filterFriendly(Arena arena, boolean foe) {

    }

    private LinkedHashMap<Ship, int[][]> filterPriority(Arena arena) {

    }

    private int[][] composeMap(Arena arena) {

    }

    private List<Direction> composeMove(Arena arena, LinkedHashMap<Ship, int[][]> data) {

    }

    private List<Coord> composeShots(Arena arena, LinkedHashMap<Ship, int[][]> data) {

    }

    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        Coord location = this.getCoord();
        int x = location.getX();
        int y = location.getY();
        this.fire(arena, x+1, y);
    }
    
}