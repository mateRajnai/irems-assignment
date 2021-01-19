package mate.rajnai.vendingmachine.inventory;

import mate.rajnai.vendingmachine.Product;
import mate.rajnai.vendingmachine.inventory.supplier.ProductInventorySupplier;

public class TestHelperProductInventorySupplier extends ProductInventorySupplier {
	
	// This class is for unit testing. One Coke and three Soda must be added to the inventory
	@Override
	public void fillUp(Inventory<Product> inventory) {
		inventory.addItem(Product.COKE);
		inventory.addItem(Product.SODA);
		inventory.addItem(Product.SODA);
		inventory.addItem(Product.SODA);
	}

}
