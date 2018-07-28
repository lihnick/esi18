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

    private LinkedHashMap<Ship, int[][]> filterNearby(LinkedHashMap<Ship, int[][]> data, boolean needMove) {
        LinkedHashMap<Ship, int[][]> result = new LinkedHashMap<>();
        int movement = 0;
        if (needMove) {
            // get remaining move is used to allow this method to be called multiple times in one turn
            movement = this.getRemainingMoves();
        }

        result = data.entrySet().stream().filter((ship) -> {
            if (Math.abs(this.getX() - ship.getX()) - movement <= this.getRange() && 
                Math.abs(this.getY() - ship.getY()) - movement <= this.getRange() ) {
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

    private LinkedHashMap<Ship, int[][]> sortPriority(LinkedHashMap<Ship, int[][]> data) {
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

    private int[][] composeMap(Arena arena, LinkedHashMap<Ship, int[][]> data) {
        int[][] heatmap = new int[arena.getXSize()][arena.getYSize()];
        
        for (Map.Entry<Ship, int[][]> entry : data.entrySet()) {
            for (int x = 0; x < arena.getXSize(); x++) {
                for (int y = 0; y < arena.getYSize(); y++) {
                    heatmap[x][y] += entry.getValue()[x][y];
                }
            }
        }
        return heatmap;
    }

    // function only works for ship speed of 1
    private Direction deriveMovement(Coord start, Coord goal) {
        Direction result;
        if (start.getX() < goal.getX()) {
            result = Direction.EAST;
        }
        if (start.getX() > goal.getX()) {
            result = Direction.WEST;
        }
        if (start.getY() > goal.getY()) {
            result = Direction.SOUTH;
        }
        if (start.getY() < goal.getY()) {
            result = Direction.NORTH;
        }
    }

    private Coord offsetCoord(Coord start, Direction dir) {
        Coord result;
        if (dir == Direction.NORTH) {
            result = new Coord(start.getX(), start.getY()-1);
        }
        else if (dir == Direction.SOUTH) {
            result = new Coord(start.getX(), start.getY()+1);
        }
        else if (dir == Direction.WEST) {
            result = new Coord(start.getX()-1, start.getY());
        }
        else if (dir == Direction.EAST) {
            result = new Coord(start.getX()+1, start.getY());
        }
        else {
            result = start;
        }
        return result;
    }

    /* Offense Cases: (no nearby, nearby within movement, sinkables)
        * no nearby ships within own movement
            * Move closer (can be risky)
        * nearby ships within own movement
            * for sinkable ships
                if (low threat)
                    * move closer / attack
                else
                    * move closer to team if low hp / stay put
        
        Defense Cases: (non sinkeable nearby ships)
            * fire using priority filter
            * move closer to team
    */

    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        LinkedHashMap<Ship, int[][]> data = updateThreat(arena);
        LinkedHashMap<Ship, int[][]> nearby = filterNearby(data, true);
        LinkedHashMap<Ship, int[][]> moveNearby = filterNearby(data, false);

        LinkedHashMap<Ship, int[][]> nearbyEnemy = filterFriendly(nearby, true);
        LinkedHashMap<Ship, int[][]> moveNearbyEnemy = filterFriendly(moveNearby, true);

        // on offense if there are no nearby enemy that are shootable without movement
        boolean offense = nearbyEnemy.size() == 0;

        if (offense) {
            if (moveNearbyEnemy.size() == 0) {
                // move closer, need a easy wait to find a direction
                // This step will take a bit of a risk
                LinkedHashMap<Ship, int[][]> search = filterFriendly(data, true);
                LinkedHashMap<Ship, int[][]> priority = sortPriority(search);

                Ship first = priority.firstEntry().getKey();
                Direction moveDir = deriveMovement(this.getCoord(), first.getCoord());
                this.move(arena, moveDir);

                List<Ship> nearby = this.getNearbyShips(arena);
                for (Ship s : nearby) {
                    if (!this.isSameTeamAs(s)) {
                        Coord loc = s.getCoord();
                        this.fire(arena, loc.getX(), loc.getY());
                    }
                }

            } else {
                // find sinkables within movement
                LinkedHashMap<Ship, int[][]> priority = sortPriority(moveNearbyEnemy);
                int[][] currentThreats = composeMap(priority);

                LinkedHashMap<Ship, Integer> fireSolution = new LinkedHashMap<>();
                for (Map.Entry<Ship, int[][]> entry : priority.entrySet()) {
                    Ship enemy = entry.getKey();
                    int[][] negatedThreat = entry.getValue(); // once destroyed

                    Direction moveDir = deriveMovement(this.getCoord(), enemy.getCoord());
                    Coord moveTo = offsetCoord(this.getCoord(), moveDir);

                    int threatAfterSink = currentThreats[moveTo.getX()][moveTo.getY()] - negatedThreat[moveTo.getX()][moveTo.getY()];

                    fireSolution.put(enemy, threatAfterSink);
                }

                fireSolution.entrySet().stream()
                .sorted(Map.Entry.comparingByValue().reversed())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
                // move to enemy first

                for (Map.Entry<Ship, Integer> entry : fireSolution.entrySet()) {
                    Ship enemy = entry.getKey();
                    Coord enemyLoc = enemy.getCoord();
                    if (this.getRemainingMoves() > 0) {
                        Direction moveDir = deriveMovement(this.getCoord(), enemyLoc);
                        this.move(arena, moveDir);
                    }
                    if (this.getRemainingShots() == 0) {
                        break;
                    }
                    else {
                        while(this.getRemainingShots() > 0 && enemy.getHealth() > 0) {
                            this.fire(arena, enemyLoc.getX(), enemyLoc.getY());
                        }
                    }
                }
            }
        } else {
            // find sinkables, and safety after shooting
            LinkedHashMap<Ship, int[][]> destroy = filterSinkable(nearbyEnemy, true);
            if (destroy.size() == 0) {
                // no sinkables, focus on shooting high firepower/hull ratio ships
                LinkedHashMap<Ship, int[][]> priority = sortPriority(nearbyEnemy);
                int[][] currentThreat = composeMap(priority);
            }
            else {
                // shoot sinkables, move back
            }
        }


    }
    
}