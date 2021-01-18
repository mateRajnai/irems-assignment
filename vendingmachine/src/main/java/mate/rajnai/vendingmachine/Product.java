package mate.rajnai.vendingmachine;

public enum Product {
	COKE("Coke", 25),
	PEPSI("Pepsi", 35),
	SODA("Soda", 45);
	
	private final String name;
	private final int price;
	
	Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName( ) {
		return this.name;
	}
	
	public int getPrice( ) {
		return this.price;
	}
}
