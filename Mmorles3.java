package esi18; 
import battleship.core.*;
import java.util.List;
import java.util.ArrayList;

/*
 * SimpleShip
 * @author Your Name
 */
public class Mmorales3 extends Ship {
    
    int num_moves;
    Coord location;
    int my_x;
    int my_y;
    
    public Mmorales3() {
        this.initializeName("My Ship");
        this.initializeOwner("Mario Morales");
        this.initializeHull(3);
        this.initializeFirepower(3);
        this.initializeSpeed(1);
        this.initializeRange(3);
    }
    
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    
    @Override
    protected void doTurn(Arena arena) {
        
        ArrayList<Coord> enemy_pos = new ArrayList<Coord>();
        num_moves = this.getRemainingMoves();
        List<Ship> ships = this.getNearbyShips(arena);
        location = this.getCoord();
        my_x = location.getX();
        my_y = location.getY();
        
        for (Ship s : ships)
            if (!this.isSameTeamAs(s) && !s.isSunk()){
                while(this.getRemainingShots() > 0 && !s.isSunk()) 
                    this.fire(arena, s.getCoord().getX(), s.getCoord().getY()); 
            }
                //enemy_pos.add(s.getCoord());
        if (enemy_pos.size() == 0)
            while (this.getRemainingMoves() > 0) 
                this.move(arena, Direction.EAST);
        // else{
        //     for (Coord c : enemy_pos) {
        //         System.out.println("Enemy ship at: (" + c.getX() + ", " + c.getY()+ ")!");
        //         this.fire(arena, c.getX(), c.getY());
                
        //     }
        // }
        
        enemy_pos.clear();
    }
    
}