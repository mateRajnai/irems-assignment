package mate.rajnai.vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mate.rajnai.vendingmachine.inventory.CoinInventorySupplier;
import mate.rajnai.vendingmachine.inventory.InventorySupplier;
import mate.rajnai.vendingmachine.inventory.TestHelperProductInventorySupplier;
import mate.rajnai.vendingmachine.reporting.Reporting;
import mate.rajnai.vendingmachine.reporting.ReportingImpl;

class ReportingImplTest {

	@Test
	void reportConsumptionByProduct_oneProduct() {
		InventorySupplier<Product> productSupplier = new TestHelperProductInventorySupplier();
		InventorySupplier<Coin> coinSupplier = new CoinInventorySupplier();
		VendingMachine vendingMachine = new VendingMachineImpl(productSupplier, coinSupplier);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.buyProductAndReturnChangesIfAny(Product.COKE);
		Reporting reporting = new ReportingImpl(vendingMachine);
		String report = reporting.reportConsumptionByProduct();
		String expectedReport = "Coke: 1\nPepsi: 0\nSoda: 0\n";
		assertEquals(expectedReport, report);
	}
	
	@Test
	void reportConsumptionByProduct_twoDifferentTypeOfProducts() {
		InventorySupplier<Product> productSupplier = new TestHelperProductInventorySupplier();
		InventorySupplier<Coin> coinSupplier = new CoinInventorySupplier();
		VendingMachine vendingMachine = new VendingMachineImpl(productSupplier, coinSupplier);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.buyProductAndReturnChangesIfAny(Product.COKE);
		vendingMachine.insertCoin(Coin.QUARTER);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.insertCoin(Coin.DIME);
		vendingMachine.buyProductAndReturnChangesIfAny(Product.SODA);
		Reporting reporting = new ReportingImpl(vendingMachine);
		String report = reporting.reportConsumptionByProduct();
		String expectedReport = "Coke: 1\nPepsi: 0\nSoda: 1\n";
		assertEquals(expectedReport, report);
	}
	
	@Test
	void reportConsumptionByProduct_threeProductsFromOneType() {
		InventorySupplier<Product> productSupplier = new TestHelperProductInventorySupplier();
		InventorySupplier<Coin> coinSupplier = new CoinInventorySupplier();
		VendingMachine vendingMachine = new VendingMachineImpl(productSupplier, coinSupplier);
		for (int i = 0; i < 3; i++) {
			vendingMachine.insertCoin(Coin.QUARTER);
			vendingMachine.insertCoin(Coin.DIME);
			vendingMachine.insertCoin(Coin.DIME);
			vendingMachine.buyProductAndReturnChangesIfAny(Product.SODA);
		}
		Reporting reporting = new ReportingImpl(vendingMachine);
		String report = reporting.reportConsumptionByProduct();
		String expectedReport = "Coke: 0\nPepsi: 0\nSoda: 3\n";
		assertEquals(expectedReport, report);
	}

}
