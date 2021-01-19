package mate.rajnai.vendingmachine;

import java.util.List;

public interface VendingMachine {

	int getInsertedMoneyOfCurrentPurchase();

	void insertCoin(Coin coin);

	Purchase buyProductAndReturnChangesIfAny(Product product);

	List<Coin> takeRefund();

	Purchase reset();

}
