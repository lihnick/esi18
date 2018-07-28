package esi18; 
import battleship.core.*;
import java.util.List;

/*
 * SimpleShip
 * @author Your Name
 */
public class Evazquez1 extends Ship {
    
    public Evazquez1() {
        this.initializeName("SS.Spongebob");
        this.initializeOwner("Evelyn");
        this.initializeHull(3);
        this.initializeFirepower(4);
        this.initializeSpeed(2);
        this.initializeRange(1);
    }
    //boats start on the left side(west) boats will be moving west to east
    //arena size 15 x 8 
    /*
     * Determines what actions the ship will take on a given turn
     * @param arena (Arena) the battlefield for the match
     * @return void
     */
    @Override
    protected void doTurn(Arena arena) {
        this.move(arena, Direction.EAST);
        
        List<Ship> nearby = this.getNearbyShips(arena);
        
        for(int i = 0; i<nearby.size(); i++){
            if (this.isSameTeamAs(nearby.get(i))){

        }else{   
            Coord location = this.getCoord();
            int x = location.getX();
            int y = location.getY();
            this.fire(arena, x+1, y);
        }
      
    }
     for (int index = 0; index < nearby.size(); index += 1) {
        Ship shipInfo = nearby.get(index);
        System.out.println("One nearby ship has " + shipInfo.getHealth() + " HP left.");
    }
    
    }

   Coord location = ship.getCoord();
        int w = location.getX();
        int z = location.getY();
        if(ship (arena,x + 1, y )){
            this.fire(arena, x + 1, y);
        } else{}
}