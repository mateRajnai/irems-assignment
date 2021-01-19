package mate.rajnai.vendingmachine.inventory.supplier;

import mate.rajnai.vendingmachine.Coin;
import mate.rajnai.vendingmachine.inventory.Inventory;

public class CoinInventorySupplier implements InventorySupplier<Coin> {

	@Override
	public void fillUp(Inventory<Coin> inventory) {
		inventory.addItem(Coin.DIME);
	}

}
