package mate.rajnai.vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {
	
	private Inventory<Coin> inventory;

	@BeforeEach
	void init() {
		inventory = new Inventory<Coin>();
	}
	
	@Test
	void noItemsInInventoryAfterInstantiation() {
		List<Coin> coins = inventory.getItems();
		assertEquals(0, coins.size());
	}
	
	@Test
	void addItemToInventory() {
		inventory.add(Coin.PENNY);
		List<Coin> coins = inventory.getItems();
		assertEquals(1, coins.size());
	}
	

}
