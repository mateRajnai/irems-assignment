package mate.rajnai.vendingmachine;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VendingMachineImplTest {
	
	private VendingMachine vendingMachine;
	
	@BeforeEach
	void init() {
		vendingMachine = new VendingMachineImpl(new ProductInventorySupplier(), new CoinInventorySupplier());
	}
	
	@Test
	void insertCoin_Penny() {
		vendingMachine.insertCoin(Coin.PENNY);
		int insertedMoneyOfCurrentPurchase = vendingMachine.getInsertedMoneyOfCurrentPurchase();
		assertEquals(1, insertedMoneyOfCurrentPurchase);
	}
	
	@Test
	void insertFourCoinsInOnePurchase() {
		vendingMachine.insertCoin(Coin.PENNY);
		vendingMachine.insertCoin(Coin.NICKEL);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.QUARTER);
		int insertedMoneyOfCurrentPurchase = vendingMachine.getInsertedMoneyOfCurrentPurchase();
		assertEquals(41, insertedMoneyOfCurrentPurchase);
	}
	
	
	
	@Test
	void canBuyProductAndNoChangesHaveToBeReturned() {
		Product product = Product.SODA;
		Purchase purchase = vendingMachine.buyProductAndReturnChangesIfAny(product);
		assertEquals(product, purchase.getProduct());
		assertEquals(0, purchase.getChange().size());
	}
	


}
