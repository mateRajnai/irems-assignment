package mate.rajnai.vendingmachine.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mate.rajnai.vendingmachine.Coin;
import mate.rajnai.vendingmachine.Product;

class CoinInventorySupplierTest {

	@Test
	void fillUp() {
		InventorySupplier<Coin> supplier = new CoinInventorySupplier();
		Inventory<Coin> inventory = new Inventory<Coin>();
		supplier.fillUp(inventory);
		assertNotEquals(0, inventory.getItems().size());
	}

}
