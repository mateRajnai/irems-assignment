package mate.rajnai.vendingmachine;

import java.util.ArrayList;
import java.util.List;

import mate.rajnai.vendingmachine.inventory.Inventory;
import mate.rajnai.vendingmachine.inventory.InventorySupplier;

public class VendingMachineImpl implements VendingMachine {
	
	private Inventory<Product> availableProducts = new Inventory<Product>();
	private Inventory<Coin> availableCoins = new Inventory<Coin>();
	private Inventory<Coin> insertedCoinsOfCurrentPurchase = new Inventory<Coin>();
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
		this.insertedMoneyOfCurrentPurchase += coin.getValue();
		this.insertedCoinsOfCurrentPurchase.addItem(coin);
	}

	@Override
	public Purchase buyProductAndReturnChangesIfAny(Product product) {
		if (this.insertedMoneyOfCurrentPurchase < product.getPrice()) {
			throw new NotEnoughCoinIsInsertedException("Inserted coin is less than product's price!");
		}
		if(this.availableProducts.removeItem(product)) {
			this.insertedMoneyOfCurrentPurchase = 0;
			return new Purchase(product, new ArrayList<Coin>());
		}
		else
			throw new ProductIsOutOfRunException(product.getName() + " is out of run!");
	}

	@Override
	public List<Coin> takeRefund() {
		return this.insertedCoinsOfCurrentPurchase.clearItems();
	}
	
	

}
