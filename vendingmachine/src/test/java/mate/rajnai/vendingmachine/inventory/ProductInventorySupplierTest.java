package mate.rajnai.vendingmachine.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mate.rajnai.vendingmachine.Product;

class ProductInventorySupplierTest {
	

	@Test
	void fillUp() {
		InventorySupplier<Product> supplier = new ProductInventorySupplier();
		Inventory<Product> inventory = new Inventory<Product>();
		supplier.fillUp(inventory);
		assertNotEquals(0, inventory.getItems().size());
	}

}
