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

    @Override
    public boolean equals(Ship other) {
        if (this instanceof other) {
            return true;
        }
        return false;
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

            // reduce the number of heatmap that needs to be traverse
            int minX = Math.max(0, ship.getX() - (ship.getRange() + ship.getSpeed()));
            int minY = Math.max(0, ship.getY() - (ship.getRange() + ship.getSpeed()));
            int maxX = Math.min(arena.getX(), ship.getX() + ship.getRange() + ship.getSpeed());
            int maxY = Math.min(arena.getY(), ship.getY() + ship.getRange() + ship.getSpeed());
            
            for (int mapX = minX; mapX < maxX; mapX++) {

                for (int mapY = minY; mapY < maxY; mapY++) {

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
            result.put(ship, heatmap);
        }
        return result;
    }

    private LinkedHashMap<Ship, int[][]> filterNearby(LinkedHashMap<Ship, int[][]> data) {
        LinkedHashMap<Ship, int[][]> result = new LinkedHashMap<>();

        result = data.entrySet().stream().filter((ship) -> {
            if (Math.abs(this.getX() - ship.getX()) <= this.getRange() && 
                Math.abs(this.getY() - ship.getY()) <= this.getRange() ) {
                return true;
            }
            return false;
        }).collect(Collectors.toMap(ship -> ship.getKey(), ship -> ship.getValue()));

        return result;
    }

    private LinkedHashMap<Ship, int[][]> filterSinkable(LinkedHashMap<Ship, int[][]> data, boolean sinkable) {
        LinkedHashMap<Ship, int[][]> result = new LinkedHashMap<>();

        result = data.entrySet().stream().filter((ship) -> {
            if (ship.getHealth() <= this.getRemainingShots()) {
                return (true == sinkable);
            }
            return (false == sinkable);
        }).collect(Collectors.toMap(ship -> ship.getKey(), ship -> ship.getValue()));

        return result;
    }

    private LinkedHashMap<Ship, int[][]> filterFriendly(LinkedHashMap<Ship, int[][]> data, boolean foe) {
        LinkedHashMap<Ship, int[][]> result = new LinkedHashMap<>();

        result = data.entrySet().stream().filter((ship) -> {
            // filter out your own ship in all cases
            if (!this.equals(ship)) {
                if (!this.isSameTeamAs(ship) == foe) {
                    return true;
                }
                return false;
            }
            return false;
        }).collect(Collectors.toMap(ship -> ship.getKey(), ship -> ship.getValue()));

        return result;
    }

    private LinkedHashMap<Ship, int[][]> filterPriority(Arena arena) {
        LinkedHashMap<Ship, int[][]> result = new LinkedHashMap<>();

        result = data.entrySet().stream().sorted((lhs, rhs) -> {
            // sort by highest firepower/health ratio first
            double rhsRatio = (double) rhs.getFirepower()/rhs.getHealth();
            double lhsRatio = (double) lhs.getFirepower()/lhs.getHealth();

            if ((int)rhsRatio != (int)lhsRatio) {
                return (int)(rhsRatio - lhsRatio);
            }
            // sort by the highest range first
            else if (rhs.getRange() != lhs.getRange()) {
                return rhs.getRange() - lhs.getRange();
            }
            // sort by lowest health first
            else if (lhs.getHealth() != rhs.getHealth()) {
                return lhs.getHealth() - rhs.getHealth();
            }
            else {
                return lhs.getName().compareTo(rhs.getName());
            }
        }).collect(Collectors.toMap(ship -> ship.getKey(), ship -> ship.getValue()));

        return result;
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