package mate.rajnai.vendingmachine.inventory;

import mate.rajnai.vendingmachine.Product;

public class TestHelperProductInventorySupplier extends ProductInventorySupplier {
	
	@Override
	public void fillUp(Inventory<Product> inventory) {
		inventory.addItem(Product.COKE);
	
	}

}
