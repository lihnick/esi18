package esi18; 
import battleship.core.*;
import java.util.List;


public class ZKyledipietropaolo extends Ship {
    
    public ZKyledipietropaolo() {
        this.initializeName("Simple Ship");
        this.initializeOwner("Zoe");
        this.initializeHull(1);
        this.initializeFirepower(1);
        this.initializeSpeed(1);
        this.initializeRange(1);
    }
   
  
   public boolean watchAndWait(){
      
    public boolean target = false; 
        
    List<Ship> targets = this.getNearbyShips(arena);
    
    public Ship enemy = list.get(i);
  
    boolean isOnMyTeam = this.isSameTeamAs(enemy);
    
    for (int index = 0; target = true; index += 1) {
     
    
     Ship shipInfo = targets.get(index);
     
      if (isOnMyTeam){
        
      }
        else {
            if((enemy.getHealth() < this.getFirepower())){
            target = true
              Coord TargetLocation = enemy.getCoord();
         
            }
            else{
                
            }
        }
    
        return target;
    }

   
   public turnAction (boolean target){
       
       if(target){
         
         if (isInRange(this, enemy)) {
         int x = targetLocation.getX();
        int y = targetLocation.getY();
        this.fire(arena, x, y);  
         }
       }
       else{
           
       }
   }
   
       
   }
   public 

    @Override
   protected void doTurn(Arena arena) {
  
        watchAndWait();
        
        
      
        
        
    }
    
}