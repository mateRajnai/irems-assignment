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
		inventory.addItem(Coin.PENNY);
		List<Coin> coins = inventory.getItems();
		assertEquals(1, coins.size());
	}
	
	@Test
	void addTwoItemsToInventory() {
		inventory.addItem(Coin.PENNY);
		inventory.addItem(Coin.PENNY);
		List<Coin> coins = inventory.getItems();
		assertEquals(2, coins.size());
	}
	
	@Test
	void removeExistingItemFromInventoryReturnsTrue() {
		inventory.addItem(Coin.PENNY);
		boolean isRemoved = inventory.removeItem(Coin.PENNY);
		List<Coin> coins = inventory.getItems();
		assertEquals(0, coins.size());
		assertEquals(true, isRemoved);
	}
	
	@Test
	void removeNotExistingItemFromInventoryReturnsFalse() {
		boolean isRemoved = inventory.removeItem(Coin.PENNY);
		List<Coin> coins = inventory.getItems();
		assertEquals(0, coins.size());
		assertEquals(false, isRemoved);
	}

	
	
	
}
