package mate.rajnai.vendingmachine;

import java.util.List;

import mate.rajnai.vendingmachine.inventory.Inventory;

public interface VendingMachine {

	int getInsertedMoneyOfCurrentPurchase();

	void insertCoin(Coin coin);

	Purchase buyProductAndReturnChangesIfAny(Product product);

	List<Coin> takeRefund();

	InventoriesOfVendingMachine reset(Inventory<Product> productInventory, Inventory<Coin> coinInventory);

}
