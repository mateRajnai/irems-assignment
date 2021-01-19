package mate.rajnai.vendingmachine;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mate.rajnai.vendingmachine.inventory.CoinInventorySupplier;
import mate.rajnai.vendingmachine.inventory.Inventory;
import mate.rajnai.vendingmachine.inventory.ProductInventorySupplier;
import mate.rajnai.vendingmachine.inventory.TestHelperProductInventorySupplier;

class VendingMachineImplTest {
	
	private VendingMachine vendingMachine;
	
	@BeforeEach
	void init() {
		vendingMachine = new VendingMachineImpl(new TestHelperProductInventorySupplier(), new CoinInventorySupplier());
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
	void canBuyProductAndNoChangeHasToBeReturned() {
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
	void canBuyProductAndOneCoinIsReturned() {
		Product product = Product.COKE;
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.QUARTER);
		Purchase purchase = vendingMachine.buyProductAndReturnChangesIfAny(product);
		List<Coin> expectedChange = new ArrayList<Coin>(Arrays.asList(Coin.DIME));
		assertEquals(product, purchase.getProduct());
		assertEquals(expectedChange, purchase.getChange());
	}
	
	@Test
	void canBuyProductAndTwoCoinsAreReturned() {
		Product product = Product.COKE;
		vendingMachine.insertCoin(Coin.PENNY);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.QUARTER);
		Purchase purchase = vendingMachine.buyProductAndReturnChangesIfAny(product);
		List<Coin> expectedChange = new ArrayList<Coin>(Arrays.asList(Coin.DIME, Coin.PENNY));
		assertEquals(product, purchase.getProduct());
		assertEquals(expectedChange, purchase.getChange());
	}
	
	@Test
	void canNotBuyProductBecauseNotEnoughChange_throwsVendingMachineHasNotEnoughChangeException() {
		Product product = Product.COKE;
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.DIME);
		assertThrows(VendingMachineHasNotEnoughChangeException.class, 
				() -> vendingMachine.buyProductAndReturnChangesIfAny(product));
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
	
	@Test
	void afterResetInsertedMoneyOfCurrentPurchaseIsZero() {
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		Product product = Product.COKE;
		vendingMachine.buyProductAndReturnChangesIfAny(product);
		vendingMachine.reset(new Inventory<Product>(), new Inventory<Coin>());
		int insertedMoney = vendingMachine.getInsertedMoneyOfCurrentPurchase();
		assertEquals(0, insertedMoney);
	}
	
	@Test
	void afterResetAvailableCoinsAreReturned() {
		vendingMachine.insertCoin(Coin.PENNY);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		Product product = Product.COKE;
		vendingMachine.buyProductAndReturnChangesIfAny(product);
		InventoriesOfVendingMachine productAndCoinInventories = vendingMachine.reset(new Inventory<Product>(), new Inventory<Coin>());
		List<Coin> expectedCoins = new ArrayList<>(Arrays.asList(Coin.QUARTER));
		assertEquals(expectedCoins, productAndCoinInventories.getCoinInventory().getItems());
	}
	
	@Test
	void afterResetMachineHasNoCoins() {
		vendingMachine.insertCoin(Coin.PENNY);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		Product product = Product.COKE;
		vendingMachine.buyProductAndReturnChangesIfAny(product);
		vendingMachine.reset(new Inventory<Product>(), new Inventory<Coin>());
		InventoriesOfVendingMachine productAndCoinInventories = vendingMachine.reset(new Inventory<Product>(), new Inventory<Coin>());
		List<Coin> expectedCoins = new ArrayList<>();
		assertEquals(expectedCoins, productAndCoinInventories.getCoinInventory().getItems());
	}
	
	@Test
	void afterResetAvailableProductsAreReturned() {
		vendingMachine.insertCoin(Coin.PENNY);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.QUARTER);
		Product product = Product.COKE;
		vendingMachine.buyProductAndReturnChangesIfAny(product);
		InventoriesOfVendingMachine productAndCoinInventories = vendingMachine.reset(new Inventory<Product>(), new Inventory<Coin>());
		List<Product> expectedProucts = new ArrayList<>(Arrays.asList(Product.SODA));
		assertEquals(expectedProucts, productAndCoinInventories.getProductInventory().getItems());
	}
	

}
