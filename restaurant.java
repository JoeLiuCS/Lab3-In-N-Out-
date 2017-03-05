
public class restaurant {
       private foodStore s;
       private int missCustomer;
       private int [] countOrder;
       
       public restaurant(){
    	   s = new foodStore();
    	   missCustomer = 0;
    	   countOrder = new int[6];
       }
       
       public void marchWork(){
    	   for(int i = 301; i<332;i++){
    		   missCustomer = 0;
    		   if(i != 301 && i%3 == 1){
    			   s.shipmentWork(i);
    		   }
    		
    		   workingHour();
    		   s.checkExpiration(i);
    		   s.printWaste();
    		   printCustomerMiss();
    		   printOrder();
    		   
    	   }
       }
       
       public void workingHour(){
    	   for(int i = 0 ;i<10;i++){
    		   int customer = customerComing();
    		   if(customer<=50){
    			   order(customer);
    		   }
    		   else{
    			   missCustomer =+ (customer - 50);
    			   order(50);
    		   }
    	   }
       }
       
       public int customerComing(){
    	   return (int) (Math.random()*(100) + 1);
       }
       
       public void order(int customerNum){
    	   for(int i=0;i<customerNum;i++){
    		   int orderNum = (int) (Math.random()*(6)+1);
    		   boolean make = s.makeThisOrder(orderNum);
    		   if(make == false){
    			   missCustomer++;
    		   }
    		   else{
        		   countOrder[orderNum-1] =+1;
    		   }
    	   }
       }
       
       public int getMissCustomer (){
    	   return missCustomer;
       }
       
       public void printCustomerMiss(){
    	   System.out.println("Missing customer : " + missCustomer );
       }
       
       public void printOrder(){
    	   for(int i =0;i<6;i++){
    		   System.out.println("order# " + (i+1) + " : " + countOrder[i]);
    	   }
       }
       
}
