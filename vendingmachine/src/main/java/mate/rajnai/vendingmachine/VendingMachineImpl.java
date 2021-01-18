package mate.rajnai.vendingmachine;

import java.util.ArrayList;

import mate.rajnai.vendingmachine.inventory.Inventory;
import mate.rajnai.vendingmachine.inventory.InventorySupplier;

public class VendingMachineImpl implements VendingMachine {
	
	private Inventory<Product> availableProducts = new Inventory<Product>();
	private Inventory<Coin> availableCoins = new Inventory<Coin>();
	private int insertedMoneyOfCurrentPurchase;
	
	VendingMachineImpl(InventorySupplier<Product> productInventorySupplier, InventorySupplier<Coin> coinInventorySupplier) {
		productInventorySupplier.fillUp(this.availableProducts);
		coinInventorySupplier.fillUp(this.availableCoins);
	}

	@Override
	public int getInsertedMoneyOfCurrentPurchase() {
		return insertedMoneyOfCurrentPurchase;
	}

	@Override
	public void insertCoin(Coin coin) {
		insertedMoneyOfCurrentPurchase += coin.getValue();	
	}

	@Override
	public Purchase buyProductAndReturnChangesIfAny(Product product) {
		if(this.availableProducts.removeItem(product)) 
			return new Purchase(product, new ArrayList<Coin>());
		return null;
	}
	
	

}
