import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;


public class foodStore {
    private Queue<foodData> [] food;  // 0:bun(5)  1:patty(4)  2:lettuce(3) 3:onion(5)  4:cheese(2) 5: tomato(3)
    private final int [] expirationDate = {5,4,3,5,2,3}; 
    private final int [][] menu = new int [][]{
    	{1,1,1,1,0,1},
    	{1,1,1,1,1,1},
    	{0,0,2,1,0,1},
    	{1,1,1,0,1,1},
    	{1,1,1,0,1,1},
    	{1,1,1,1,0,0}
    };
    private final int startDate = 301;
    private int [] countWaste;
    
    
    @SuppressWarnings("unchecked")
	public foodStore(){
    	  countWaste = new int[6];
    	  food = new Queue[6];
    	  for(int i =0; i<6 ;i++){
    		  food[i] = new LinkedList<>();
    	  }
    	  shipmentWork(startDate);
     }
    
    public void shipmentWork (int shipDate){
		for(int i = 0 ;i <6; i++){
			int randomQuantity = (int) (Math.random()*(1000 - 700 + 1)) + 700;
			foodData temp = new foodData(randomQuantity,shipDate);
			food[i].add(temp);
		}
	}
    
    public boolean makeThisOrder(int orderNum){
    	boolean result;
    	if(canMakeThisOrder(orderNum)){
    		
    		for(int i =0;i<menu[orderNum].length;i++){
    			if(menu[orderNum][i] == 1){
    				replaceWithNum(i,1);
    				removeTopIsEmpty();
    			}
    			if(menu[orderNum][i] == 2){
    				if(food[i].peek().getQuantity() == 1){
    					replaceWithNum(i,1);
    					removeTopIsEmpty();
    					replaceWithNum(i,1);
    				}
    				else{
    					replaceWithNum(i,2);
    					removeTopIsEmpty();
    				}
    			}
    		}
    		
    		result = true;
    	}
    	else{
    		result = false;
    	}
    	return result;
    }
    
    public void replaceWithNum(int foodPosition,int numRemove){
    	ListIterator<foodData> s = (ListIterator<foodData>) food[foodPosition].iterator();
		foodData temp = s.next();
		foodData replace = new foodData(temp.getQuantity()-numRemove,temp.getExpirationDate());
		s.set(replace);
    }
    
    public boolean canMakeThisOrder(int orderNum){
    	removeTopIsEmpty();
		for(int j =0; j<menu[orderNum].length;j++){
			if(menu[orderNum][j] == 1 && food[j] == null){
				return false;
			}
			if (j==2 && orderNum ==2){
				if(food[j].peek().getQuantity() <= 1 && food[j].size() <1){
					return false;
				}
			}
		}
    	return true;
    }
    
    public void removeTopIsEmpty(){
	   for(int i = 0; i<6 ;i++){
		   while(!food[i].isEmpty()){
			   if(food[i].peek().getQuantity() == 0){
			       food[i].poll();
			   }
			   else{
			       break;
			   }
		   }
	   }
    }
    
    public void checkExpiration(int today){
		for(int i =0;i<6 ;i++){
		   if(food[i].peek().getExpirationDate() + expirationDate[i] <= today){
			   countWaste[i] =+ food[i].poll().getQuantity();
		   }
		}
	}
	
	public void printWaste(){
		String [] names = {"bun","patty","lettuce","onion","cheese","tomato"};
		for(int i =0; i<6 ;i++){
			System.out.println("Waste" + names[i] + " : " + countWaste[i]);
		}
	}
}
