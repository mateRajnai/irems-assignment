package mate.rajnai.vendingmachine;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mate.rajnai.vendingmachine.inventory.CoinInventorySupplier;
import mate.rajnai.vendingmachine.inventory.ProductInventorySupplier;

class VendingMachineImplTest {
	
	private VendingMachine vendingMachine;
	
	@BeforeEach
	void init() {
		vendingMachine = new VendingMachineImpl(new TestProductInventorySupplier(), new CoinInventorySupplier());
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
		Product product = Product.COKE;
		vendingMachine.insertCoin(Coin.QUARTER);
		Purchase purchase = vendingMachine.buyProductAndReturnChangesIfAny(product);
		assertEquals(product, purchase.getProduct());
		assertEquals(0, purchase.getChange().size());
	}
	
	@Test
	void canNotBuyProductThrowsNotEnoughCoinIsInsertedException() {
		Product product = Product.COKE;
		assertThrows(NotEnoughCoinIsInsertedException.class, () -> {
			vendingMachine.buyProductAndReturnChangesIfAny(product);
			});
	}
	
	@Test
	void canNotBuyProductThrowsProductIsOutOfRunException() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		Product product = Product.PEPSI;
		assertThrows(ProductIsOutOfRunException.class, () -> {
			vendingMachine.buyProductAndReturnChangesIfAny(product);
			});
	}
	
	@Test
	void afterBuyingProductInsertedMoneyOfCurrentPurchaseIsZero() {
		vendingMachine.insertCoin(Coin.QUARTER);
		Product product = Product.COKE;
		vendingMachine.buyProductAndReturnChangesIfAny(product);
		int insertedMoney = vendingMachine.getInsertedMoneyOfCurrentPurchase();
		assertEquals(0, insertedMoney);
	}
	
	@Test
	void takeRefund() {
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.QUARTER);
		List<Coin> coins = vendingMachine.takeRefund();
		assertEquals(Coin.DIME, coins.get(0));
		assertEquals(Coin.QUARTER, coins.get(1));
	}
	
	@Test
	void afterBuyingProductTakeRefundReturnsZeroCoins() {
		vendingMachine.insertCoin(Coin.QUARTER);
		Product product = Product.COKE;
		vendingMachine.buyProductAndReturnChangesIfAny(product);
		List<Coin> coins = vendingMachine.takeRefund();
		assertEquals(0, coins.size());
	}

}
