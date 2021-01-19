package mate.rajnai.vendingmachine;

import mate.rajnai.vendingmachine.inventory.Inventory;

public class InventoriesOfVendingMachine {
	
	private Inventory<Product> productInventory;
	private Inventory<Coin> coinInventory;
	
	InventoriesOfVendingMachine(Inventory<Product> productInventory, Inventory<Coin> coinInventory) {
		this.productInventory = productInventory;
		this.coinInventory = coinInventory;
	}
	
	public Inventory<Product> getProductInventory() {
		return this.productInventory;
	}
	
	public Inventory<Coin> getCoinInventory() {
		return this.coinInventory;
	}
	

}
