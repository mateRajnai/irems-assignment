package mate.rajnai.vendingmachine;

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
	
	

}
