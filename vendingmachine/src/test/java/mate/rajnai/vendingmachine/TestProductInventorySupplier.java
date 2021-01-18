package mate.rajnai.vendingmachine;

import mate.rajnai.vendingmachine.inventory.Inventory;
import mate.rajnai.vendingmachine.inventory.ProductInventorySupplier;

public class TestProductInventorySupplier extends ProductInventorySupplier {
	
	@Override
	public void fillUp(Inventory<Product> inventory) {
		inventory.addItem(Product.COKE);
	
	}

}
