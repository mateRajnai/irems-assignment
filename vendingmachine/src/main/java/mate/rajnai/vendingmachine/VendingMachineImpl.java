package mate.rajnai.vendingmachine;

import java.util.ArrayList;

public class VendingMachineImpl implements VendingMachine {
	
	private Inventory<Coin> availableCoins;
	private int insertedMoneyOfCurrentPurchase;

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
		return new Purchase(product, new ArrayList<Coin>());
	}
	
	

}
