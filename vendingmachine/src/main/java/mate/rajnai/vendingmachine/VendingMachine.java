package mate.rajnai.vendingmachine;

public interface VendingMachine {

	int getInsertedMoneyOfCurrentPurchase();

	void insertCoin(Coin coin);

	Purchase buyProductAndReturnChangesIfAny(Product product);

}
