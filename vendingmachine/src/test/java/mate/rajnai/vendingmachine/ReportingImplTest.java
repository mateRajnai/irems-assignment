package mate.rajnai.vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import mate.rajnai.vendingmachine.inventory.CoinInventorySupplier;
import mate.rajnai.vendingmachine.inventory.InventorySupplier;
import mate.rajnai.vendingmachine.inventory.TestHelperProductInventorySupplier;

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
		String expectedReport = "Coke: 1";
		assertEquals(expectedReport, report);
	}

}
