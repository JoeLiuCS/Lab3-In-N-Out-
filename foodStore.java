import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;


public class foodStore {
    private Queue<foodData> [] food;  // 0:bun(5)  1:patty(4)  2:lettuce(3) 3:onion(5)  4:cheese(2) 5: tomato(3)
    private final int [] expirationDate = {5,4,3,5,2,3}; 
    private ArrayList<ArrayList<Integer>> menu;
    private final int startDate = 301;
    private int [] countWaste;
    
    
    @SuppressWarnings("unchecked")
	public foodStore(){
    	  countWaste = new int[6];  //create waste counter
    	  food = new Queue[6];      //create food container
    	  for(int i =0; i<6 ;i++){
    		  food[i] = new LinkedList<>();
    	  }
    	  shipmentWork(startDate);    //ship by first day
    	  
    	  menu = new ArrayList<ArrayList<Integer>>();
    	  ArrayList<Integer> comb1 = new ArrayList<>(Arrays.asList(1,1,1,1,0,1));
    	  menu.add(comb1);
    	  ArrayList<Integer> comb2 = new ArrayList<>(Arrays.asList(1,1,1,1,1,1));
    	  menu.add(comb2);
    	  ArrayList<Integer> comb3 = new ArrayList<>(Arrays.asList(0,0,2,1,0,1));
    	  menu.add(comb3);
    	  ArrayList<Integer> comb4 = new ArrayList<>(Arrays.asList(1,1,1,0,0,1));
    	  menu.add(comb4);
    	  ArrayList<Integer> comb5 = new ArrayList<>(Arrays.asList(1,1,1,0,1,1));
    	  menu.add(comb5);
    	  ArrayList<Integer> comb6 = new ArrayList<>(Arrays.asList(1,1,1,1,0,0));
    	  menu.add(comb6);
    	  
     }
    
    public void shipmentWork (int shipDate){
		for(int i = 0 ;i <food.length; i++){
			int randomQuantity = (int) (Math.random()*(1000 - 700 + 1)) + 700;
			foodData temp = new foodData(randomQuantity,shipDate);
			food[i].add(temp);
		}
	}
    
    public boolean makeThisOrder(int orderNum){
    	boolean result;
    	if(canMakeThisOrder(orderNum)){
	    	for(int i =0;i<menu.get(orderNum-1).size();i++){
	    		int howManyNeeds = menu.get(orderNum-1).get(i);
	    		if( howManyNeeds == 1){
	    			replaceWithNum(i,1);
	    			removeTopIsEmpty();
	    		}
	    		if( howManyNeeds == 2){
	    			if(food[i].peek().getQuantity() ==1){
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
		s.next().itemUse(numRemove);
    }
    
    public boolean canMakeThisOrder(int orderNum){  	
    	removeTopIsEmpty();
    	for(int i=0;i<menu.get(orderNum-1).size();i++){
    		int needThis = menu.get(orderNum-1).get(i);
    		if( needThis==1 ){
    			if(food[i].isEmpty()){
    				return false;
    			}
    		}
    		if (needThis == 2){
    			if(food[i].isEmpty() || (food[i].peek().getQuantity() <=1 && food[i].size()==1)){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public void removeTopIsEmpty(){
	   for(int i = 0; i<food.length ;i++){
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
