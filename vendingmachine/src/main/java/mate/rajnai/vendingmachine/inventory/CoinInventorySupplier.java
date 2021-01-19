package mate.rajnai.vendingmachine.inventory;

import mate.rajnai.vendingmachine.Coin;

public class CoinInventorySupplier implements InventorySupplier<Coin> {

	@Override
	public void fillUp(Inventory<Coin> inventory) {
		inventory.addItem(Coin.DIME);
	}

}
