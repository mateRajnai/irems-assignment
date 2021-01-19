package mate.rajnai.vendingmachine.inventory.supplier;

import mate.rajnai.vendingmachine.Product;
import mate.rajnai.vendingmachine.inventory.Inventory;

public class ProductInventorySupplier implements InventorySupplier<Product> {

	@Override
	public void fillUp(Inventory<Product> inventory) {
		inventory.addItem(Product.SODA);
	
	}

}
