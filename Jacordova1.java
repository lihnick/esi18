package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Jcordova1 extends Ship {
    
    public Jcordova1() {
        this.initializeName("Will of the Thousands");
        this.initializeOwner("Jake");
        this.initializeHull(4);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(2 );
    }
    s
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        this.move(arena, Direction.SOUTH);
        Coord location = this.getCoord();
        int x = location.getX();
        int y = location.getY();
        this.fire(arena, x+1, y);
        
    protected void doTurn(Arena arena) {
    // instruction for ship behavior here
    this.fire(arena, 1, 3);

    // get a list of nearby ships
    List<Ship> targets = this.getNearbyShips(arena);
    // access the list if there are any nearby ships
    if (targets.size() > 0) {
        // get the first ship in the targets list
        Ship ship = targets.get(2);
        Coord location = ship.getCoord();
        int x = location.getX();
        int y = location.getY();
        this.fire(arena, x, y);    
        
    protected void doTurn(Arena arena) {
    // instruction for ship behavior here
    this.fire(arena, 1, 3);

    // get a list of nearby ships
    List<Ship> targets = this.getNearbyShips(arena);

    // loop over all nearby ships
    for (int index = 0; index < targets.size(); index += 1) {
        Ship shipInfo = targets.get(index);
        System.out.println("One nearby ship has " + shipInfo.getHealth() + " HP left.");
    }

    System.out.println("There are " + targets.size() + " number of nearby ships");

    // if there is atleast one nearby ships
    if (targets.size() > 0) {
        // get the first ship in the list
        Ship first = targets.get(0);
        // get the fourth ship in the list
        Ship fourth = targets.get(0);
        
    List<Ship> nearby = this.getNearbyShips(arena);
for (int i = 0; i < nearby.size(); i++) {
    Ship other = nearby.get(i);
    boolean isOnMyTeam = this.isSameTeamAs(other);
    if (isOnMyTeam) {
        System.out.println("This ship is on my team!");
    } else {
        System.out.println("This ship is an enemy.");
        
        Ship first = nearby.get(0);
    Ship second = nearby.get(1);
    if (first.isSameTeamAs(second)) {
        System.out.println("These two ships are conspiring with each other.");
    } else {u7
        System.out.println("These two ships are not friends with each other.");
    }
    
    List<Ship> list = this.getNearbyShips(arena);
Ship enemy = list.get(0);
System.out.println("Enemy Ship: " + enemy.getName());
System.out.println("Firepower: " + enemy.getFirepower());

if (enemy.getSpeed() > this.getSpeed()) {
    System.out.println("The enemy ship will move before my ship.");
} else if (enemy.getSpeed() > this.getSpeed()) {
    System.out.println("The enemy ship will move after my ship.");
} else {
    System.out.println("The enemy ship might move before or after my ship.");
}

int hitsTaken = enemy.getHull() - enemy.getHealth();
System.out.println("Enemy has taken " + hitsTaken + " hits.");

Coord myShipLocation = this.getCoord();
// if current x location plus one is equal to the size of the map
// then we have reached the EAST edge of the map
if (myShipLocation.getX() + 1 == arena.getXSize()) {
    this.move(arena, Direction.WEST);
}
// similar example but uses y location and a variable to store the values
int shipYLocation = myShipLocation.getY();
// if current y location minus one is equal to zero
// then we have reached the NORTH edge of the map
if (shipYLocation - 1 == 0) {
    this.move(arena, Direction.SOUTH);
}

// Get a random integer between 0 (inclusive) and 4 (exclusive)
int i = arena.getRandom().nextInt(4);
// Get a random double between 0.0 and 1
double d = arena.getRandom().nextDouble();
// Get a random object from a list
List<Ship> list = this.getNearbyShips(arena);
int index = arena.getRandom().nextInt(list.size());
Ship r = list.get(index);

protected void doTurn(Arena arena) {
    // your implementation of a ship

    // gets the current location of the ship
    Coord coord = this.getCoord();
    int x = coord.getX();
    int y = coord.getY();

    if (x > 6) {
        this.move(arena, Direction.WEST);
    }
    else if (x < 3) {
        this.move(arena, Direction.EAST);
    }
    else if (y > 3) {
        this.move(arena, Direction.NORTH);
    }
    else if (y < 4) {
        this.move(arena, Direction.SOUTH);
    }
    else {
        // make a list of all the location, and store it in a variable
        Direction[] possibleMovement = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

        // get a random number and store it in a variable
        int randomNumber = arena.getRandom().nextInt(4)

        // get a random movement by using the random number to access one possibleMovement
        this.move(arena, possibleMovement[randomNumber]);
    }

    // ship using this instruction will fire at location (x: 0, y: 0) each turn
    this.fire(arena, 3, 3);
}
        
    }
    
}