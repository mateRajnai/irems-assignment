package mate.rajnai.vendingmachine.inventory;

import mate.rajnai.vendingmachine.Coin;
import mate.rajnai.vendingmachine.Product;

public class InventoriesOfVendingMachine {
	
	private Inventory<Product> productInventory;
	private Inventory<Coin> coinInventory;
	
	public InventoriesOfVendingMachine(Inventory<Product> productInventory, Inventory<Coin> coinInventory) {
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
