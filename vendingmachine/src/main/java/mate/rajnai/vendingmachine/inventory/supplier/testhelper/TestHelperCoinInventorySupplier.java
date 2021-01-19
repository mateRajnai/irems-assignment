package mate.rajnai.vendingmachine.inventory.supplier.testhelper;

import mate.rajnai.vendingmachine.Coin;
import mate.rajnai.vendingmachine.inventory.Inventory;
import mate.rajnai.vendingmachine.inventory.supplier.CoinInventorySupplier;

public class TestHelperCoinInventorySupplier extends CoinInventorySupplier {
	
	// This class is for unit testing. No coin must be added
	@Override
	public void fillUp(Inventory<Coin> inventory) {
		
	}

}
