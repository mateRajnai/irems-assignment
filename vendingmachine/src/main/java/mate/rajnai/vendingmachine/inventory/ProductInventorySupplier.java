package mate.rajnai.vendingmachine.inventory;

import mate.rajnai.vendingmachine.Product;

public class ProductInventorySupplier implements InventorySupplier<Product> {

	@Override
	public void fillUp(Inventory<Product> inventory) {
		inventory.addItem(Product.SODA);
	
	}

}
