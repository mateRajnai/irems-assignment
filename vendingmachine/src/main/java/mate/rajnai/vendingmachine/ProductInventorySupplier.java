package mate.rajnai.vendingmachine;

public class ProductInventorySupplier implements InventorySupplier<Product> {

	@Override
	public void fillUp(Inventory<Product> inventory) {
		inventory.addItem(Product.SODA);
	
	}

}
