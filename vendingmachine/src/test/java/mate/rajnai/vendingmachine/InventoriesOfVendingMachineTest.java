package mate.rajnai.vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mate.rajnai.vendingmachine.inventory.InventoriesOfVendingMachine;
import mate.rajnai.vendingmachine.inventory.Inventory;

class InventoriesOfVendingMachineTest {
	
	private InventoriesOfVendingMachine inventoriesOfVendingMachine;
	private Inventory<Product> productInventory;
	private Inventory<Coin> coinInventory;
	
	@BeforeEach
	void init() {
		productInventory = new Inventory<>();
		coinInventory = new Inventory<>();
		inventoriesOfVendingMachine = new InventoriesOfVendingMachine(productInventory, coinInventory);
	}

	@Test
	void getProductInventory() {
		assertEquals(productInventory, inventoriesOfVendingMachine.getProductInventory());
	}

	@Test
	void testGetCoinInventory() {
		assertEquals(coinInventory, inventoriesOfVendingMachine.getCoinInventory());
	}

}
