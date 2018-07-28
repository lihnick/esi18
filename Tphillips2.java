package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Tphillips2 extends Ship {
    
    public Tphillips2() {
        this.initializeName("tati");
        this.initializeOwner("TATI");
        this.initializeHull(6);
        this.initializeFirepower(2);
        this.initializeSpeed(1);
        this.initializeRange(1);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        
        Coord coord = this.getCoord();
        int x = coord.getX();
        int y = coord.getY();
        List<Ship> list = this.getNearbyShips(arena);
        Ship enemy = list.get(0);
        System.out.println("Enemy Ship: " + enemy.getName());
        System.out.println("Firepower: " + enemy.getFirepower());

                    System.out.println("My ship is at (" + x + ", " + y + ").");
                    int numMoves = this.getRemainingMoves();
                if (enemy.getSpeed() > this.getSpeed()) {
                    System.out.println("The enemy ship will move before my ship.");
                if (x < 5){
                    while(numMoves > 1) {
                        this.move(arena, Direction.WEST);
                        numMoves = this.getRemainingMoves();
                    }
                        this.move(arena, Direction.EAST);
                    }
                if (x> 5){
                    while(numMoves > 1) {
                        this.move(arena, Direction.NORTH);
                        this.move(arena, Direction.WEST);
                        numMoves = this.getRemainingMoves();
                    }
                        this.move(arena, Direction.EAST);
                    }  
                else if (enemy.getSpeed() < this.getSpeed()) {
                                System.out.println("The enemy ship will move after my ship.");
                        while(numMoves > 1) {
                                this.move(arena, Direction.NORTH);
                                this.move(arena, Direction.WEST);
                                numMoves = this.getRemainingMoves();
                    }
                                this.move(arena, Direction.EAST);
                    } 
                else {
                        System.out.println("The enemy ship might move before or after my ship.");
                    }
                }
    }
}
        
    
