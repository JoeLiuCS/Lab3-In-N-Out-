
public class foodData {

	private int quantity;
	private int expirationDate;
	
	public foodData(int itemNum, int Date){
		quantity = itemNum;
		expirationDate = Date;
	}
	
	public int getExpirationDate (){
		return expirationDate;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void itemUse(int numUsed){
		quantity = quantity - numUsed;
	}
	
}
