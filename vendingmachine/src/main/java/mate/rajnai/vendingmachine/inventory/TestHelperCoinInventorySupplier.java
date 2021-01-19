package mate.rajnai.vendingmachine.inventory;

import mate.rajnai.vendingmachine.Coin;

public class TestHelperCoinInventorySupplier extends CoinInventorySupplier {
	
	// This class is for unit testing. No coin must be added
	@Override
	public void fillUp(Inventory<Coin> inventory) {
		
	}

}
